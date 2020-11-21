package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KParameter

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
public abstract class RecipeRoomDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    private class RecipeDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.recipeDao())
                }
            }
        }

        suspend fun populateDatabase(recipeDao: RecipeDao) {
            var recipe = Recipe(1,"Nasi Goreng", "Nasi digoreng",
                "gambar nasgor", "2020-01-23 05:30")
            recipeDao.insertRecipe(recipe)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: RecipeRoomDatabase? = null

        fun getRecipeDatabase(context: Context,
                              scope: CoroutineScope
        ): RecipeRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeRoomDatabase::class.java,
                    "recipe_database"
                )
                    .addCallback(RecipeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}