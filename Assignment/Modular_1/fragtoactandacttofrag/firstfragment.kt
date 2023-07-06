package com.example.fragmenttoactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class firstfragment : Fragment() {
    lateinit var btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_firstfragment, container, false)

         btn= view.findViewById(R.id.btn)

        btn.setOnClickListener {
            var i=Intent(activity,MainActivity::class.java)
            startActivity(i)

        }
        return view
    }

}
