package com.example.modulefour

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RadioButton

class radiobuttoncolor : AppCompatActivity() {
    lateinit var yellow :RadioButton
    lateinit var blue:RadioButton
    lateinit var green:RadioButton
    lateinit var red:RadioButton
    lateinit var l2:LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radiobuttoncolor)

        yellow=findViewById(R.id.Yellow)
        blue=findViewById(R.id.Green)
        green=findViewById(R.id.Blue)
        red=findViewById(R.id.Red)
        l2=findViewById(R.id.ll)

        yellow.setOnCheckedChangeListener { compoundButton, b ->
            if (yellow.isChecked)
            {
                l2.setBackgroundColor(Color.parseColor("YELLOW"))
            }
        }
        blue.setOnCheckedChangeListener { compoundButton, b ->
            if (blue.isChecked)
            {
                l2.setBackgroundColor(Color.parseColor("BLUE"))
            }
        }
        green.setOnCheckedChangeListener { compoundButton, b ->
            if (green.isChecked)
            {
                l2.setBackgroundColor(Color.parseColor("GREEN"))
            }
        }
        red.setOnCheckedChangeListener { compoundButton, b ->
            if (red.isChecked)
            {
                l2.setBackgroundColor(Color.parseColor("RED"))
            }
        }

    }
}