package com.example.latihanlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HalamanPreferensiAlergi : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_preferensi_alergi)

        // Inisialisasi database Firebase
        databaseReference = FirebaseDatabase.getInstance().reference

        val cardDiary = findViewById<CardView>(R.id.cardDiary)
        val cardEgg = findViewById<CardView>(R.id.cardEgg)
        val cardGluten = findViewById<CardView>(R.id.cardGluten)
        val cardPeanut = findViewById<CardView>(R.id.cardPeanut)
        val cardSeafood = findViewById<CardView>(R.id.cardSeafood)
        val cardSesame = findViewById<CardView>(R.id.cardSesame)
        val cardSoy = findViewById<CardView>(R.id.cardSoy)
        val cardSulfite = findViewById<CardView>(R.id.cardSulfite)
        val cardTreenut = findViewById<CardView>(R.id.cardTreenut)
        val cardWheat = findViewById<CardView>(R.id.cardWheat)

        // Variabel untuk menyimpan status pilihan CardView
        val selectedPreferences = mutableSetOf<String>()
        val userId: String = "Kharisma"

        cardDiary.setOnClickListener {
            if (!selectedPreferences.contains("Diary")) {
                savePreferenceToFirebase("Diary", userId)
                cardDiary.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Diary")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Diary").removeValue()
                cardDiary.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Diary")
            }
        }
        cardEgg.setOnClickListener {
            if (!selectedPreferences.contains("Egg")) {
                savePreferenceToFirebase("Egg", userId)
                cardEgg.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Egg")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Egg").removeValue()
                cardEgg.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Egg")
            }
        }
        cardGluten.setOnClickListener {
            if (!selectedPreferences.contains("Gluten")) {
                savePreferenceToFirebase("Gluten", userId)
                cardGluten.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Gluten")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Gluten").removeValue()
                cardGluten.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Gluten")
            }
        }
        cardPeanut.setOnClickListener {
            if (!selectedPreferences.contains("Peanut")) {
                savePreferenceToFirebase("Peanut", userId)
                cardPeanut.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Peanut")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Peanut").removeValue()
                cardPeanut.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Peanut")
            }
        }
        cardSesame.setOnClickListener {
            if (!selectedPreferences.contains("Sesame")) {
                savePreferenceToFirebase("Sesame", userId)
                cardSesame.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Sesame")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Sesame").removeValue()
                cardSesame.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Sesame")
            }
        }
        cardSoy.setOnClickListener {
            if (!selectedPreferences.contains("Soy")) {
                savePreferenceToFirebase("Soy", userId)
                cardSoy.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Soy")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Soy").removeValue()
                cardSoy.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Soy")
            }
        }
        cardSulfite.setOnClickListener {
            if (!selectedPreferences.contains("Sulfite")) {
                savePreferenceToFirebase("Sulfite", userId)
                cardSulfite.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Sulfite")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Sulfite").removeValue()
                cardSulfite.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Sulfite")
            }
        }
        cardTreenut.setOnClickListener {
            if (!selectedPreferences.contains("Treenut")) {
                savePreferenceToFirebase("Treenut", userId)
                cardTreenut.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Treenut")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Treenut").removeValue()
                cardTreenut.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Treenut")
            }
        }
        cardWheat.setOnClickListener {
            if (!selectedPreferences.contains("Wheat")) {
                savePreferenceToFirebase("Wheat", userId)
                cardWheat.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Wheat")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Wheat").removeValue()
                cardWheat.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Wheat")
            }
        }
        cardSeafood.setOnClickListener {
            if (!selectedPreferences.contains("Seafood")) {
                savePreferenceToFirebase("Seafood", userId)
                cardSeafood.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                selectedPreferences.add("Seafood")
            } else {
                // Batalkan pilihan
                databaseReference.child("users").child(userId).child("preferences").child("Seafood").removeValue()
                cardSeafood.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorUnselected))
                selectedPreferences.remove("Seafood")
            }
        }


        val btnNextDislike = findViewById<Button>(R.id.btnNextDislike)
        btnNextDislike.setOnClickListener {
            // Simpan semua preferensi yang dipilih
            for (preference in selectedPreferences) {
                savePreferenceToFirebase(preference, userId)
            }

            // Pindah ke halaman berikutnya (misalnya HalamanPreferensiDislike)
            val intent = Intent(this, HalamanPreferensiDislike::class.java)
            startActivity(intent)
        }
    }

    private fun savePreferenceToFirebase(preference: String, userId: String) {
        // Pastikan UID tidak null sebelum menyimpan data
        userId.let {
            // Simpan data ke Firebase
            databaseReference.child("users").child(it).child("preferensi alergi").child(preference).setValue(true)
        }
    }

}