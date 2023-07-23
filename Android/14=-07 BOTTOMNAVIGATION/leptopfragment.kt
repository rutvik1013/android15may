package com.example.bottumnavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.bottumnavigation.model2.model2


class leptopfragment : Fragment() {
    lateinit var listView: ListView
    lateinit var list: MutableList<model2>



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_leptopfragment,container,false)

        listView=view.findViewById(R.id.list2)
        list=ArrayList()

        list.add(model2(R.drawable.apple,"Apple Macbook","80,000","Mac Os"))
        list.add(model2(R.drawable.asus,"Asus Zenbook","45,000","i5 10th gen"))
        list.add(model2(R.drawable.acer,"Acer inspiro","30,000","i3 11th gen"))
        list.add(model2(R.drawable.dell,"Dell Vastro","50,000","i7 12th gen"))
        list.add(model2(R.drawable.samleptop,"Samsung","60,000","i9 10th gen"))

        var adapter=Myadapter2(requireActivity(),list)
        listView.adapter=adapter

        return view

    }


}