package com.example.loginpagefragment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replacefragment(Login())
    }

    private fun replacefragment(fragment: Fragment) :Boolean
    {

        var fm =supportFragmentManager
        var ft=fm.beginTransaction()
        ft.replace(R.id.frmid,fragment).commit()

        return true
    }

}