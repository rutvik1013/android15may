package com.example.empassign

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var btnsubmit:Button
    lateinit var imageuri:Uri
    lateinit var edtfname:EditText
    lateinit var edtlname:EditText
    lateinit var edtexp:EditText
    lateinit var edtemail:EditText
    lateinit var edtpass:EditText
    lateinit var edtmobile:EditText

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        imageuri=it!!
        imageView.setImageURI(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }
    private fun setup()
    {
        edtfname=findViewById(R.id.edtfname)
        edtlname=findViewById(R.id.edtlname)
        edtexp=findViewById(R.id.edtexperience)
        edtemail=findViewById(R.id.edtemail)
        edtpass=findViewById(R.id.edtpassword)
        edtmobile=findViewById(R.id.edtphone)
        imageView=findViewById(R.id.img)
        btnsubmit=findViewById(R.id.btnsubmit)
        imageView.setOnClickListener { contract.launch("image/*") }
        btnsubmit.setOnClickListener { upload() }
    }
    private fun upload()
    {
        var filesDir=applicationContext.filesDir
        var file=File(filesDir,"image.jpg")
        var inputStream=contentResolver.openInputStream(imageuri)
        var outputStream=FileOutputStream(file)
        inputStream!!.copyTo(outputStream)

        var requestBody=file.asRequestBody("image/*".toMediaTypeOrNull())
        var part=MultipartBody.Part.createFormData("image",file.name,requestBody)
        var ename1:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,edtfname.text.toString())
        var elname1:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,edtlname.text.toString())
        var eexp1:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,edtexp.text.toString())
        var edtemail1:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,edtemail.text.toString())
        var epass1:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,edtpass.text.toString())
        var emobile1:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,edtmobile.text.toString())


        var retrofit=Retrofit.Builder().baseUrl("https://rutvikbabariya.000webhostapp.com/API2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            var response=retrofit.uploaddata(part,ename1,elname1,eexp1,edtemail1,epass1,emobile1)
            Log.d("Cheezycoder",response.toString())

            //displayed toast msg

            var message=if (response!=null)
            {
                startActivity(Intent(applicationContext,MainActivity2::class.java))
                Toast.makeText(applicationContext, "Image uploaded successfully", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext, "Image not uploaded", Toast.LENGTH_SHORT).show()
            }

            // use the main thread to show the toast
            runOnUiThread {
                Toast.makeText(applicationContext, "Message", Toast.LENGTH_SHORT).show()
            }
        }
    }
}