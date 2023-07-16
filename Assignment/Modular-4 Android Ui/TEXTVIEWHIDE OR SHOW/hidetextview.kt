package com.example.modulefour

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class hidetextview : AppCompatActivity() {
    lateinit var txt:TextView
    lateinit var bt1:Button
    lateinit var bt2:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hidetextview)

        txt=findViewById(R.id.txt1)
        bt1=findViewById(R.id.btn1)
        bt2=findViewById(R.id.btn2)

        bt1.setOnClickListener {
            txt.visibility=View.INVISIBLE
            Toast.makeText(applicationContext, "Hide", Toast.LENGTH_SHORT).show()
        }
        bt2.setOnClickListener {
            txt.visibility=View.VISIBLE
            Toast.makeText(applicationContext, "Show", Toast.LENGTH_SHORT).show()
        }
    }
}