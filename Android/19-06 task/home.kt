package com.example.foodapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class home:AppCompatActivity()
{
    lateinit var call:ImageView
    lateinit var txt:TextView
    lateinit var c1:CheckBox
    lateinit var c2:CheckBox
    lateinit var c3:CheckBox
    lateinit var btn1:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        call=findViewById(R.id.call)
        txt=findViewById(R.id.txt1)
        c1=findViewById(R.id.ch1)
        c2=findViewById(R.id.ch2)
        c3=findViewById(R.id.ch3)
        btn1=findViewById(R.id.btn1)

        call.setOnClickListener {
            var  num="6351202084"
            var i=Intent(Intent.ACTION_CALL)
            startActivity(i)
        }
        btn1.setOnClickListener {
            var builder=StringBuilder("\n Selected Items:\n")
            var amount=0

            if (c1.isChecked)
            {
                builder.append("\n Pizza @ Rs.100")
                amount+=100

            }
            if (c2.isChecked)
            {
                builder.append("\n Burger @ Rs.70")
                amount+=70
            }
            if (c3.isChecked)
            {
                builder.append("\n Coffee @ Rs.50")
                amount+=50
            }
            builder.append("-----------------------------------------")
            builder.append("\n Total:"+amount)

            /* Toast.makeText(applicationContext,""+builder.toString(),Toast.LENGTH_LONG).show()
            Log.d("BILL",builder.toString())*/

            var i=Intent(applicationContext,Bill::class.java)
            i.putExtra("BILL",builder.toString())
            startActivity(i)
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
