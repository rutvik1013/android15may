package com.example.modulefour

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class textsizeincredecre : AppCompatActivity() {
    lateinit var txt:TextView
    lateinit var btn1:Button
    lateinit var btn2:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textsizeincredecre)

        txt=findViewById(R.id.txt1)
        btn1=findViewById(R.id.incre)
        btn2=findViewById(R.id.decre)

        btn1.setOnClickListener {
            txt.setTextSize(0,txt.textSize+3f)
        }
        btn2.setOnClickListener {
            txt.setTextSize(0,txt.textSize-3f)
        }
    }
}