package com.example.musicassign


import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.musicassign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        binding.playbutton.setOnClickListener {
            startAudio()
            binding.playbutton.visibility= View.GONE
            binding.pausebutton.visibility= View.VISIBLE
        }
        binding.pausebutton.setOnClickListener {
            pauseAudio()
            binding.playbutton.visibility= View.VISIBLE
            binding.pausebutton.visibility= View.GONE
        }

    }
    private fun startAudio()
    {
        mediaPlayer= MediaPlayer.create(this,R.raw.fearless)
        mediaPlayer?.start()
    }
    private fun pauseAudio()
    {

        mediaPlayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()


    }
}