package com.example.jsonpersingcrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jsonpersingcrud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.enter.setOnClickListener {

            var pname = binding.edtname.text.toString()
            var pprice = binding.edtprice.text.toString()
            var pdes = binding.edtdes.text.toString()

            var stringrequest:StringRequest = object :StringRequest(Request.Method.POST,"https://immitigable-weeks.000webhostapp.com/API/insert.php",
                Response.Listener {

                    Toast.makeText(applicationContext,"Insert Data",Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext,MainActivity2::class.java))

                },Response.ErrorListener {

                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()

                })


            {
                override fun getParams(): MutableMap<String, String>?
                {
                    var map = HashMap<String,String>()
                    map["p_name"]=pname
                    map["p_price"]=pprice
                    map["p_des"]=pdes
                    return map
                }

            }
            var queue:RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringrequest)



        }


    }
}