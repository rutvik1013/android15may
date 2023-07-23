package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.chatapp.databinding.FragmentChatBinding


class Chat : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    lateinit var list: MutableList<model>
    lateinit var listView: ListView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentChatBinding.inflate(inflater,container,false)
        val view=binding.root
        listView=view.findViewById(R.id.list)

        list=ArrayList()

        list.add(model(R.drawable.dhoni,"m.s.dhoni","6351202084"))
        list.add(model(R.drawable.virat,"Virat Kohli","9265518695"))
        list.add(model(R.drawable.sachin,"sachin tenulakar","6351002999"))
        list.add(model(R.drawable.yuvraj,"yuvaraj singh","9878787856"))
        list.add(model(R.drawable.rohit,"Rohit sharma","6355053750"))

        var adapter=Myadapter2(requireActivity(),list)
        listView.adapter=adapter

        return view
    }




}