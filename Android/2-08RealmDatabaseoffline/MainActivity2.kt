package com.example.realm

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.realm.databinding.ActivityMain2Binding
import io.realm.Realm

class MainActivity2 : AppCompatActivity(), AdapterView.OnItemLongClickListener {
    private lateinit var binding: ActivityMain2Binding
    lateinit var realm: Realm
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        realm= Realm.getInstance(Realm.getDefaultConfiguration()!!)
        list=ArrayList()

        realm.beginTransaction()

        var realmResults=realm.where(Model::class.java).findAll()

        for (i in realmResults.indices)
        {
            list.add(realmResults[i]!!)
        }
        var adapter=Myadapter(applicationContext,list)
        binding.list.adapter=adapter

        realm.commitTransaction()
        binding.list.setOnItemLongClickListener(this)



    }

    @SuppressLint("MissingInflatedId")
    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        var alert=AlertDialog.Builder(this)
        alert.setTitle("Select Operations?")
        alert.setPositiveButton("Update",{dialogInterface:DialogInterface,i:Int->

            var alert2=AlertDialog.Builder(this)
            var inflater=LayoutInflater.from(this)
            var view=inflater.inflate(R.layout.activity_update,null)

            var edtname:EditText=view.findViewById(R.id.edit1)
            var edtnum:EditText=view.findViewById(R.id.edit2)

            edtname.setText(list.get(position).name)
            edtnum.setText(list.get(position).num)

            alert2.setPositiveButton("Edit",{dialogInterface:DialogInterface,i:Int->
                realm.beginTransaction()
                var name=edtname.text.toString()
                var num=edtnum.text.toString()
                list.get(position).name=name
                list.get(position).num=num
                startActivity(Intent(this,MainActivity2::class.java))

                realm.commitTransaction()
            })
            alert.setNegativeButton("Delete",{dialogInterface:DialogInterface,i:Int->
                realm.beginTransaction()
                var realmResults=realm.where(Model::class.java).findAll()
                realmResults.deleteAllFromRealm()
                realm.commitTransaction()
                startActivity(Intent(applicationContext,MainActivity2::class.java))

            })




        })
        return false
    }

    override fun onBackPressed() {
       finishAffinity()
    }
}


