package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao

import androidx.room.*
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe_table ORDER BY createdOn ASC")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(recipe: Recipe)

    @Update
    fun updateRecipe(vararg recipe: Recipe)

    @Delete
    fun deleteRecipe(vararg recipe: Recipe)
}