package com.example.retrofitcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitcrud.databinding.ActivityViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class View_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBinding
    lateinit var apiinterface: Apiinterface
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityViewBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        apiinterface=Apiclint.getapiclient().create(Apiinterface::class.java)

        list=ArrayList()

        var call:Call<List<Model>> =apiinterface.viewdata()
        call.enqueue(object :Callback<List<Model>>{
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                list=response.body() as MutableList<Model>

                var adapter=MyAdapter(applicationContext,list)
                binding.recycle.adapter=adapter
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}