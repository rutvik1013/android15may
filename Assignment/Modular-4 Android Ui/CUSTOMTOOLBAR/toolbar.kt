package com.example.modulefour

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Spinner
import android.widget.Toast
import com.example.modulefour.databinding.ActivityToolbarBinding

class toolbar : AppCompatActivity(), AdapterView.OnItemSelectedListener {
   private lateinit var binding: ActivityToolbarBinding
   lateinit var list: MutableList<String>

   var array= arrayOf("Rutvik","Dhaval","Jayraj","Bhavesh","Bhavin")

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityToolbarBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        list=ArrayList()

        list.add("Rutvik")
        list.add("Dhaval")
        list.add("Jayraj")
        list.add("Bhavesh")
        list.add("Bhavin")

        var adapter=ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,list)
        binding.list.adapter=adapter

        binding.spinner.onItemSelectedListener=this


        binding.search.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)

                return true
            }
        })


    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(applicationContext, ""+array[p2], Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}