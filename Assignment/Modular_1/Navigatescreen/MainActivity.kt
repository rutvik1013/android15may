package com.example.navigatescreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn=findViewById(R.id.btn)

        btn.setOnClickListener {
            var i=Intent(applicationContext,MainActivity2::class.java)
            startActivity(i)
        }
    }
}