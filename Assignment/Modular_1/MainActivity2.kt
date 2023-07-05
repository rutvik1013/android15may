package com.example.passdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity()
{
    lateinit var txt1:TextView
    lateinit var txt2:TextView
    lateinit var txt3:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var i=intent
        var name=i.getStringExtra("Name")
        var adress=i.getStringExtra("Adress")
        var phone=i.getStringExtra("Phone")


        txt1=findViewById(R.id.txt1)
        txt2=findViewById(R.id.txt2)
        txt3=findViewById(R.id.txt3)

        
       txt1.setText(name)
        txt2.setText(adress)
        txt3.setText(phone)

    }
}