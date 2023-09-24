package com.example.myloginreg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myloginreg.databinding.ActivityInsertBinding
import com.example.myloginreg.databinding.ActivityMainBinding

class insertActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInsertBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.btninsert.setOnClickListener {
            var fname=binding.edtfname.text.toString()
            var lname=binding.edtlname.text.toString()
            var experience=binding.edtexperience.text.toString()
            var email=binding.edtemail.text.toString()
            var pass=binding.edtpassword.text.toString()
            var phone=binding.edtphone.text.toString()

            var stringrequest:StringRequest=object :StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API2/insert_emp_details.php",Response.Listener {
                Toast.makeText(applicationContext, "Details Inserted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext,ViewActivity::class.java))
            },Response.ErrorListener {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            })
            {
                override fun getParams(): MutableMap<String, String>? {
                    var map=HashMap<String,String>()
                    map["efirstname"]=fname
                    map["elastname"]=lname
                    map["e_experience"]=experience
                    map["email"]=email
                    map["password"]=pass
                    map["mobile"]=phone

                    return map
                }

            }
            var queue:RequestQueue=Volley.newRequestQueue(this)
            queue.add(stringrequest)
        }
    }
}