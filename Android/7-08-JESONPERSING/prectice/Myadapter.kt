package com.example.jasonpassing

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class Myadapter(var context: Context,var list: MutableList<Model>):BaseAdapter()
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
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var layout=LayoutInflater.from(context)
        var view=layout.inflate(R.layout.design,parent,false)

        var text1:TextView=view.findViewById(R.id.txt1)
        var text2:TextView=view.findViewById(R.id.txt2)
        var text3:TextView=view.findViewById(R.id.txt3)
        var image:ImageView=view.findViewById(R.id.img)

        text1.setText(list.get(position).name)
        text2.setText(list.get(position).surname)
        text3.setText(list.get(position).highscore)
        Picasso.get().load(list.get(position).image).into(image)

        return view

    }

}