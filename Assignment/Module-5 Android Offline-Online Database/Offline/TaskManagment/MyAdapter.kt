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
        var inflater: LayoutInflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.design, parent, false)



        var image:ImageView=view.findViewById(R.id.img)
        var texttask:TextView=view.findViewById(R.id.task_name)
        var textdes:TextView=view.findViewById(R.id.task_des)
        var txtpriority=view.findViewById<View>(R.id.priority)

        texttask.setText(list.get(position).name)
        textdes.setText(list.get(position).description)

        if (list.get(position).status=="A")
        {
            image.setImageResource(R.drawable.countdown)
        }
        else if (list.get(position).status=="B")
        {
            image.setImageResource(R.drawable.correct)
        }
        if (list.get(position).priority=="high")
        {
            txtpriority.setBackgroundColor(Color.RED)
        }
        else if (list.get(position).priority=="avg")
        {
            txtpriority.setBackgroundColor(Color.BLUE)
        }
        else if (list.get(position).priority=="low")
        {
            txtpriority.setBackgroundColor(Color.GREEN)
        }


        return view
    }
}
