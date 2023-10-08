package com.example.musicassign


import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.musicassign.databinding.ActivitySearverSongBinding
import java.io.IOException


class SearverSong :AppCompatActivity() {
    private lateinit var binding: ActivitySearverSongBinding
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearverSongBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        mediaPlayer = MediaPlayer()

        binding.idIBPlay.setOnClickListener {
            var url = "https://rutvikbabariya.000webhostapp.com/API2/fearless.mp3"
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            // on below line we are running a try
            // and catch block for our media player.

            try {
                mediaPlayer.setDataSource(url)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: Exception) {

                // on below line we are handling our exception.
                e.printStackTrace()
            }
            // on below line we are displaying a toast message as audio player.
            Toast.makeText(applicationContext, "Audio started playing..", Toast.LENGTH_SHORT).show()

            }
        binding.idIBPause.setOnClickListener {
            if (mediaPlayer.isPlaying)
            {
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                Toast.makeText(applicationContext, "Audio has been  paused", Toast.LENGTH_SHORT).show()

            }
            else
            {
                Toast.makeText(applicationContext, "Audio not played", Toast.LENGTH_SHORT).show()
            }


        }

    }
}