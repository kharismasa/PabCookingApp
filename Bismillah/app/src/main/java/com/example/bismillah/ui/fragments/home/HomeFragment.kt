package com.example.bismillah.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bismillah.adapters.RecomendationAdapter
import com.example.bismillah.databinding.FragmentHomeBinding
import com.example.bismillah.util.Constants.Companion.API_KEY
import com.example.bismillah.util.NetworkResult
import com.example.bismillah.viewmodels.MainViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val recomendationAdapter by lazy { RecomendationAdapter() }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.mainViewModel = mainViewModel


        // Inisialisasi referensi database Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child("Kharisma")

        // Dapatkan preferensi pengguna dari Firebase
        getPreferencesFromFirebase()

        setupRecyclerView()

        return binding.root
    }

    private fun getPreferencesFromFirebase() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Pengguna ditemukan di Firebase
                    val alergiPreferences = snapshot.child("preferensi alergi")
                    val dislikePreferences = snapshot.child("preferensi dislike")

                    // Bangun excludeIngredientsList berdasarkan preferensi
                    val excludeIngredientsList = buildExcludeIngredientsList(alergiPreferences, dislikePreferences)

                    // Gunakan excludeIngredientsList untuk mendapatkan resep
                    requestApiData(excludeIngredientsList)
                } else {
                    // Pengguna tidak ditemukan di Firebase
                    // requestApiData tanpa excludeIngredientsList (kosong)
                    requestApiData(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e("HomeFragment", "Error fetching user preferences: ${error.message}")
            }
        })
    }

    private fun buildExcludeIngredientsList(
        alergiPreferences: DataSnapshot,
        dislikePreferences: DataSnapshot
    ): List<String> {
        val excludeIngredientsList = mutableListOf<String>()

        // Tambahkan bahan dari preferensi alergi
        alergiPreferences.children.forEach { alergi ->
            if (alergi.value as Boolean) {
                excludeIngredientsList.add(alergi.key!!)
            }
        }

        // Tambahkan bahan dari preferensi dislike
        dislikePreferences.children.forEach { dislike ->
            if (dislike.value as Boolean) {
                excludeIngredientsList.add(dislike.key!!)
            }
        }

        return excludeIngredientsList
    }

    private fun setupRecyclerView() {
        binding.rekomendasiRecyclerView.adapter = recomendationAdapter
        binding.rekomendasiRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun requestApiData(excludeIngredientsList: List<String>) {
        mainViewModel.getRecipes(applyQueries(excludeIngredientsList))
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { recomendationAdapter.setData(it) }
                }

                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    private fun applyQueries(excludeIngredientsList: List<String>): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries["number"] = "50"
        queries["apiKey"] = API_KEY
//        queries["type"] = "snack"
//        queries["diet"] = "vegan"
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"

        // Tambahkan query untuk excludeIngredientsList
        if (excludeIngredientsList.isNotEmpty()) {
            queries["excludeIngredients"] = excludeIngredientsList.joinToString(",")
        }

        return queries
    }

    private fun showShimmerEffect() {
        binding.shimmerFrameLayout.startShimmer()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.rekomendasiRecyclerView.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.rekomendasiRecyclerView.visibility = View.VISIBLE
    }
}