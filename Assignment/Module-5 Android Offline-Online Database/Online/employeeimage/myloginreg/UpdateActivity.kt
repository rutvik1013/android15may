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
import com.example.myloginreg.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        var i=intent
        var id=i.getStringExtra("id")
        var efirstname=i.getStringExtra("efirstname")
        var elastname=i.getStringExtra("elastname")
        var e_experience=i.getStringExtra("e_experience")
        var email=i.getStringExtra("email")
        var password=i.getStringExtra("password")
        var mobile=i.getStringExtra("mobile")

        binding.btnupdate.setOnClickListener {
            var efname=binding.edtfname.text.toString()
            var elname=binding.edtlname.text.toString()
            var experience=binding.edtexperience.text.toString()
            var e_email=binding.edtemail.text.toString()
            var pass=binding.edtpassword.text.toString()
            var phone=binding.edtphone.text.toString()

            var stringrequest:StringRequest=object :StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API2/update_emp_details.php",Response.Listener {
                Toast.makeText(applicationContext, "Project Details updated", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext,ViewActivity::class.java))
            },Response.ErrorListener {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            })
            {
                override fun getParams(): MutableMap<String, String>? {
                    var map=HashMap<String,String>()

                    map["id"]=id.toString()
                    map["efirstname"]=efname
                    map["elastname"]=elname
                    map["e_experience"]=experience
                    map["email"]=e_email
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