package com.example.modulefour

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class googleurl : AppCompatActivity() {
    lateinit var back:Button
    lateinit var forward:Button
    lateinit var web:WebView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_googleurl)

        back=findViewById(R.id.back)
        forward=findViewById(R.id.forward)
        web=findViewById(R.id.web)

        web.webViewClient= WebViewClient()

        web.loadUrl("https://www.google.com/")

        web.settings.javaScriptEnabled=true


        back.setOnClickListener {
            if (web.canGoBack())
                web.goBack()
        }
        forward.setOnClickListener {
            if (web.canGoForward())
                web.goForward()
        }
    }
    }
