package com.example.modulefour

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TextView

class addtextview : AppCompatActivity() {
    lateinit var teble:TableLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtextview)

        teble=findViewById(R.id.table)

        var a=TextView(this)
        a.text="TextView Generated"

        teble.addView(a)
    }
}