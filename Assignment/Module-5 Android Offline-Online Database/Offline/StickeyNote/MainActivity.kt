package com.example.stickeynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.stickeynote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //intialization of db helper
        dbHelper= DbHelper(applicationContext)

        //button click event

        binding.add.setOnClickListener {
            var title=binding.edt1.text.toString()
            var des=binding.edt2.text.toString()

            //From Model class

            var m=Model()
            m.title=title
            m.description=des

            var id=dbHelper.insertdata(m)

            Toast.makeText(applicationContext, "Title Inserted"+id, Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext,Addstickey::class.java))

        }
    }
}