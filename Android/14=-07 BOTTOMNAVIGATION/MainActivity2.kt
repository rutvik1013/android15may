package com.example.bottumnavigation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    lateinit var txt1:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        txt1=findViewById(R.id.txt1)

        var i=intent
        Toast.makeText(applicationContext, ""+i.getStringExtra("Data"), Toast.LENGTH_SHORT).show()

        var price=i.getStringExtra("data")
        var features=i.getStringExtra("features")

        txt1.setText(features)
    }
}