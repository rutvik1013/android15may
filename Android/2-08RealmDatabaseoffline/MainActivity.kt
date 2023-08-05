package com.example.realm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realm.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        realm=Realm.getInstance(Realm.getDefaultConfiguration())

        binding.insert.setOnClickListener {
            var name=binding.edt1.text.toString()
            var num=binding.edt2.text.toString()

            realm.beginTransaction()

            var m=realm.createObject(Model::class.java)
            m.name=name
            m.num=num

            realm.commitTransaction()
            Toast.makeText(applicationContext, "Insert Data", Toast.LENGTH_SHORT).show()
        }
        binding.view.setOnClickListener {
            startActivity(Intent(applicationContext,MainActivity2::class.java))
        }
    }
}