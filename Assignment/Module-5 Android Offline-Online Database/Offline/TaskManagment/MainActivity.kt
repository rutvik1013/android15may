package com.example.taskmanassign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanassign.databinding.ActivityMainBinding

class MainActivity :AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    lateinit var list: MutableList<Model>
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        dbHelper= DbHelper(applicationContext)

        list=dbHelper.viewdata()

        var adapter=MyAdapter(applicationContext,list)
        binding.list.adapter=adapter

        binding.insert.setOnClickListener {
                startActivity(Intent(applicationContext,Addactivity::class.java))

        }
        binding.list.setOnItemClickListener { adapterView, view, i, l ->
            var a=adapterView.getItemAtPosition(i).toString()

            var i=Intent(applicationContext,UpdateActivity::class.java)
            i.putExtra("id",a)
            startActivity(i)
        }
    }
}