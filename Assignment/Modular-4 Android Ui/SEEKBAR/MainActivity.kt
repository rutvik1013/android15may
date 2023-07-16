package com.example.seekbar

import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.seekbar.databinding.ActivityMainBinding

class MainActivity :AppCompatActivity(),SeekBar.OnSeekBarChangeListener
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.seek1.setOnSeekBarChangeListener(this)
        binding.seek2.setOnSeekBarChangeListener(this)
        binding.seek3.setOnSeekBarChangeListener(this)

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        var r=binding.seek1.progress
        var g=binding.seek2.progress
        var b=binding.seek3.progress

        binding.img.setBackgroundColor(Color.rgb(r,g,b))
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }

}