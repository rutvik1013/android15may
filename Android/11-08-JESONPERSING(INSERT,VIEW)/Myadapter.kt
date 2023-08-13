package com.example.jsonpersingcrud

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
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

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater=LayoutInflater.from(context)
        var view=inflater.inflate(R.layout.design,parent,false)

        var txt1:TextView=view.findViewById(R.id.txtpname)
        var txt2:TextView=view.findViewById(R.id.txtprice)
        var txt3:TextView=view.findViewById(R.id.txtdes)

        txt1.setText(list.get(position).pname)
        txt2.setText(list.get(position).pprice)
        txt3.setText(list.get(position).pdes)

        return view
    }

}