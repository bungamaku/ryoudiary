package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.repository

import androidx.annotation.WorkerThread
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {

    val allRecipes: Flow<List<Recipe>> = recipeDao.getAllRecipes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }
}