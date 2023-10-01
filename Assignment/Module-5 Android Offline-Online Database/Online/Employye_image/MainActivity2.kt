package com.example.empassign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.empassign.databinding.ActivityMain2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    lateinit var list: MutableList<Model>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        list=ArrayList()

        binding.recycle.layoutManager=LinearLayoutManager(this)

        var retrofit=Retrofit.Builder().baseUrl("https://rutvikbabariya.000webhostapp.com/API2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            var call:Call<List<Model>> =retrofit.getdata()
            call.enqueue(object :Callback<List<Model>>{
                override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                    list=response.body() as MutableList<Model>

                    var  adapter=Myadapter(applicationContext,list)
                    binding.recycle.adapter=adapter

                }

                override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}