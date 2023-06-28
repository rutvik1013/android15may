package com.example.customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.customlistview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var list:MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        list=ArrayList()

        list.add("Rutvik")
        list.add("DhavaL")
        list.add("Jayraj")
        list.add("Bhavesh")

        var adapter=ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,list)
        binding.list1.adapter=adapter

    }
}