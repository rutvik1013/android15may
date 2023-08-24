package com.example.employeelogin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.employeelogin.databinding.ActivityDashboardBinding
//for viewing perpose for this activity
class dashboard : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityDashboardBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDashboardBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        sharedPreferences=getSharedPreferences("USER_SESSION",Context.MODE_PRIVATE)

        var name=sharedPreferences.getString("email","")
        binding.txtname.setText(name)

        // if user want to logout
        binding.logout.setOnClickListener {
            sharedPreferences.edit().clear().commit()
            finish()

            var i=Intent(applicationContext,LoginActivity::class.java)
            startActivity(i)
        }
    }
}