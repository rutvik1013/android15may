package com.example.modulefour

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class internet : AppCompatActivity() {
    lateinit var btn:Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet)

        btn=findViewById(R.id.btn1)

        btn.setOnClickListener {
            if (isNetworkConnected())
            {
                Toast.makeText(applicationContext, "Internet", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext, "Not Available", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun isNetworkConnected():Boolean
    {
        val ab=applicationContext.getSystemService(CONNECTIVITY_SERVICE)as ConnectivityManager
        return ab.activeNetworkInfo!=null

    }
}