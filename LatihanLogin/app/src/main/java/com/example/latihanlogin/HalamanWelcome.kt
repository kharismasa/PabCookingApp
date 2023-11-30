package com.example.latihanlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HalamanWelcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_welcome)

        val textViewWelcome = findViewById<TextView>(R.id.tvUsername)


        val intent = intent
        if (intent != null) {
            val username = intent.getStringExtra("username")
            textViewWelcome.text = "Welcome $username"
        }

    }

}