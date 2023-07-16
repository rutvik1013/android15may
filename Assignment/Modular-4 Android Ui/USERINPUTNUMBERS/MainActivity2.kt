package com.example.inputnumbers

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    lateinit var txt:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        txt=findViewById(R.id.txt1)

        var i=intent

        var array=ArrayList<Int>()

        var start=i.getStringExtra("Start")
        var end=i.getStringExtra("End")

        var a=start.toString().toInt()
        var b=end.toString().toInt()

        for (j in 0..b-a)
        {
            array.add(a)
            a++
        }
        Toast.makeText(this, "${array[0]}", Toast.LENGTH_SHORT).show()
        txt.setText(array.toString())
    }
}