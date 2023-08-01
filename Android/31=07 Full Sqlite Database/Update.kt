package com.example.sqliteinsertview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqliteinsertview.databinding.ActivityUpdateBinding

class Update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        dbHelper= DbHelper(applicationContext)

        var i=intent
        var id=i.getIntExtra("id",101)
        binding.edtname.setText(i.getStringExtra("myname"))
        binding.edtnum.setText(i.getStringExtra("mynum"))

        binding.update.setOnClickListener {
            var name=binding.edtname.text.toString()
            var number=binding.edtnum.text.toString()

            var m=Model()
            m.id=id
            m.name=name
            m.number=number

            var id=dbHelper.updateData(m)
            startActivity(Intent(applicationContext,MainActivity2::class.java))
        }

    }
}

