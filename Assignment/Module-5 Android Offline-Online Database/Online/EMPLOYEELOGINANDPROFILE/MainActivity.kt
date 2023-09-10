package com.example.empjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.empjson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //button click event for inserted employee data for the next activity

        binding.enter.setOnClickListener {

            var email=binding.edtempemail.text.toString()
            var pass=binding.edtemppassword.text.toString()

            var stringrequest:StringRequest=object :StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API2/insert_assignment.php",Response.Listener {
                Toast.makeText(applicationContext, "Employee Data Inserted", Toast.LENGTH_SHORT).show()

                startActivity(Intent(applicationContext,ViewActivity::class.java))
            },Response.ErrorListener {
                Toast.makeText(applicationContext, "Not Connected", Toast.LENGTH_SHORT).show()

            })
            {
                override fun getParams(): MutableMap<String, String>? {
                    var map=HashMap<String,String>()
                    map["email"]=email
                    map["password"]=pass

                    return map
                }
            }
            var queue:RequestQueue=Volley.newRequestQueue(this)
            queue.add(stringrequest)
        }

    }
}