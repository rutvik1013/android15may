package com.example.modulefour

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class googleurl : AppCompatActivity() {
    lateinit var txt:TextView
    lateinit var btn1:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_googleurl)

        txt=findViewById(R.id.txt1)
        btn1=findViewById(R.id.btn1)

        btn1.setOnClickListener {

            var website="https://www.google.com/"
            var i=Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(website))
            startActivity(i)

            onBackPressed()
            finishAffinity()
        }
    }
}