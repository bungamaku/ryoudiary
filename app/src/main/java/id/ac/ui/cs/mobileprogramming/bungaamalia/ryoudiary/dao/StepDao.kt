package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao

import androidx.room.*
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Step
import kotlinx.coroutines.flow.Flow

@Dao
interface StepDao {
    @Query("SELECT * FROM step_table WHERE recipeId = :recipeId")
    fun getAllStepsForRecipe(recipeId: Int): Flow<List<Step>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStep(step: Step)

    @Update
    fun updateStep(vararg step: Step)

    @Delete
    fun deleteStep(vararg step: Step)
}