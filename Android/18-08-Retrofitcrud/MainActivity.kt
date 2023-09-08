package com.example.retrofitcrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitcrud.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var apiinterface: Apiinterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        apiinterface=Apiclint.getapiclient().create(Apiinterface::class.java)

        binding.btnenter.setOnClickListener {
            var fullname=binding.edtfullname.text.toString()
            var email=binding.edtemail.text.toString()
            var password=binding.edtpassword.text.toString()

            var call:Call<Void> =apiinterface.insertdata(fullname,email,password)
            call.enqueue(object :Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "Inserted record", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,View_Activity::class.java))
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }
}