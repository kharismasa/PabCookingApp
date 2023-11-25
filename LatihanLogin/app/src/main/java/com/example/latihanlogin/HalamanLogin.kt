package com.example.latihanlogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HalamanLogin : AppCompatActivity() {

    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inputUsername = findViewById(R.id.editTextUsername)
        inputPassword = findViewById(R.id.editTextPassword)
    }

    fun fLogin(view: View) {
        val iUsername = inputUsername.text.toString()
        val iPassword = inputPassword.text.toString()

        // Contoh: Ganti dengan logika otentikasi sesuai kebutuhan
        val isLoginSuccessful = performLogin(iUsername, iPassword)

        if (isLoginSuccessful) {
            // Jika login berhasil, buat Intent dan kirim data ke aktivitas selanjutnya
            val intKeHalamanWelcome = Intent(this, HalamanWelcome::class.java)
            intKeHalamanWelcome.apply {
                putExtra("username", iUsername)
            }
            startActivity(intKeHalamanWelcome)
        } else {
            // Jika login gagal, tampilkan pesan toast
            Toast.makeText(this, "Login Gagal. Periksa kembali username dan password Anda.", Toast.LENGTH_SHORT).show()
        }
    }

    // Metode simulasi otentikasi
    private fun performLogin(username: String, password: String): Boolean {
        // Ganti dengan logika otentikasi sesuai kebutuhan
        // Misalnya, validasi username dan password dengan data yang sudah disimpan
        // di server atau penyimpanan lokal.
        return  password == "kelasC"
    }
}
