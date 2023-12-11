package com.example.bismillah.ui.fragments.home

import com.example.bismillah.models.FoodRecipe

class RecipesRepository(private val apiService: ApiService) {

    suspend fun getRandomRecipes(): List<FoodRecipe> {
        // Panggil metode di ApiService atau sumber data Anda untuk mendapatkan resep acak
        return apiService.getRandomRecipes()
    }
}