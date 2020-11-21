package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.repository

import androidx.annotation.WorkerThread
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao.IngredientDao
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Ingredient
import kotlinx.coroutines.flow.Flow

class IngredientRepository(private val ingredientDao: IngredientDao,
                           recipeId: Int) {

    val allIngredientsForRecipe: Flow<List<Ingredient>> =
        ingredientDao.getAllIngredientsForRecipe(recipeId)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(ingredient: Ingredient) {
        ingredientDao.insertIngredient(ingredient)
    }
}