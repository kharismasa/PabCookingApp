package com.example.bismillah.preferensi

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.bismillah.R
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class HalamanEditPreferensi : AppCompatActivity() {

    private lateinit var checkBoxDairy: CheckBox
    private lateinit var checkBoxEgg: CheckBox
    private lateinit var checkBoxGluten: CheckBox
    private lateinit var checkBoxPeanut: CheckBox
    private lateinit var checkBoxSeafood: CheckBox
    private lateinit var checkBoxSesame: CheckBox
    private lateinit var checkBoxSoy: CheckBox
    private lateinit var checkBoxSulfite: CheckBox
    private lateinit var checkBoxTreenut: CheckBox
    private lateinit var checkBoxWheat: CheckBox

    private lateinit var checkBoxAlcohol: CheckBox
    private lateinit var checkBoxAvocado: CheckBox
    private lateinit var checkBoxBeef: CheckBox
    private lateinit var checkBoxFish: CheckBox
    private lateinit var checkBoxMayonnaise: CheckBox
    private lateinit var checkBoxOnion: CheckBox
    private lateinit var checkBoxPork: CheckBox
    private lateinit var checkBoxPotato: CheckBox
    private lateinit var checkBoxSeafoodDisliked: CheckBox
    private lateinit var checkBoxShrimp: CheckBox

    private lateinit var btnSavePreferences: Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_edit_preferensi)

        // Inisialisasi CheckBox
        checkBoxDairy = findViewById(R.id.checkBoxDairy)
        checkBoxEgg = findViewById(R.id.checkBoxEgg)
        checkBoxGluten = findViewById(R.id.checkBoxGluten)
        checkBoxPeanut = findViewById(R.id.checkBoxPeanut)
        checkBoxSeafood = findViewById(R.id.checkBoxSeafood)
        checkBoxSesame = findViewById(R.id.checkBoxSesame)
        checkBoxSoy = findViewById(R.id.checkBoxSoy)
        checkBoxSulfite = findViewById(R.id.checkBoxSulfite)
        checkBoxTreenut = findViewById(R.id.checkBoxTreenut)
        checkBoxWheat = findViewById(R.id.checkBoxWheat)

        checkBoxAlcohol = findViewById(R.id.checkBoxAlcohol)
        checkBoxAvocado = findViewById(R.id.checkBoxAvocado)
        checkBoxBeef = findViewById(R.id.checkBoxBeef)
        checkBoxFish = findViewById(R.id.checkBoxFish)
        checkBoxMayonnaise = findViewById(R.id.checkBoxMayonnaise)
        checkBoxOnion = findViewById(R.id.checkBoxOnion)
        checkBoxPork = findViewById(R.id.checkBoxPork)
        checkBoxPotato = findViewById(R.id.checkBoxPotato)
        checkBoxSeafoodDisliked = findViewById(R.id.checkBoxSeafoodDisliked)
        checkBoxShrimp = findViewById(R.id.checkBoxShrimp)
        // Inisialisasi CheckBox lainnya sesuai kebutuhan

        btnSavePreferences = findViewById(R.id.btnSimpanPreferensi)

        // Ganti dengan referensi Firebase yang sesuai
        val userId = "Kharisma"
        databaseReference = FirebaseDatabase.getInstance().reference.child("users").child(userId)

        // Mendapatkan data dari Firebase dan menginisialisasi CheckBox
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxDairy)
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxEgg)
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxGluten)
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxPeanut)
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxSeafood)
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxSesame)
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxSoy)
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxSulfite)
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxTreenut)
                    initializeCheckboxFromFirebase(snapshot, "preferensi alergi", checkBoxWheat)

                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxAlcohol)
                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxAvocado)
                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxBeef)
                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxFish)
                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxMayonnaise)
                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxOnion)
                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxPork)
                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxPotato)
                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxSeafoodDisliked)
                    initializeCheckboxFromFirebase(snapshot, "preferensi dislike", checkBoxShrimp)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        // Set listener tombol save
        btnSavePreferences.setOnClickListener {
            saveDataToFirebase()
        }
    }

    private fun initializeCheckboxFromFirebase(snapshot: DataSnapshot, key: String, checkBox: CheckBox) {
        val preferencesSnapshot = snapshot.child(key)
        val ingredientName = checkBox.text.toString()

        if (preferencesSnapshot.child(ingredientName).getValue(Boolean::class.java) == true) {
            checkBox.isChecked = true
        }
    }

//    private fun saveDataToFirebase() {
//        val allergenicIngredients = listOf(
//            "Dairy", "Egg", "Gluten", "Peanut", "Seafood", "Sesame", "Soy", "Sulfite", "Treenut", "Wheat"
//            // Tambahkan bahan alergi lainnya sesuai kebutuhan
//        )
//
//        val dislikedIngredients = listOf(
//            "Alcohol", "Avocado", "Beef", "Fish", "Mayonnaise", "Onion", "Pork", "Potato", "Seafood", "Shrimp"
//            // Tambahkan bahan dislike lainnya sesuai kebutuhan
//        )
//
//        // Simpan data ke Firebase
//        saveIngredientsToFirebase("preferensi alergi", allergenicIngredients)
//        saveIngredientsToFirebase("preferensi dislike", dislikedIngredients)
//
//        // Tampilkan Toast "Data Preferensi telah tersimpan" setelah penyimpanan berhasil
//        Toast.makeText(this, "Data Preferensi telah tersimpan", Toast.LENGTH_SHORT).show()
//    }

    private fun saveDataToFirebase() {
        val allergenicIngredients = listOf(
            "Dairy", "Egg", "Gluten", "Peanut", "Seafood", "Sesame", "Soy", "Sulfite", "Treenut", "Wheat"
            // Tambahkan bahan alergi lainnya sesuai kebutuhan
        )

        val dislikedIngredients = listOf(
            "Alcohol", "Avocado", "Beef", "Fish", "Mayonnaise", "Onion", "Pork", "Potato", "Seafood", "Shrimp"
            // Tambahkan bahan dislike lainnya sesuai kebutuhan
        )

        // Inisialisasi Map untuk menyimpan data yang akan disimpan di Firebase
        val saveData = mutableMapOf<String, Any?>()

        // Simpan data bahan alergi yang dicentang
        for (ingredient in allergenicIngredients) {
            val checkBox = getCheckBoxByName(ingredient)
            saveData["preferensi alergi/$ingredient"] = checkBox?.isChecked == true
        }

        // Simpan data bahan dislike yang dicentang
        for (ingredient in dislikedIngredients) {
            val checkBox = getCheckBoxByName(ingredient)
            saveData["preferensi dislike/$ingredient"] = checkBox?.isChecked == true
        }

        // Melakukan penyimpanan data di Firebase
        databaseReference.updateChildren(saveData)
            .addOnSuccessListener {
                // Tampilkan Toast "Data Preferensi telah tersimpan" setelah penyimpanan berhasil
                Toast.makeText(this, "Data Preferensi telah tersimpan", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                // Handle error
                Toast.makeText(this, "Gagal menyimpan data Preferensi", Toast.LENGTH_SHORT).show()
            }

        // Hapus data bahan yang tidak dicentang
        deleteUncheckedIngredients()
    }

    private fun getCheckBoxByName(ingredient: String): CheckBox? {
        return when (ingredient) {
            "Dairy" -> checkBoxDairy
            "Egg" -> checkBoxEgg
            "Gluten" -> checkBoxGluten
            "Peanut" -> checkBoxPeanut
            "Seafood" -> checkBoxSeafood
            "Sesame" -> checkBoxSesame
            "Soy" -> checkBoxSoy
            "Sulfite" -> checkBoxSulfite
            "Treenut" -> checkBoxTreenut
            "Wheat" -> checkBoxWheat
            "Alcohol" -> checkBoxAlcohol
            "Avocado" -> checkBoxAvocado
            "Beef" -> checkBoxBeef
            "Fish" -> checkBoxFish
            "Mayonnaise" -> checkBoxMayonnaise
            "Onion" -> checkBoxOnion
            "Pork" -> checkBoxPork
            "Potato" -> checkBoxPotato
            "SeafoodDisliked" -> checkBoxSeafoodDisliked
            "Shrimp" -> checkBoxShrimp
            else -> null
        }
    }

    private fun deleteUncheckedIngredients() {
        val allergenicIngredients = listOf(
            "Dairy", "Egg", "Gluten", "Peanut", "Seafood", "Sesame", "Soy", "Sulfite", "Treenut", "Wheat"
            // Tambahkan bahan alergi lainnya sesuai kebutuhan
        )

        val dislikedIngredients = listOf(
            "Alcohol", "Avocado", "Beef", "Fish", "Mayonnaise", "Onion", "Pork", "Potato", "Seafood", "Shrimp"
            // Tambahkan bahan dislike lainnya sesuai kebutuhan
        )

        // Inisialisasi Map untuk menyimpan data yang akan dihapus di Firebase
        val deleteData = mutableMapOf<String, Any?>()

        // Hapus bahan alergi yang tidak dicentang
        for (ingredient in allergenicIngredients) {
            val checkBox = getCheckBoxByName(ingredient)
            if (checkBox?.isChecked == false) {
                deleteData["preferensi alergi/$ingredient"] = null
            }
        }

        // Hapus bahan dislike yang tidak dicentang
        for (ingredient in dislikedIngredients) {
            val checkBox = getCheckBoxByName(ingredient)
            if (checkBox?.isChecked == false) {
                deleteData["preferensi dislike/$ingredient"] = null
            }
        }

        // Melakukan penghapusan data di Firebase
        databaseReference.updateChildren(deleteData)
            .addOnSuccessListener {
                // Tidak ada toast ditampilkan karena ini tidak disertakan
            }
            .addOnFailureListener {
                // Handle error
                // Tidak ada toast ditampilkan karena ini tidak disertakan
            }
    }


//    private fun saveIngredientsToFirebase(key: String, ingredients: List<String>) {
//        val ingredientsMap = mutableMapOf<String, Boolean>()
//        for (ingredient in ingredients) {
//            ingredientsMap[ingredient] = true
//        }
//        databaseReference.child(key).setValue(ingredientsMap)
//    }
}
