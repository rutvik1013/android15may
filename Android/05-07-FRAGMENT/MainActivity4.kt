package com.example.fragments

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity4 : AppCompatActivity() {
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var btn1:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        edt1=findViewById(R.id.edt1)
        edt2=findViewById(R.id.edt2)
        edt3=findViewById(R.id.edt3)
        btn1=findViewById(R.id.btn1)

        btn1.setOnClickListener {
            var data=edt3.text.toString()

            if (data.equals("1013"))
            {
                var b=BlankFragment3()
                var fm:FragmentManager=supportFragmentManager
                var ft:FragmentTransaction=fm.beginTransaction()
                ft.replace(R.id.frm2,b).commit()
            }
            else
            {
                Toast.makeText(applicationContext, "Wrong", Toast.LENGTH_SHORT).show()
            }
        }


    }
}