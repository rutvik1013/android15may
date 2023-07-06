package com.example.fragmenttoactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var im:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        im=findViewById(R.id.im1)
        im.setOnClickListener {
            var f1=firstfragment()
            var fm:FragmentManager=supportFragmentManager
            var ft:FragmentTransaction=fm.beginTransaction()
            ft.replace(R.id.fm1,f1).commit()
        }
    }
}