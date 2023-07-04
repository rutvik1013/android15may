package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.listview.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    lateinit var list: MutableList<model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        list=ArrayList()

        list.add(model(R.drawable.milk,"Amul Milk","50"))
        list.add(model(R.drawable.dahi,"Amul Dahi","20"))
        list.add(model(R.drawable.buttermilk,"ButterMilk","30"))
        list.add(model(R.drawable.ghee,"Amul Ghee","520"))
        list.add(model(R.drawable.kool,"Amul Kool Koko","100"))
        list.add(model(R.drawable.kulfi,"Kulfi","15"))
        list.add(model(R.drawable.chocolate,"Chocolate","30"))
        list.add(model(R.drawable.choco,"Choco Butter","80"))
        list.add(model(R.drawable.cheese,"Amul Cheese","40"))
        list.add(model(R.drawable.mithai,"Amul Mithai Mate","150"))

        var adapter=Myadapter(applicationContext,list)
        binding.list2.adapter=adapter

    }
}