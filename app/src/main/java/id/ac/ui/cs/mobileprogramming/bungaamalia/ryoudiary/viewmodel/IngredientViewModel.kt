package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel

import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Ingredient
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.repository.IngredientRepository
import kotlinx.coroutines.launch

class IngredientViewModel(private val repository: IngredientRepository) : ViewModel() {

    val allIngredientsForRecipe: LiveData<List<Ingredient>> =
        repository.allIngredientsForRecipe.asLiveData()

    fun insertIngredient(ingredient: Ingredient) = viewModelScope.launch {
        repository.insert(ingredient)
    }
}

class IngredientViewModelFactory(private val repository: IngredientRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngredientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IngredientViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}