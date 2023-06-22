package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myapplication.databinding.ActivityFormBinding
import com.example.myapplication.databinding.ActivityMainBinding

class Form : AppCompatActivity()
{
    private lateinit var binding: ActivityFormBinding
    var city= arrayOf("Rajkot","Baroda","Surat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFormBinding.inflate(layoutInflater)
        val  view=binding.root
        setContentView(view)

        var data=ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,city)
        binding.spin.adapter=data

        binding.btn1.setOnClickListener {
            var name=binding.edt1.text.toString()
            Toast.makeText(applicationContext, ""+name, Toast.LENGTH_SHORT).show()


        }




    }
}
