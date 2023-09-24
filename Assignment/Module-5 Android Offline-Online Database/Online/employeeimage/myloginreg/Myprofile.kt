package com.example.myloginreg

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myloginreg.databinding.ActivityMyprofileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream

class Myprofile : AppCompatActivity() {
    private lateinit var binding: ActivityMyprofileBinding
    lateinit var imageuri:Uri

    private val contract=registerForActivityResult(ActivityResultContracts.GetContent())
    {
        imageuri=it!!
        binding.empimage.setImageURI(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyprofileBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        setup()

    }
    private fun setup()
    {
        binding.empimage.setOnClickListener { contract.launch(("image/*")) }
        binding.btnimage.setOnClickListener {
                startActivity(Intent(applicationContext,ViewActivity::class.java))
        }
    }
    private fun upload()
    {
        val fileDir=applicationContext.filesDir
        val file=File(fileDir,"image.png")
        val inputstream=contentResolver.openInputStream(imageuri)
        val outputStream=FileOutputStream(file)
        inputstream!!.copyTo(outputStream)

        val requestBody=file.asRequestBody("image/*".toMediaTypeOrNull())
        val part=MultipartBody.Part.createFormData("image",file.name,requestBody)
        var fname:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,binding.edtfname.text.toString())
        var lname:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,binding.edtlname.text.toString())
        var e_experience:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,binding.edtexperience.text.toString())
        var email:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,binding.edtemail.text.toString())
        var password:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,binding.edtpassword.text.toString())
        var mobile:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,binding.edtphone.text.toString())


        val retrofit=Retrofit.Builder().baseUrl("https://rutvikbabariya.000webhostapp.com/API2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response=retrofit.uploaddata(part,fname,lname,e_experience,email,password,mobile)
            Log.d("Cheezycoder",response.toString())

        }
    }
}