package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class MainActivity :AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    lateinit var e1:EditText
    lateinit var e2:EditText
    lateinit var e3:EditText
    lateinit var r1:RadioButton
    lateinit var r2:RadioButton
    lateinit var btn1:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1=findViewById(R.id.edt1)
        e2=findViewById(R.id.edt2)
        e3=findViewById(R.id.edt3)
        r1=findViewById(R.id.rb1)
        r2=findViewById(R.id.rb2)
        btn1=findViewById(R.id.btn1)

        r1.setOnCheckedChangeListener(this)
        r2.setOnCheckedChangeListener(this)

        btn1.setOnClickListener {
            var name=e1.text.toString()
            var pass=e2.text.toString()
            var adress=e3.text.toString()

            if (name.length==0&&pass.length==0&&adress.length==0)
            {
                e1.setError("Please Enter Student Name")
                e2.setError("Please Enter Student Password")
                e3.setError("Please Enter Student Adress")

            }
            else if (name.length==0)
            {
                e1.setError("Please Enter Name")
            }
            else if (pass.length==0)
            {
                e2.setError("Please Enter Student password")
            }
            else if (adress.length==0)
            {
                e3.setError("Please Enter Student Adress")
            }
            else
            {
                if (name.equals("Rutvik Babariya")&&pass.equals("1013")&&adress.equals("Ahmedabad"))
                {
                    Toast.makeText(applicationContext, "Correct Information", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                }
                else
                {
                    Toast.makeText(applicationContext, "Incorrect Information", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(buttonView==r1)
        {
            if (r1.isChecked)
            {
                Toast.makeText(applicationContext, "Male", Toast.LENGTH_SHORT).show()
            }
        }
        if (buttonView==r2)
        {
            if (r2.isChecked)
            {
                Toast.makeText(applicationContext, "Female", Toast.LENGTH_SHORT).show()
            }
        }
    }
}