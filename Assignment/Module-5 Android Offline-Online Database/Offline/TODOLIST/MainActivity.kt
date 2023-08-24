package com.example.todoapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityMainBinding


class MainActivity :AppCompatActivity()
{
    //view binding
    private lateinit var binding: ActivityMainBinding
    lateinit var list: MutableList<Model>
    var arrayList:ArrayList<HashMap<String,String?>> = ArrayList()
    lateinit var dbHelper: dbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root

        //floating action button click event
        binding.floting.setOnClickListener {
            startActivity(Intent(applicationContext,MainActivity2::class.java))
        }
        //intialization of list and db helper
        list=ArrayList()
        dbHelper= dbHelper(applicationContext)

        list=dbHelper.viewdata()

        for (i in list)
        {
            var hm=HashMap<String,String?>()
            hm["TASK"]=i.task
            hm["DESCRIPTION"]=i.des

            arrayList.add(hm)
        }
        var from= arrayOf("TASK","DESCRIPTION")
        var to= intArrayOf(R.id.task,R.id.des)

        var adapter=SimpleAdapter(applicationContext,arrayList,R.layout.design,from, to)
        binding.list.adapter=adapter

        registerForContextMenu(binding.list)



    }
    //for Update and delete
    //Context menu
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        var menu1=menu!!.add(0,1,0,"Update")
        var menu2=menu!!.add(0,2,0,"Remove")

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var acm: AdapterContextMenuInfo=item.menuInfo as AdapterContextMenuInfo
        var id=acm.position
        var user=list[id]

        when(item.itemId)
        {
            1->

            {
                //for update data

                var i=Intent(applicationContext,UpdateActivity::class.java)
                i.putExtra("id",user.id)
                i.putExtra("Task",user.task)
                i.putExtra("Description",user.des)
                startActivity(i)
            }
            2->
            {
                //for delete data
                var alert=AlertDialog.Builder(this)
                alert.setTitle("Are you Sure you want to removing this task?")
                alert.setPositiveButton("Yes",{dialogInterface:DialogInterface,i:Int->

                    dbHelper.deletedata(user.id)
                    startActivity(Intent(applicationContext,MainActivity::class.java))

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


