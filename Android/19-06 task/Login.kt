package com.example.foodapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login :AppCompatActivity()
{
    lateinit var e1:EditText
    lateinit var e2:EditText
    lateinit var bt:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        e1=findViewById(R.id.edt1)
        e2=findViewById(R.id.edt2)
        bt=findViewById(R.id.btn1)

        bt.setOnClickListener {
            var mobile=e1.text.toString()
            var pass=e2.text.toString()

            if(mobile.length==0&&pass.length==0)
            {
                e1.setError("Please enter mobile number")
                e2.setError("please Enter password")
            }
            else if (mobile.length==0)
            {
                e2.setError("please Enter password")
            }
            else if (pass.length==0)
            {
                e1.setError("please Enter mobile number")
            }
            else
            {
                if (mobile.equals("6351202084")&&pass.equals("1013"))
                {
                    Toast.makeText(applicationContext,"Logged Succesfully",Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext,home::class.java))
                }
                else
                {
                    Toast.makeText(applicationContext,"Invalid Credentials",Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun onBackPressed() {
        finishAffinity()
    }
}