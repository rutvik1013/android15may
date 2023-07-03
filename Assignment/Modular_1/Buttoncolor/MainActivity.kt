package com.example.button

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Color.BLUE
import android.graphics.Color.YELLOW
import android.hardware.camera2.params.RggbChannelVector.BLUE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    lateinit var bt:Button
    lateinit var lin:LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt=findViewById(R.id.lin)
        lin=findViewById(R.id.bt)

        bt.setOnClickListener {
            lin.setBackgroundColor(Color.BLUE)
        }

    }
}