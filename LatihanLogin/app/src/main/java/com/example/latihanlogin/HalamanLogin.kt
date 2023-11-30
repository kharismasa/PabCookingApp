package com.example.latihanlogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HalamanLogin : AppCompatActivity() {

    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Hide the action bar
        supportActionBar?.hide()

        inputUsername = findViewById(R.id.editTextUsername)
        inputPassword = findViewById(R.id.editTextPassword)

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference
    }

    fun fLogin(view: View) {
        val iUsername = inputUsername.text.toString()
        val iPassword = inputPassword.text.toString()

        // Contoh: Ganti dengan logika otentikasi sesuai kebutuhan
        val isLoginSuccessful = performLogin(iUsername, iPassword)

        if (isLoginSuccessful) {
            // Jika login berhasil, buat Intent dan kirim data ke aktivitas selanjutnya
            val intKeHalamanPreverensiAlergi = Intent(this, HalamanPreferensiAlergi::class.java)
            intKeHalamanPreverensiAlergi.apply {
                putExtra("username", iUsername)
            }
            startActivity(intKeHalamanPreverensiAlergi)

            // Simpan data ke Firebase Realtime Database
            saveDataToFirebase(iUsername, iPassword)
        } else {
            // Jika login gagal, tampilkan pesan toast
            Toast.makeText(this, "Login Gagal. Periksa kembali username dan password Anda.", Toast.LENGTH_SHORT).show()
        }
    }

    // Metode simulasi otentikasi
    private fun performLogin(username: String, password: String): Boolean {
        // Ganti ini dengan otentikasi Firebase jika Anda menggunakannya
        return username == "Kharisma" && password == "kelasC"
    }

    private fun saveDataToFirebase(username: String, password: String) {
        // Mendapatkan UID pengguna yang sudah login
        val userId = firebaseAuth.currentUser?.uid

        // Pastikan userId tidak null sebelum menyimpan data
        userId?.let {
            // Jalur referensi di Firebase Realtime Database
            val userReference = databaseReference.child("users").child(userId)

            // Simpan data ke Firebase
            userReference.child("username").setValue(username)
            userReference.child("password").setValue(password)
        }
    }
}
