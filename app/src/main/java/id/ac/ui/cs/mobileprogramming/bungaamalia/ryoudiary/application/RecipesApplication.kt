package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.application

import android.app.Application
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.roomdatabase.RecipeRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RecipesApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { RecipeRoomDatabase.getRecipeDatabase(this, applicationScope) }
    val repository by lazy { RecipeRepository(database.recipeDao()) }
}