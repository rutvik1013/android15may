package com.example.modulefour

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class googleurl : AppCompatActivity() {

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_googleurl)

        webView = findViewById(R.id.webview)

        webView.loadUrl("https://www.google.co.in/")
        webView.settings.javaScriptEnabled = true


    }

    override fun onBackPressed() {

        if (webView.canGoBack()) {
            
            webView.goBack()
        } else {
            super.onBackPressed()
        }

    }
}

