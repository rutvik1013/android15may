package com.example.retrofitregastrationlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitregastrationlogin.databinding.ActivityMainBinding
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
        apiinterface=ApiClient.getapiclient().create(Apiinterface::class.java)

        binding.registration.setOnClickListener {
            var name=binding.edt1.text.toString()
            var email=binding.edt2.text.toString()
            var password=binding.edt3.text.toString()

            var call:Call<Void> =apiinterface.register(name, email, password)

            call.enqueue(object :Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "Registration Completed", Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Registration Error", Toast.LENGTH_SHORT).show()
                }

            })
        }
        binding.txtpassword.setOnClickListener {
            startActivity(Intent(applicationContext,Login::class.java))
        }
    }
}