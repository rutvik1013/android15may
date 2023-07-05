package com.example.passdata

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var btn:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1=findViewById(R.id.edt1)
        edt2=findViewById(R.id.edt2)
        edt3=findViewById(R.id.edt3)
        btn=findViewById(R.id.btn)

        btn.setOnClickListener {
            var edt1=edt1.text.toString()
            var edt2=edt2.text.toString()
            var edt3=edt3.text.toString()


            var i=Intent(applicationContext,MainActivity2::class.java)
            i.putExtra("Name",edt1)
            i.putExtra("Adress",edt2)
            i.putExtra("Phone",edt3)
            startActivity(i)
        }


    }
}