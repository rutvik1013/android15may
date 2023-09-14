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
import com.example.empjson.databinding.ActivityUpdateBinding
//update activity for update data

class UpdateActivity : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        var i=intent
        var id=i.getIntExtra("id",102)
        var email=i.getStringExtra("email")
        var pass=i.getStringExtra("password")

        binding.edtempemail.setText(email)
        binding.edtemppassword.setText(pass)

        binding.enter.setOnClickListener {

            var email=binding.edtempemail.text.toString()
            var pass=binding.edtemppassword.text.toString()

            var stringrequest: StringRequest =object :StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API2/update_assignment.php",Response.Listener {
                Toast.makeText(applicationContext, "Updated Employee Data", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext,ViewActivity::class.java))
            }, Response.ErrorListener {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            })
            {
                override fun getParams(): MutableMap<String, String>? {
                    var map=HashMap<String,String>()
                    map["id"]=id.toString()
                    map["email"]=email
                    map["password"]=pass

                    return map
                }
            }
            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringrequest)
        }
    }
}