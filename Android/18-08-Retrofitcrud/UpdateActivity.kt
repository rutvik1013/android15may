package com.example.retrofitcrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitcrud.databinding.ActivityUpdateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    lateinit var apiinterface: Apiinterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        apiinterface=Apiclint.getapiclient().create(Apiinterface::class.java)

        var i=intent
        var id=i.getIntExtra("id",101)
        binding.edtfullname.setText(i.getStringExtra("fullname"))
        binding.edtemail.setText(i.getStringExtra("email"))
        binding.edtpassword.setText(i.getStringExtra("password"))

        binding.btnupdate.setOnClickListener {
            var fullname=binding.edtfullname.text.toString()
            var email=binding.edtemail.text.toString()
            var password=binding.edtpassword.text.toString()

            var call:Call<Void> =apiinterface.updatedata(id,fullname,email,password)
            call.enqueue(object :Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "Updated Record", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,View_Activity::class.java))
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}