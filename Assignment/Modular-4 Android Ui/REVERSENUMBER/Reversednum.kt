package com.example.modulefour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.modulefour.databinding.ActivityReversednumBinding
import java.lang.StringBuilder

class Reversednum:AppCompatActivity()
{
    lateinit var edt:EditText
    lateinit var txt:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reversednum)

        edt=findViewById(R.id.edt1)
        txt=findViewById(R.id.txt1)

        edt.addTextChangedListener (object : TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                txt.setText(edt.text.toString().reversed())
            }
        })
    }
}