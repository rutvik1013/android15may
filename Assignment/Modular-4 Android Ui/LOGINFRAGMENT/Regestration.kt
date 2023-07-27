package com.example.loginpagefragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class Regestration : Fragment() {
        lateinit var regestration: TextView
        lateinit var btn2:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_regestration, container, false)

        regestration=view.findViewById(R.id.reg)
        btn2=view.findViewById(R.id.btn)

        btn2.setOnClickListener {
           replacefragment(Login())
        }

        return view
    }
    private fun replacefragment(fragment: Fragment) :Boolean
    {
        val fm=requireFragmentManager()
        val ft=fm.beginTransaction()
        ft.replace(R.id.frmid,fragment).commit()

        return true
    }
}