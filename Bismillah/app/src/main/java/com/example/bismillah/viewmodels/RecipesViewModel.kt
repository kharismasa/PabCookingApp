package com.example.bismillah.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bismillah.data.DataStoreRepository
import com.example.bismillah.data.MealAndDietType
import com.example.bismillah.util.Constants.Companion.API_KEY
import com.example.bismillah.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.bismillah.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.bismillah.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.example.bismillah.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.example.bismillah.util.Constants.Companion.QUERY_API_KEY
import com.example.bismillah.util.Constants.Companion.QUERY_DIET
import com.example.bismillah.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.example.bismillah.util.Constants.Companion.QUERY_NUMBER
import com.example.bismillah.util.Constants.Companion.QUERY_SEARCH
import com.example.bismillah.util.Constants.Companion.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private lateinit var mealAndDiet: MealAndDietType

    var networkStatus = false
    var backOnline = false

    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    fun saveMealAndDietType() =
        viewModelScope.launch(Dispatchers.IO) {
            if (this@RecipesViewModel::mealAndDiet.isInitialized) {
                dataStoreRepository.saveMealAndDietType(
                    mealAndDiet.selectedMealType,
                    mealAndDiet.selectedMealTypeId,
                    mealAndDiet.selectedDietType,
                    mealAndDiet.selectedDietTypeId
                )
            }
        }

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        mealAndDiet = MealAndDietType(
            mealType,
            mealTypeId,
            dietType,
            dietTypeId
        )
    }

    private fun saveBackOnline(backOnline: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        if (this@RecipesViewModel::mealAndDiet.isInitialized) {
            queries[QUERY_TYPE] = mealAndDiet.selectedMealType
            queries[QUERY_DIET] = mealAndDiet.selectedDietType
        } else {
            queries[QUERY_TYPE] = DEFAULT_MEAL_TYPE
            queries[QUERY_DIET] = DEFAULT_DIET_TYPE
        }

        return queries
    }

    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }

//    private val _randomRecipes = MutableLiveData<List<FoodRecipe>>()
//    val randomRecipes: LiveData<List<FoodRecipe>> get() = _randomRecipes
//    val viewModel = ViewModelProvider(this, RecipesViewModelFactory(RecipesRepository)).get(RecipesViewModel::class.java)
//
//
//    init {
//        getRandomRecipes()
//    }
//
//    private fun getRandomRecipes() {
//        viewModelScope.launch {
//            try {
//                val recipes = repository.getRandomRecipes()
//                _randomRecipes.value = recipes
//            } catch (e: Exception) {
//                // Handle error, if any
//            }
//        }
//    }

}