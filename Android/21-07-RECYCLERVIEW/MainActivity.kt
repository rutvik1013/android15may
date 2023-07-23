package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recycle)

        list=ArrayList()

        var layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(applicationContext)
        recyclerView.layoutManager=layoutManager

        list.add(Model(R.drawable.samsung,"Samsung Galaxy s23 Ultra","1,20,000"))
        list.add(Model(R.drawable.redmi,"Redmi note 12","20,000"))
        list.add(Model(R.drawable.realme,"Realme","12,000"))
        list.add(Model(R.drawable.oppo,"Oppo","10,000"))
        list.add(Model(R.drawable.iphone14,"Iphone 14 pro Max","150,000"))

        var myadapter=MyAdapter(applicationContext,list)
        recyclerView.adapter=myadapter

    }
}