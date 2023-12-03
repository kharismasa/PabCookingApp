package com.example.testsekalilagi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val signOutButton: Button = findViewById(R.id.btnSignOut)
        signOutButton.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        auth.signOut()

        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}