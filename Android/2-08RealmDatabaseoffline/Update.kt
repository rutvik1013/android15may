package com.example.realm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.realm.databinding.ActivityUpdateBinding
import io.realm.Realm

class Update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

    }
}