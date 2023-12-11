package com.example.bismillah.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bismillah.data.DataStoreRepository
import com.example.bismillah.viewmodels.RecipesViewModel

class RecipesViewModelFactory(
    private val repository: RecipesRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModelProvider.Factory {

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return RecipesViewModel(repository, dataStoreRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
}
