package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var list:MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view=binding.root
        setContentView(view)

        list=ArrayList()

        list.add(" Amul Milk")
        list.add(" Amul Butter milk")
        list.add(" Amul Dahi")
        list.add("Amul ghee")
        list.add("Amul cheese")
        list.add("Amul kool koko")
        list.add("Amul Dark chocolate")
        list.add("Amul Mithai")
        list.add("Amul Choco Butter")
        list.add("Amul Kulfi")

        var adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,list)
        binding.list.adapter=adapter

    }

}