package com.example.searchviewintoolbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.searchviewintoolbar.databinding.ActivityMainBinding


class MainActivity:AppCompatActivity()
{
  private lateinit var binding: ActivityMainBinding
  var adapter:ArrayAdapter<*>?=null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding= ActivityMainBinding.inflate(layoutInflater)
    val view=binding.root
    setContentView(view)

    adapter= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.name_array))
    binding.list.adapter=adapter

    binding.list.setOnItemClickListener { parent, view, position, id ->
      Toast.makeText(applicationContext,parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show()
    }
    binding.list.emptyView

  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu,menu)

    val search:MenuItem=menu!!.findItem(R.id.searchbar)
    val searchview:SearchView=search.actionView as SearchView
    searchview.queryHint=("Search")
    searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener
    {
      override fun onQueryTextSubmit(query: String?): Boolean {
        return false

      }

      override fun onQueryTextChange(newtext: String?): Boolean {
        adapter!!.filter.filter(newtext)
        return true
      }
    })
    return super.onCreateOptionsMenu(menu)
  }
}