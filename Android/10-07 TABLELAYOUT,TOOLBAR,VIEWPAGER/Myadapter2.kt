package com.example.chatapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Myadapter2(var context: Context,var list: MutableList<model>):BaseAdapter()
{
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {
        var inflater=LayoutInflater.from(context)
        var view=inflater.inflate(R.layout.design,parent,false)

        var image:ImageView=view.findViewById(R.id.img)
        var text1:TextView=view.findViewById(R.id.txt1)
        var text2:TextView=view.findViewById(R.id.txt2)

        image.setImageResource(list.get(position).Image)
        text1.setText(list.get(position).name)
        text2.setText(list.get(position).phone)

        return view
    }


}