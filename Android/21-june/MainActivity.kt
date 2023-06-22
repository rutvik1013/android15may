package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.Toast

class MainActivity :AppCompatActivity(),CompoundButton.OnCheckedChangeListener{
    lateinit var rb1:RadioButton
    lateinit var rb2:RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rb1=findViewById(R.id.rb1)
        rb2=findViewById(R.id.rb2)

        rb1.setOnCheckedChangeListener(this)
        rb2.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (buttonView == rb1) {
            if (rb1.isChecked) {
                Toast.makeText(applicationContext, "Male", Toast.LENGTH_SHORT).show()
            }
        }
        if (buttonView == rb2) {
            if (rb2.isChecked) {
                Toast.makeText(applicationContext, "Female", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

