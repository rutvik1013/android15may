package com.example.modulefour

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class radiobutton : AppCompatActivity() {
    lateinit var e1:EditText
    lateinit var e2:EditText
    lateinit var add:RadioButton
    lateinit var sub:RadioButton
    lateinit var mul:RadioButton
    lateinit var div:RadioButton
    lateinit var bt:Button
    lateinit var txt:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radiobutton)

        e1=findViewById(R.id.edt1)
        e2=findViewById(R.id.edt2)

        add=findViewById(R.id.r1)
        sub=findViewById(R.id.r2)
        mul=findViewById(R.id.r3)
        div=findViewById(R.id.r4)

        bt=findViewById(R.id.btn1)
        txt=findViewById(R.id.txt1)

        add.setOnCheckedChangeListener { compoundButton, b ->
            if (add.isChecked)
            {
                bt.setOnClickListener {
                    txt.setText("${e1.text.toString().toInt()+e2.text.toString().toInt()}")
                }
            }
        }
        sub.setOnCheckedChangeListener { compoundButton, b ->
            if (sub.isChecked)
            {
                bt.setOnClickListener {
                    txt.setText("${e1.text.toString().toInt()-e2.text.toString().toInt()}")
                }
            }
        }
        mul.setOnCheckedChangeListener { compoundButton, b ->
            if (mul.isChecked)
            {
                bt.setOnClickListener {
                    txt.setText("${e1.text.toString().toInt()*e2.text.toString().toInt()}")

                }
            }
        }
        div.setOnCheckedChangeListener { compoundButton, b ->
            if (div.isChecked)
            {
                bt.setOnClickListener {
                    txt.setText("${e1.text.toString().toInt()/e2.text.toString().toInt()}")
                }
            }
        }
    }
}