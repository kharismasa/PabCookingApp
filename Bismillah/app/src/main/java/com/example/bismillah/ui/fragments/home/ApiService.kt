package com.example.bismillah.ui.fragments.home

import com.example.bismillah.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("recipes")
    suspend fun getRecipes(): List<FoodRecipe>
//    @GET("/recipes/complexSearch")
//    suspend fun getRecipes(
//        @QueryMap queries: Map<String, String>
//    ): Response<FoodRecipe>

    @GET("random_recipes")
    suspend fun getRandomRecipes(): List<FoodRecipe>
}
