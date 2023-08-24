package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todoapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    lateinit var dbHelper: dbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        dbHelper= dbHelper(applicationContext)

        var i=intent
        var id=i.getIntExtra("id",101)
        var taskname=i.getStringExtra("Task")
        var des=i.getStringExtra("Description")

        binding.edttask.setText(taskname)
        binding.edtdes.setText(des)

        binding.task.setOnClickListener {
            var task=binding.edttask.text.toString()
            var des=binding.edtdes.text.toString()

            var m=Model()

            m.id=id
            m.task=task
            m.des=des

            dbHelper.updatedata(m)

            Toast.makeText(applicationContext, "Updated Task", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }
    }
}