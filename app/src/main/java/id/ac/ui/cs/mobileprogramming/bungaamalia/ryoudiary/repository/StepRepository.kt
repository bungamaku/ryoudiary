package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.repository

import androidx.annotation.WorkerThread
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao.StepDao
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Step
import kotlinx.coroutines.flow.Flow

class StepRepository(private val stepDao: StepDao, recipeId: Int) {

    val allStepsForRecipe: Flow<List<Step>> =
        stepDao.getAllStepsForRecipe(recipeId)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(step: Step) {
        stepDao.insertStep(step)
    }
}