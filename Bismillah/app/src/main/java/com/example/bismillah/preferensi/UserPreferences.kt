package com.example.bismillah.preferensi

data class UserPreferences(
    var allergenicIngredients: List<String> = emptyList(),
    var dislikedIngredients: List<String> = emptyList()
)

