package com.example.bottumnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.bottumnavigation.model.model


class mobilefragment : Fragment() {
    lateinit var listView: ListView
    lateinit var list: MutableList<model>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_mobilefragment,container,false)

        listView=view.findViewById(R.id.list)
        list=ArrayList()

        list.add(model(R.drawable.iphone,"Iphone 14 pro max", "1,50,000", "island display"))
        list.add(model(R.drawable.samsung,"Samsung s23 Ultra","1,00,000","200x Zoom"))
        list.add(model(R.drawable.redmi,"Redmi 10 pro","20,000","108 MP Camara"))
        list.add(model(R.drawable.realme,"Realme 10","10,000","50 MP Camara"))
        list.add(model(R.drawable.oneplus,"Oneplus 10 pro","30,000","Big Screen"))

        var adapter=Myadapter(requireActivity(),list)
        listView.adapter=adapter

        return view
    }


}