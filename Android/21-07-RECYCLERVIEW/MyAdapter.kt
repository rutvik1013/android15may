package com.example.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

 class MyAdapter(var context: Context, var list: MutableList<Model>) : RecyclerView.Adapter<MyView>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        var layoutInflater=LayoutInflater.from(context)
        var view=layoutInflater.inflate(R.layout.design,parent,false)
        return MyView(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.image.setImageResource(list.get(position).image)
        holder.textView.setText(list.get(position).name)
        holder.textView2.setText(list.get(position).Price)
    }

}
class MyView(itemview :View) :RecyclerView.ViewHolder(itemview)
{
    lateinit var image :ImageView
    lateinit var textView: TextView
    lateinit var textView2: TextView

    init {
        image=itemview.findViewById(R.id.i1)
        textView=itemview.findViewById(R.id.txt)
        textView2=itemview.findViewById(R.id.txt2)

    }
}