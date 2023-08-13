package com.example.jsonperceingtask

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class Myadapter(var context:Context,var list: MutableList<Model>):BaseAdapter() {
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
        val view=layout.inflate(R.layout.design,parent,false)

        var image:ImageView=view.findViewById(R.id.img)
        var text1:TextView=view.findViewById(R.id.txtname)
        var text2:TextView=view.findViewById(R.id.txtrealname)
        var text3:TextView=view.findViewById(R.id.txtteam)
        var text4:TextView=view.findViewById(R.id.txtapperence)
        var text5:TextView=view.findViewById(R.id.txtcreated)
        var text6:TextView=view.findViewById(R.id.txtpublisher)
        var text7:TextView=view.findViewById(R.id.txtbio)

        Picasso.get().load(list.get(position).imageurl).into(image)
        text1.setText(list.get(position).name)
        text2.setText(list.get(position).realname)
        text3.setText(list.get(position).team)
        text4.setText(list.get(position).firstapperence)
        text5.setText(list.get(position).createdby)
        text6.setText(list.get(position).publisher)
        text7.setText(list.get(position).bio)


        return view

    }
}