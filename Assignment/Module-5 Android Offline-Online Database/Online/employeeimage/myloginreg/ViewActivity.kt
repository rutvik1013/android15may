package com.example.myloginreg

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myloginreg.databinding.ActivityViewBinding
import org.json.JSONArray

class ViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBinding
    lateinit var list: MutableList<EmployeeModel>
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityViewBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        sharedPreferences=getSharedPreferences("SESSION",Context.MODE_PRIVATE)

        binding.f1.setOnClickListener {
            startActivity(Intent(applicationContext,Myprofile::class.java))
            finish()
        }

        var layoutmanager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        binding.recycle.layoutManager=layoutmanager

        list=ArrayList()

        var stringrequest=StringRequest(Request.Method.GET,"https://rutvikbabariya.000webhostapp.com/API2/view_emp_details.php",object :Response.Listener<String>{
            override fun onResponse(response: String?) {
                var jsonArray=JSONArray(response)
                for (i in 0 until jsonArray.length())
                {
                    var jsonObject=jsonArray.getJSONObject(i)

                    var id=jsonObject.getString("id")
                    var efirstname=jsonObject.getString("efirstname")
                    var elastname=jsonObject.getString("elastname")
                    var e_experience=jsonObject.getString("e_experience")
                    var email=jsonObject.getString("email")
                    var password=jsonObject.getString("password")
                    var mobile=jsonObject.getString("mobile")
                    var image=jsonObject.getString("image")

                    var m=EmployeeModel()

                    m.id=id
                    m.efirstname=efirstname
                    m.elastname=elastname
                    m.e_experience=e_experience
                    m.email=email
                    m.password=password
                    m.mobile=mobile
                    m.image=image


                    list.add(m)
                }
                var adapter=MyProjectAdapter(applicationContext,list)
                binding.recycle.adapter=adapter

            }

        },object:Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }

        })
        var queue:RequestQueue=Volley.newRequestQueue(this)
        queue.add(stringrequest)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout -> {
                sharedPreferences.edit().clear().apply()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}