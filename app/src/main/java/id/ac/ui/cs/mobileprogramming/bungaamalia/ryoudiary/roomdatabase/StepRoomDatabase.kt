package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.dao.StepDao
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Step

@Database(entities = [Step::class], version = 1, exportSchema = false)
public abstract class StepRoomDatabase : RoomDatabase() {

    abstract fun stepDao(): StepDao

    companion object {
        @Volatile
        private var INSTANCE: StepRoomDatabase? = null

        fun getStepDatabase(context: Context): StepRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StepRoomDatabase::class.java,
                    "step_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}