package com.example.musicassign

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.view.WindowManager
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import java.lang.Exception

class WakelockVideo : AppCompatActivity() {
    private lateinit var videoView: VideoView
    var url="https://rutvikbabariya1013.000webhostapp.com/Api/movie.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wakelock_video)



        videoView=findViewById(R.id.videoView)
        try {
            var mediaController=MediaController(this)
            mediaController.setAnchorView(videoView)
            var video:Uri= Uri.parse(url)
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(video)
            videoView.start()

        }catch (e:Exception)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }


    }
}