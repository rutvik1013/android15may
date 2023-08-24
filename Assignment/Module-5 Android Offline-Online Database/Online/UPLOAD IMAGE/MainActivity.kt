package com.example.uploadimageass

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.uploadimageass.databinding.ActivityMainBinding
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

        requirepermission()

        binding.btnselect.setOnClickListener {
            var i=Intent()
            i.type="image/*"
            i.action=Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i,"Select pic"),1)

        }
        binding.btnupload.setOnClickListener {
            var name=binding.edt.text.toString()
            val path=getpath(filepath)
            MultipartUploadRequest(this,"https://rutvikbabariya.000webhostapp.com/image/upload_image_assignment.php")
                .addFileToUpload(path,"url")
                .setMaxRetries(2)
                .startUpload()
            Toast.makeText(applicationContext, "Image Succesfully uploaded", Toast.LENGTH_SHORT).show()
        }
    }
    @SuppressLint("Range")
    fun getpath(uri: Uri?):String
    {
        var cursor=contentResolver.query(uri!!,null,null,null,null)
        cursor!!.moveToFirst()
        var id=cursor.getString(0)
        id=id.substring(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor.close()
        cursor=contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,MediaStore.Images.Media._ID+ "=?",
            arrayOf(id),null)

        cursor!!.moveToFirst()
        val path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor.close()

        return path
    }
    private fun requirepermission()
    {
        if (checkSelfPermission(READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE),100)
        }
        else
        {
            Toast.makeText(applicationContext, "Permission Already Granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==1&&resultCode== RESULT_OK&&data!=null)
        {
            filepath=data.data!!
            bitmap=MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            binding.img.setImageBitmap(bitmap)

        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}