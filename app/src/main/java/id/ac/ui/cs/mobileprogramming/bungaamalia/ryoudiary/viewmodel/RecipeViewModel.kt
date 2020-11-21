package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel

import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Recipe
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    val allRecipes: LiveData<List<Recipe>> = repository.allRecipes.asLiveData()

    fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.insert(recipe)
    }
}

class RecipeViewModelFactory(private val repository: RecipeRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}