package com.example.androidgradleerr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class dietary_preferences : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dietary_preferences)

        val tambahBahanTidakDisuka = findViewById<Button>(R.id.tambahBahanTidakDisuka)
        tambahBahanTidakDisuka.setOnClickListener {
            Toast.makeText(this,"Tambah Bahan Tidak Disuka Clicked", Toast.LENGTH_SHORT).show()
            val Intent = Intent(this,dietary_preferences::class.java)
            startActivity(Intent)
        }

        val tambahBahanAlergi = findViewById<Button>(R.id.tambahBahanAlergi)
        tambahBahanAlergi.setOnClickListener {
            Toast.makeText(this,"Tambah Bahan Alergi Clicked", Toast.LENGTH_SHORT).show()
            val Intent = Intent(this,dietary_preferences::class.java)
            startActivity(Intent)
        }
    }
}