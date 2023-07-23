package com.example.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class BlankFragment :Fragment()
{
    lateinit var txt1:TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_blank,container,false)

        txt1=view.findViewById(R.id.txt1)
        txt1.setOnClickListener {
            // Fragment to Activity

            //var i=Intent(activity,MainActivity2::class.java)
            //startActivity(i)

            var b1=BlankFragment2()
            var fm:FragmentManager=requireFragmentManager()
            var ft:FragmentTransaction=fm.beginTransaction()
            ft.replace(R.id.frmid,b1).commit()
        }
            return view
    }
}