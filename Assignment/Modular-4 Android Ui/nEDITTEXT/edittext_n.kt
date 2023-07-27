package com.example.modulefour


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class edittext_n:AppCompatActivity()
{
    lateinit var layout: LinearLayout
    lateinit var editText: EditText
    lateinit var btn:Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittext_n)

        layout=findViewById(R.id.linear)
        btn=findViewById(R.id.button)
        editText=findViewById(R.id.Edittext)

        btn.setOnClickListener {
            addNewEditText(editText.text.toString().toInt())
        }

    }
    private fun addNewEditText(n:Int)
    {
        for (i in 1..n)
        {
            var edt=EditText(this)
            layout.addView(edt)
        }
    }
}