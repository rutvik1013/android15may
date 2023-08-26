package com.example.taskmanagment

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import com.example.taskmanagment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DbHelper
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //intialization of dbhelper
        dbHelper= DbHelper(applicationContext)

        list=dbHelper.viewdata()

        var adapter=Myadapter(applicationContext,list)
        binding.list.adapter=adapter

        binding.insert.setOnClickListener {
            startActivity(Intent(applicationContext,Addactivity::class.java))
        }
        binding.list.setOnItemClickListener { adapterview:AdapterView<*>,view:View,i:Int,l:Long->
            var user=adapter.getItem(i).toString()

            var i=Intent(this,UpdateActivity::class.java)
            i.putExtra("id",user)
            startActivity(i)
        }
     
        }

    }


}

