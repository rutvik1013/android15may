package com.example.modulefour

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.modulefour.databinding.ActivityCheckboxTextviewBinding

class checkbox_textview : AppCompatActivity() {
 lateinit var c1:CheckBox
 lateinit var c2:CheckBox
 lateinit var textView: TextView


  @SuppressLint("MissingInflatedId")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_checkbox_textview)

    c1=findViewById(R.id.chk1)
    c2=findViewById(R.id.chk2)
    textView=findViewById(R.id.text)


    c1.setOnCheckedChangeListener { compoundButton, b ->
      if (c1.isChecked)
      {
        Toast.makeText(applicationContext, "Chacked", Toast.LENGTH_SHORT).show()
        textView.visibility=View.VISIBLE
      }
      else if (c2.isChecked)
      {
        Toast.makeText(applicationContext, "UnChacked", Toast.LENGTH_SHORT).show()
        textView.visibility=View.INVISIBLE
      }
    }
  }
}
