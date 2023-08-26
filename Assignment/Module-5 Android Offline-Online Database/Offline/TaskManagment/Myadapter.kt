package com.example.taskmanagment

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater=LayoutInflater.from(context)
        var view=inflater.inflate(R.layout.design,parent,false)

        var txtname:TextView=view.findViewById(R.id.taskname)
        var image:ImageView=view.findViewById(R.id.taskimg)
        var relative:RelativeLayout=view.findViewById(R.id.r1)

        txtname.setText(list.get(position).name)

        if (list.get(position).status=="A")
        {
            image.setImageResource(R.drawable.countdown)
        }
        else if (list.get(position).status=="B")
        {
            image.setImageResource(R.drawable.correct)
        }
        if (list.get(position).priority=="High")
        {
            relative.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")))
        }
        else if (list.get(position).priority=="Average")
        {
            relative.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0000FF")))
        }
        else if (list.get(position).priority=="Low")
        {
            relative.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("##00FF00")))
        }
        return view
    }

}