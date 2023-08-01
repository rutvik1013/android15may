package com.example.sqliteinsertview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqliteinsertview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dbHelper=DbHelper(applicationContext)

        binding.insert.setOnClickListener {
            var name = binding.edt1.text.toString()
            var number = binding.edt2.text.toString()

            var m = Model()
            m.name = name
            m.number = number

            var id = dbHelper.insertData(m)
            Toast.makeText(applicationContext, "Inserted", Toast.LENGTH_SHORT).show()
        }

        binding.view.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity2::class.java))

        }

    }
}