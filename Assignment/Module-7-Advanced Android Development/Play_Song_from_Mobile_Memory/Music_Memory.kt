package com.example.musicassign

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.musicassign.databinding.ActivityMusicMemoryBinding


class Music_Memory: AppCompatActivity() {
    private lateinit var binding:ActivityMusicMemoryBinding
    lateinit var mediaPlayer: MediaPlayer

    companion object {
        private const val REQUEST_PERMISSION = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicMemoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.selectSongButton.setOnClickListener { checkPermissionsAndOpenFileChooser() }

    }

    private fun checkPermissionsAndOpenFileChooser() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION
            )

        } else {
            openFileChooser()
        }

    }

    private fun openFileChooser() {
        var i = Intent(Intent.ACTION_OPEN_DOCUMENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "audio/*"

        startActivityForResult(i, REQUEST_PERMISSION)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFileChooser()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK)
            if (requestCode == REQUEST_PERMISSION) {
                var selectedsonguri = data?.data
                selectedsonguri?.let { startPlayingSong(it) }
            }
    }

    private fun startPlayingSong(songUri: Uri) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(this, songUri)
        mediaPlayer.prepare()
        mediaPlayer.start()

        binding.seekBar.max = mediaPlayer.duration

        mediaPlayer.setOnCompletionListener {
            // Release the MediaPlayer when the song is finished.
            mediaPlayer.release()
        }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }


        })
        // Update the seekBar progress while playing the song
        Thread(Runnable {
            while (mediaPlayer != null) {
                try {
                    var currentposition = mediaPlayer.currentPosition
                    runOnUiThread {
                        binding.seekBar.progress = currentposition

                    }
                    Thread.sleep(1000)

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}