package com.example.latihanlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HalamanPreferensiDislike : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_preferensi_dislike)

        // Hide the action bar
        supportActionBar?.hide()

        // Inisialisasi database Firebase
        databaseReference = FirebaseDatabase.getInstance().reference

        val cardAlcohol = findViewById<CardView>(R.id.cardAlcohol)
        val cardAvocado = findViewById<CardView>(R.id.cardAvocado)
        val cardBeef = findViewById<CardView>(R.id.cardBeef)
        val cardFish = findViewById<CardView>(R.id.cardFish)
        val cardMayonnaise = findViewById<CardView>(R.id.cardMayonnaise)
        val cardOnion = findViewById<CardView>(R.id.cardOnion)
        val cardPork = findViewById<CardView>(R.id.cardPork)
        val cardPotato = findViewById<CardView>(R.id.cardPotato)
        val cardSeafood = findViewById<CardView>(R.id.cardSeafood)
        val cardShrimp = findViewById<CardView>(R.id.cardShrimp)

        // Variabel untuk menyimpan status pilihan CardView
        val selectedDislikes = mutableSetOf<String>()
        val userId: String = "Kharisma"

        cardAlcohol.setOnClickListener {
            if (!selectedDislikes.contains("Alcohol")) {
                saveDislikeToFirebase("Alcohol", userId)
                cardAlcohol.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Alcohol")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Alcohol").removeValue()
                cardAlcohol.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Alcohol")
            }
        }
        cardAvocado.setOnClickListener {
            if (!selectedDislikes.contains("Avocado")) {
                saveDislikeToFirebase("Avocado", userId)
                cardAvocado.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Avocado")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Avocado").removeValue()
                cardAvocado.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Avocado")
            }
        }

        cardBeef.setOnClickListener {
            if (!selectedDislikes.contains("Beef")) {
                saveDislikeToFirebase("Beef", userId)
                cardBeef.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Beef")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Beef").removeValue()
                cardBeef.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Beef")
            }
        }
        cardFish.setOnClickListener {
            if (!selectedDislikes.contains("Fish")) {
                saveDislikeToFirebase("Fish", userId)
                cardFish.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Fish")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Fish").removeValue()
                cardFish.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Fish")
            }
        }
        cardMayonnaise.setOnClickListener {
            if (!selectedDislikes.contains("Mayonnaise")) {
                saveDislikeToFirebase("Mayonnaise", userId)
                cardMayonnaise.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Mayonnaise")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Mayonnaise").removeValue()
                cardMayonnaise.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Mayonnaise")
            }
        }

        cardOnion.setOnClickListener {
            if (!selectedDislikes.contains("Onion")) {
                saveDislikeToFirebase("Onion", userId)
                cardOnion.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Onion")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Onion").removeValue()
                cardOnion.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Onion")
            }
        }

        cardPork.setOnClickListener {
            if (!selectedDislikes.contains("Pork")) {
                saveDislikeToFirebase("Pork", userId)
                cardPork.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Pork")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Pork").removeValue()
                cardPork.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Pork")
            }
        }

        cardPotato.setOnClickListener {
            if (!selectedDislikes.contains("Potato")) {
                saveDislikeToFirebase("Potato", userId)
                cardPotato.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Potato")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Potato").removeValue()
                cardPotato.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Potato")
            }
        }

        cardSeafood.setOnClickListener {
            if (!selectedDislikes.contains("Seafood")) {
                saveDislikeToFirebase("Seafood", userId)
                cardSeafood.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Seafood")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Seafood").removeValue()
                cardSeafood.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Seafood")
            }
        }

        cardShrimp.setOnClickListener {
            if (!selectedDislikes.contains("Shrimp")) {
                saveDislikeToFirebase("Shrimp", userId)
                cardShrimp.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected))
                selectedDislikes.add("Shrimp")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferensi dislike").child("Shrimp").removeValue()
                cardShrimp.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedDislikes.remove("Shrimp")
            }
        }

        val btnNextSubmit = findViewById<Button>(R.id.btnNextHome)
        btnNextSubmit.setOnClickListener {
            // Simpan semua preferensi dislike yang dipilih
            for (dislike in selectedDislikes) {
                saveDislikeToFirebase(dislike, userId)
            }

            // Pindah ke halaman berikutnya (misalnya HalamanWelcome)
            val intent = Intent(this, HalamanWelcome::class.java)
            startActivity(intent)
        }
    }

    private fun saveDislikeToFirebase(dislike: String, userId: String) {
        // Pastikan UID tidak null sebelum menyimpan data
        userId.let {
            // Simpan data ke Firebase
            databaseReference.child("users").child(it).child("preferensi dislike").child(dislike).setValue(true)
        }
    }
}
