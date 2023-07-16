package com.example.inputnumbers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1=findViewById(R.id.edt1)
        edt2=findViewById(R.id.edt2)
        btn=findViewById(R.id.btn1)

        btn.setOnClickListener {
            edt1=findViewById(R.id.edt1)
            edt2=findViewById(R.id.edt2)

            var i=Intent(this,MainActivity2::class.java)
            i.putExtra("Start",edt1.text.toString())
            i.putExtra("End",edt2.text.toString())
            startActivity(i)
        }
    }
}