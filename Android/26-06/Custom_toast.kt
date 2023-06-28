package com.example.layouts

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast

class CustomtoastEx : AppCompatActivity()
{

    lateinit var btn1:Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.design)

        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {

            var inflater = LayoutInflater.from(applicationContext)
            var view =  inflater.inflate(R.layout.activity_customtoast_ex,null)
            var toast = Toast(this)
            toast.view=view
            toast.duration=Toast.LENGTH_LONG
            toast.show()
        }

    }