package com.example.sqliteinsertview

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
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

        registerForContextMenu(binding.list)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        var m1:MenuItem=menu!!.add(0,1,0,"Update")
        var m2:MenuItem=menu!!.add(0,2,0,"Delete")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var acm:AdapterContextMenuInfo=item.menuInfo as AdapterContextMenuInfo
        var id=acm.position
        var m:Model=list.get(id)

        when(item.itemId)
        {
            1->
            {
                var i=Intent(applicationContext,Update::class.java)
                i.putExtra("id",m.id)
                i.putExtra("myname",m.name)
                i.putExtra("mynum",m.number)
                startActivity(i)
            }
            2->
            {
                var alert=AlertDialog.Builder(this)
                alert.setTitle("Are you Sure you are deleting?")
                alert.setPositiveButton("Yes",{dialogInterface:DialogInterface,i:Int->
                    dbHelper.deleteData(m.id)
                    startActivity(Intent(applicationContext,MainActivity2::class.java))
                })
                alert.setNegativeButton("No",{dialogInterface:DialogInterface,i:Int->
                    dialogInterface.cancel()
                })
                alert.show()
            }
        }
        return super.onContextItemSelected(item)
    }

}