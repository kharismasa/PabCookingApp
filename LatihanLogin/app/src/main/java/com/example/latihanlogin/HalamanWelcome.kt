package com.example.latihanlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class HalamanWelcome : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_welcome)

        // Hide the action bar
        supportActionBar?.hide()

        // Inisialisasi database Firebase
        databaseReference = FirebaseDatabase.getInstance().reference

        val userId = "Kharisma"  // Gantilah dengan UID pengguna yang sesuai

        // TextView untuk menampilkan preferensi alergi dan dislike
        val textViewAlergi = findViewById<TextView>(R.id.textViewAlergi)
        val textViewDislike = findViewById<TextView>(R.id.textViewDislike)

        // Ambil data preferensi alergi dari Firebase
        databaseReference.child("users").child(userId).child("preferensi alergi")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val alergiList = snapshot.children.map { it.key }
                        // Tampilkan preferensi alergi
                        textViewAlergi.text = "Preferensi Alergi: ${alergiList.joinToString(", ")}"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle kesalahan jika diperlukan
                }
            })

        // Ambil data preferensi dislike dari Firebase
        databaseReference.child("users").child(userId).child("preferensi dislike")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val dislikeList = snapshot.children.map { it.key }
                        // Tampilkan preferensi dislike
                        textViewDislike.text = "Preferensi Dislike: ${dislikeList.joinToString(", ")}"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle kesalahan jika diperlukan
                }
            })

        // Button untuk beralih ke halaman menu utama
//        val btnNextMenu = findViewById<Button>(R.id.btnNextMenu)
//        btnNextMenu.setOnClickListener {
//            val intent = Intent(this, MainMenu::class.java)
//            startActivity(intent)
//        }
    }
}

