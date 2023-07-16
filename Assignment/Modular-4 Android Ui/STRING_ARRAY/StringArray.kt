package com.example.modulefour

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class StringArray : AppCompatActivity() {
    lateinit var list :ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_array)

        list=findViewById(R.id.list)

        val res :Resources=resources
        var a= arrayOf<String>()
        a=res.getStringArray(R.array.list)

        var adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,a)
        list.adapter=adapter
    }
}