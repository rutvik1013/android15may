package com.example.stickeynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stickeynote.databinding.ActivityUpdateBinding

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
        var id=i.getIntExtra("id",102)
        binding.edt1.setText(i.getStringExtra("Title"))
        binding.edt2.setText(i.getStringExtra("Description"))

        //update click event

        binding.update.setOnClickListener {
            var title=binding.edt1.text.toString()
            var des=binding.edt2.text.toString()

            //Model class

            var update=Model()

            update.id=id
            update.title=title
            update.description=des

            var id=dbHelper.updatedata(update)
            startActivity(Intent(applicationContext,Addstickey::class.java))
        }
    }
}