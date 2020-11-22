package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao

import androidx.room.*
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Ingredient
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredient_table WHERE recipeId = :recipeId")
    fun getAllIngredientsForRecipe(recipeId: Int): Flow<List<Ingredient>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(ingredient: Ingredient)

    @Update
    fun updateIngredient(vararg ingredient: Ingredient)

    @Delete
    fun deleteIngredient(vararg ingredient: Ingredient)
}