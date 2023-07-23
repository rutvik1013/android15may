package com.example.bottumnavigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.bottumnavigation.model.model
import com.example.bottumnavigation.model2.model2

class Myadapter2(var context: Context,var list: MutableList<model2>):BaseAdapter()
{
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position:Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater=LayoutInflater.from(context)
        var view=inflater.inflate(R.layout.design,parent,false)

        var image:ImageView=view.findViewById(R.id.img)
        var txt1:TextView=view.findViewById(R.id.txtname)
        var txt2:TextView=view.findViewById(R.id.txtprice)
        var txt3:TextView=view.findViewById(R.id.txtfeature)

        image.setImageResource(list.get(position).image)
        txt1.setText(list.get(position).name)
        txt2.setText(list.get(position).price)
        txt3.setText(list.get(position).features)

        return view
    }

}