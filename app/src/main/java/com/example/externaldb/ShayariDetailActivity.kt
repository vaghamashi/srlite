package com.example.externaldb

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.externaldb.databinding.ActivityShayariDetailBinding
import java.util.Locale
import kotlin.text.Typography.quote

class ShayariDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityShayariDetailBinding
    lateinit var textToSpeech : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShayariDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var shayari = intent.getStringExtra("shayari")

        binding.tvShayariDetail.text = shayari

        binding.shareText.setOnClickListener {

            val s = binding.tvShayariDetail.text.toString()
            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s);
            startActivity(Intent.createChooser(shareIntent,"Share via"))
        }
        binding.back.setOnClickListener {
            finish()
        }

        textToSpeech = TextToSpeech(this) { i ->
            if (i != TextToSpeech.ERROR) {
                textToSpeech.language = Locale("hi", "in")
            }
        }

        binding.speakText.setOnClickListener(View.OnClickListener {
            textToSpeech.speak(
                shayari, TextToSpeech.QUEUE_FLUSH, null
            )
        })

    }
}