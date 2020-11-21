package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
public abstract class RecipeRoomDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeRoomDatabase? = null

        fun getRecipeDatabase(context: Context): RecipeRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeRoomDatabase::class.java,
                    "recipe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}