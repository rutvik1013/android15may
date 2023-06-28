package com.example.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    lateinit var relativeLayout: RelativeLayout
    lateinit var txt1:TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.table)

        relativeLayout = RelativeLayout(this)
        txt1 = TextView(this)


        txt1.setText("Hello")
        var width=100
        var height =100

        relativeLayout.addView(txt1,width,height)
        setContentView(relativeLayout)

    }
}