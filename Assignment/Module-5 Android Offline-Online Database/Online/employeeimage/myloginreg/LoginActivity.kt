package com.example.myloginreg

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myloginreg.databinding.ActivityLoginBinding
import org.json.JSONException


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        sharedPreferences=getSharedPreferences("USER_SESSION",Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("USER_SESSION",false)&&!sharedPreferences.getString("email","")!!.isEmpty())
        {
            startActivity(Intent(applicationContext,insertActivity::class.java))
            finish()
        }
        //login click event

        binding.login.setOnClickListener {
            var email=binding.edtemail.text.toString()
            var password=binding.edtpassword.text.toString()

            if (email.length==0&&password.length==0)
            {
                binding.edtemail.setText("Please enter your password")
                binding.edtpassword.setText("Please enter email password")
            }
            else if (email.length==0)
            {
                binding.edtemail.setText("Please enter your email")
            }
            else if (password.length==0)
            {
                binding.edtpassword.setText("Please enter email password")
            }
            else {
                var stringrequest: StringRequest = object : StringRequest(Request.Method.POST,
                    "https://rutvikbabariya.000webhostapp.com/API2/login.php",
                    { response ->
                        try {
                            if (response.trim().equals("0")) {
                                Toast.makeText(
                                    applicationContext,
                                    "Login Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                var i = Intent(applicationContext, insertActivity::class.java)
                                var sharedPreferences: SharedPreferences.Editor =
                                    sharedPreferences.edit()
                                sharedPreferences.putBoolean("USER_SESSION", true)
                                sharedPreferences.putString("email",email)
                                sharedPreferences.commit()
                                startActivity(i)
                            } else {
                                Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_SHORT).show()
                            }

                        } catch (e: JSONException) {
                            print(e)
                        }

                    },
                    {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    })
                {
                    override fun getParams(): MutableMap<String, String>? {
                        var map=HashMap<String,String>()
                        map["email"]=email
                        map["password"]=password

                        return map
                    }
                }
                var queue: RequestQueue= Volley.newRequestQueue(this)
                queue.add(stringrequest)
            }

        }


    }
}
