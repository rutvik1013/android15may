package com.example.modulefour

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class pdf : AppCompatActivity() {

   lateinit var click:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        click=findViewById(R.id.pdf)

        click.setOnClickListener{
            var  url=("https://developer.android.com/reference/android/graphics/pdf/PdfDocument")

            var i= Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }
    }
}