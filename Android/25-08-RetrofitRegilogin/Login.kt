package com.example.retrofitregastrationlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitregastrationlogin.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var apiinterface: Apiinterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        apiinterface=ApiClient.getapiclient().create(Apiinterface::class.java)
        binding.Login.setOnClickListener {
           var email=binding.edt2.text.toString()
            var password=binding.edt3.text.toString()

            var call:Call<Model> =apiinterface.login(email, password)

            call.enqueue(object :Callback<Model>{
                override fun onResponse(call: Call<Model>, response: Response<Model>) {
                    Toast.makeText(applicationContext, "Login", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Model>, t: Throwable) {
                    Toast.makeText(applicationContext, "Login unsuccesful", Toast.LENGTH_SHORT).show()
                }

            })
        }
        binding.forget.setOnClickListener {
            startActivity(Intent(applicationContext,Firebase::class.java))

        }
    }
}