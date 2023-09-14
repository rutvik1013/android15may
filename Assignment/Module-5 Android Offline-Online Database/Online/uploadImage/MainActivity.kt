package com.example.empprofile


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.empprofile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //view binding

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        sharedPreferences=getSharedPreferences("USER_SESSION",Context.MODE_PRIVATE)

        //register button click event

        binding.registration.setOnClickListener {
            var efirstname=binding.edtfname.text.toString()
            var elastname=binding.edtlname.text.toString()
            var experience=binding.edtexperience.text.toString()
            var email=binding.edtemail.text.toString()
            var password=binding.edtpassword.text.toString()
            var mobile=binding.edtphone.text.toString()

            if(efirstname.length==0&&elastname.length==0&&experience.length==0&&email.length==0&&password.length==0&&mobile.length==0)
            {
                binding.edtfname.setError("Please enter first Name")
                binding.edtlname.setError("Please enter last Name")
                binding.edtexperience.setError("Please enter experience")
                binding.edtemail.setText("Please enter email")
                binding.edtpassword.setText("Please enter password")
                binding.edtphone.setText("Please enter mobile number")
            }
            else if (efirstname.length==0) {
                binding.edtfname.setText("Please enter first name")
            }
            else if (elastname.length==0)
            {
                binding.edtlname.setText("Please enter last name")
            }
            else if (experience.length==0)
            {
                binding.edtexperience.setText("Please enter experince")
            }
            else if(email.length==0)
            {
                binding.edtemail.setText("Please enter email ")
            }
            else if (password.length==0)
            {
                binding.edtpassword.setText("Please enter password")
            }
            else if(mobile.length==0)
            {
                binding.edtphone.setText("Please enter mobile number")
            }
            else
            {
                var stringrequest: StringRequest=object :StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API2/register.php",Response.Listener {
                    Toast.makeText(applicationContext, "Registration Successfully", Toast.LENGTH_SHORT).show()
                    var i= Intent(applicationContext,LoginActivity::class.java)
                    var sharedPreferences:SharedPreferences.Editor=sharedPreferences.edit()
                    sharedPreferences.putBoolean("USER_SESSION",true)
                    sharedPreferences.putString("email",email)
                    sharedPreferences.commit()
                    startActivity(i)
                },Response.ErrorListener {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                })
                {
                    override fun getParams(): MutableMap<String, String>? {
                        var map = HashMap<String, String>()
                        map["efirstname"]=efirstname
                        map["elastname"]=elastname
                        map["e_experince"]=experience
                        map["email"]=email
                        map["password"]=password
                        map["mobile"]=mobile

                        return map
                    }
                }
                var queue: RequestQueue= Volley.newRequestQueue(this)
                queue.add(stringrequest)
            }
            //login procees if user have already user
            binding.txt.setOnClickListener {
                startActivity(Intent(applicationContext,LoginActivity::class.java))
            }
        }
    }
}