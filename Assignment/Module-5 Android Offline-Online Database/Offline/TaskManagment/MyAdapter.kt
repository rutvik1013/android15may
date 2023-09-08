package com.example.taskmanassign

import android.annotation.SuppressLint
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


class MyAdapter(var context: Context,var list: MutableList<Model>) : BaseAdapter()
{
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, ConvertView: View?, parent: ViewGroup?): View {
        var inflater : LayoutInflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.design,parent,false)

        var name : TextView = view.findViewById(R.id.task_name)
        var img : ImageView= view.findViewById(R.id.task_img)
        var rl : RelativeLayout = view.findViewById(R.id.rl)

        name.setText(list.get(position).name)

        if (list.get(position).status=="A")
        {
            img.setImageResource(R.drawable.countdown)
        }
        else if (list.get(position).status=="B")
        {
            img.setImageResource(R.drawable.correct)
        }

        if (list.get(position).priority=="high")
        {
            rl.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")))
        }
        else if (list.get(position).priority=="avg")
        {
            rl.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0000FF")))
        }
        else if (list.get(position).priority=="low")
        {
            rl.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00FF00")))
        }
        return view
    }

}