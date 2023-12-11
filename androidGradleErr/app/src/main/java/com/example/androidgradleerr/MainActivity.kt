package com.example.androidgradleerr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BdietaryPreference = findViewById<Button>(R.id.BdietaryPreference)
        BdietaryPreference.setOnClickListener {
            Toast.makeText(this,"Dietary Preference Clicked", Toast.LENGTH_SHORT).show()
            val Intent = Intent(this,dietary_preferences::class.java)
            startActivity(Intent)
        }

        val BaboutYum = findViewById<Button>(R.id.BaboutYum)
        BdietaryPreference.setOnClickListener {
            Toast.makeText(this,"About Clicked", Toast.LENGTH_SHORT).show()
            val Intent = Intent(this,about::class.java)
            startActivity(Intent)
        }

    }
}
