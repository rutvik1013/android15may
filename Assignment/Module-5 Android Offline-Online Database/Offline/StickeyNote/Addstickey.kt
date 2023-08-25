package com.example.stickeynote

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import com.example.stickeynote.databinding.ActivityAddstickeyBinding

class Addstickey : AppCompatActivity() {
    private lateinit var binding: ActivityAddstickeyBinding
    lateinit var dbHelper: DbHelper
    lateinit var list: MutableList<Model>
    var arrayList=ArrayList<HashMap<String,String?>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddstickeyBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        dbHelper= DbHelper(applicationContext)
        list=dbHelper.viewdata()

        for (i in list)
        {
            var data=HashMap<String,String?>()
            data["TITLE"]=i.title
            data["DESCRIPTION"]=i.description
            arrayList.add(data)

        }
        var from= arrayOf("TITLE","DESCRIPTION")
        var to= intArrayOf(R.id.txttitle,R.id.txtdes)

        var adapter=SimpleAdapter(applicationContext,arrayList,R.layout.design,from, to)
        binding.list.adapter=adapter

        registerForContextMenu(binding.list)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        var menu1:MenuItem=menu!!.add(0,1,0,"Update")
        var menu2:MenuItem=menu!!.add(0,2,0,"Delete")

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var user:AdapterContextMenuInfo=item.menuInfo as AdapterContextMenuInfo
        var id=user.position
        var m:Model=list.get(id)
        when(item.itemId)
        {
            1->
            {
                var i=Intent(applicationContext,Update::class.java)
                i.putExtra("id",m.id)
                i.putExtra("Title",m.title)
                i.putExtra("Description",m.description)
                startActivity(i)
            }
            2->
            {
                var alert=AlertDialog.Builder(this)
                alert.setTitle("Are you sure you want to delete this stickey note?")
                alert.setPositiveButton("Yes",{dialogInterface:DialogInterface,i:Int->
                    dbHelper.deletedata(m.id)
                    startActivity(Intent(applicationContext,Addstickey::class.java))

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