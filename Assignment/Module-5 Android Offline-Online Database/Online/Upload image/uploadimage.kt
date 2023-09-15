package com.example.empprofile

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

class uploadimage : AppCompatActivity()
{
    lateinit var btn: Button
    private var imageView: ImageView?=null
    private val GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploadimage)

        permission()

        btn = findViewById(R.id.btnGallery)
        imageView = findViewById<View>(R.id.imageView) as ImageView

        btn.setOnClickListener {
            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(galleryIntent, GALLERY)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED)
        {
            return
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    imageView!!.setImageBitmap(bitmap)
                    uploadImage(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@uploadimage, "Failed image", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun uploadImage(bitmap: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val encodedImage =
            Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
        val imgname = Calendar.getInstance().timeInMillis.toString()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(imageinterface.imageurl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api: imageinterface = retrofit.create(imageinterface::class.java)
        Log.d("image", "$encodedImage   >>$imgname")
        Log.d("gallary", "   >>$imgname")
        val call: Call<String?>? = api.getUserLogin(imgname, encodedImage)
        call!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.i("Responsestring", response.body().toString())
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Log.i("onSuccessimg", response.body().toString())
                        val jsonresponse = response.body().toString()

                    } else {
                        Log.i(
                            "onEmptyResponse",
                            "Returned empty response"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {


            }
        })
    }

    fun permission() {

        if(checkSelfPermission(READ_EXTERNAL_STORAGE)!=PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(READ_EXTERNAL_STORAGE),1)
        }

    }
}

