package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityMain2Binding

class MainActivity2 :AppCompatActivity()
{
    private lateinit var binding: ActivityMain2Binding
    lateinit var dbHelper: dbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //intialization of dbhelper
        dbHelper= dbHelper(applicationContext)

        //click event of new task
        binding.task.setOnClickListener {
            var taskname=binding.edttask.text.toString()
            var taskdes=binding.edtdes.text.toString()

            var m=Model()

            m.task=taskname
            m.des=taskdes

            dbHelper.insertdata(m)

            Toast.makeText(applicationContext, "Inserted Task", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }
    }
}