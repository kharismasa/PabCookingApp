package com.example.latihanlogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class HalamanAwal : AppCompatActivity() {

    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_awal)

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@HalamanAwal, HalamanLogin::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}
