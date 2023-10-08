package com.example.musicassign

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast

import com.example.musicassign.databinding.ActivityTexttospeechBinding
import java.util.Locale

class Texttospeech : AppCompatActivity() {
    var textToSpeech: TextToSpeech? = null
    private lateinit var binding: ActivityTexttospeechBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTexttospeechBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        textToSpeech = TextToSpeech(applicationContext) { i ->
            // if No error is found then only it will run
            if (i != TextToSpeech.ERROR) {
                // To Choose language of speech
                textToSpeech!!.setLanguage(Locale.UK)
            }
        }

        binding.btnText.setOnClickListener {
            val toSpeak: String = binding.Text.getText().toString()

            Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
            textToSpeech!!.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}
