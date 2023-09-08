package com.example.taskmanassign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskmanassign.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    lateinit var list: MutableList<Model>
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)





        var i=intent
        var position=i.getStringExtra("id")!!.toInt()
        var m=Model()

        dbHelper= DbHelper(applicationContext)
        list=dbHelper.viewdata()

        binding.updatetask.setText(list.get(position).name)
        binding.updatedes.setText(list.get(position).description)
        binding.date.setText(list.get(position).date)
        binding.time.setText(list.get(position).time)
        binding.priority.setText(list.get(position).priority)

        binding.complete.setOnClickListener {
            m.status="B"

            dbHelper.updatestatus(m,list.get(position).id)
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }
        binding.delete.setOnClickListener {
            dbHelper.deletedata(list.get(position).id)
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }
    }
}