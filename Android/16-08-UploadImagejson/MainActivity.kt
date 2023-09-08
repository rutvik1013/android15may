package com.example.uploadimage

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.uploadimage.databinding.ActivityMainBinding
import net.gotev.uploadservice.MultipartUploadRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var filepath:Uri
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        permission()

        binding.btn1.setOnClickListener {
            var i=Intent()
            i.type="image/*"
            i.action=Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i,"Select Picture"),1)

        }
        binding.btn2.setOnClickListener {
            val name=binding.edt1.text.toString()
            val path=getpath(filepath)
            MultipartUploadRequest(this,"https://rutvikbabariya.000webhostapp.com/image/upload.php")
                .addFileToUpload(path,"url")
                .addParameter("name",name)
                .setMaxRetries(2)
                .startUpload()
            Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()

        }


    }
    @SuppressLint("Range")
    fun getpath(uri: Uri?):String
    {
        var cursor=contentResolver.query(uri!!,null,null,null,null)
        cursor!!.moveToFirst()
        var document_id=cursor.getString(0)
        document_id= document_id.substring(document_id.lastIndexOf(":")+1)
        cursor.close()
        cursor=contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ,null,MediaStore.Images.Media._ID+"=?", arrayOf(document_id),null)
        cursor!!.moveToFirst()
        val path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor.close()
        return path
    }
    private fun permission()
    {
        if (checkSelfPermission(READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE),100)

        }
        else{
            Toast.makeText(applicationContext, "Permission Aleardy granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==1&&requestCode== RESULT_OK&&data!=null)
        {
            filepath=data.data!!
            bitmap=MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            binding.img.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}