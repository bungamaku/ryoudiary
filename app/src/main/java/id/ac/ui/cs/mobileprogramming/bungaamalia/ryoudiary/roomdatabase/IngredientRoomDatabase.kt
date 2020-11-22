package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao.IngredientDao
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Ingredient

@Database(entities = [Ingredient::class], version = 1, exportSchema = false)
public abstract class IngredientRoomDatabase : RoomDatabase() {

    abstract fun ingredientDao(): IngredientDao

    companion object {
        @Volatile
        private var INSTANCE: IngredientRoomDatabase? = null

        fun getIngredientDatabase(context: Context): IngredientRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IngredientRoomDatabase::class.java,
                    "ingredient_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}