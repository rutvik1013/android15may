package com.example.sqliteinsertview

import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.sqliteinsertview.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity()
{
    private lateinit var binding: ActivityMain2Binding
    var list:List<Model> =ArrayList()
    var arrayList=ArrayList<HashMap<String,String?>>()
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        dbHelper= DbHelper(applicationContext)
        list=dbHelper.viewData()

        for (i in list)
        {
            var hm=HashMap<String,String?>()
            hm["NAME"]=i.name
            hm["Number"]=i.number
            arrayList.add(hm)


        }
        var from= arrayOf("NAME","NUMBER")
        var to= intArrayOf(R.id.txname,R.id.txnum)

        var adapter= SimpleAdapter(applicationContext,arrayList,R.layout.design,from, to)
        binding.list.adapter=adapter
    }

}