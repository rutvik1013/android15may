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

class Login : Fragment() {
        lateinit var login: TextView
        lateinit var btn1:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        login = view.findViewById(R.id.txt1)
        btn1=view.findViewById(R.id.Login)


        btn1.setOnClickListener {
                    replacefragment(Regestration())
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