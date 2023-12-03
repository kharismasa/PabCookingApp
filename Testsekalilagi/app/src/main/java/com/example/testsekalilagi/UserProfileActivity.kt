package com.example.testsekalilagi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserProfileActivity : AppCompatActivity() {

    private lateinit var tilName: TextView
    private lateinit var tilEmail: TextView
    private lateinit var editButton: Button
    private lateinit var profileImage: ImageView
    private lateinit var logoutButton: TextView

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("users")

        tilName = findViewById(R.id.tilName)
        tilEmail = findViewById(R.id.tilEmail)
        editButton = findViewById(R.id.editbutton)
        profileImage = findViewById(R.id.profileimg)
        logoutButton = findViewById(R.id.logoutp)

        showUserData()

        editButton.setOnClickListener {
            startActivity(Intent(this@UserProfileActivity, GetStartedActivity::class.java))
        }

        logoutButton.setOnClickListener {
            startActivity(Intent(this@UserProfileActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun showUserData() {
        val currentUser = firebaseAuth.currentUser

        currentUser?.let {
            val userId = it.uid
            val userRef = databaseReference.child(userId)

            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.getValue(User::class.java)
                        user?.let {
                            // Set data pengguna ke tampilan
                            tilName.text = it.name
                            tilEmail.text = it.email
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle errors here
                }
            })
        }
    }
}
