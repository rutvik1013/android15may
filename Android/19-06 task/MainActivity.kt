package com.example.foodapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import  android.widget.CheckBox
import android.widget.CompoundButton
import  android.widget.Toast

class MainActivity :AppCompatActivity(),CompoundButton.OnCheckedChangeListener{

    //1

    lateinit var c1:CheckBox
    lateinit var c2:CheckBox
    lateinit var c3:CheckBox
    lateinit var c4:CheckBox
    lateinit var btn1:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //2

        c1=findViewById(R.id.ch1)
        c2=findViewById(R.id.ch2)
        c3=findViewById(R.id.ch3)
        c4=findViewById(R.id.ch4)
        btn1=findViewById(R.id.btn1)

        /* c1.setOnCheckedChangeListener { buttonView, isChecked ->  }
         c2.setOnCheckedChangeListener { buttonView, isChecked ->  }
         c3.setOnCheckedChangeListener { buttonView, isChecked ->  }
         c4.setOnCheckedChangeListener { buttonView, isChecked ->  }*/

        //3 Click Event

        c1.setOnCheckedChangeListener(this)
        c2.setOnCheckedChangeListener(this)
        c3.setOnCheckedChangeListener(this)
        c4.setOnCheckedChangeListener(this)


        //4

        btn1.setOnClickListener {

            if (c1.isChecked)
            {
                Toast.makeText(applicationContext,"English",Toast.LENGTH_LONG).show()

            }
            if(c2.isChecked)
            {
                Toast.makeText(applicationContext,"Hindi",Toast.LENGTH_LONG).show()
            }
            if (c3.isChecked)
            {
                Toast.makeText(applicationContext,"Gujarati",Toast.LENGTH_LONG).show()
            }
            if(c4.isChecked)
            {
                Toast.makeText(applicationContext,"Sanskrit",Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

    }
}