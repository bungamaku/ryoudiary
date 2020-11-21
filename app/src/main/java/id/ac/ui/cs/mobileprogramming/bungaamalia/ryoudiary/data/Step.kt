package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step_table")
class Step(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "recipeId") val recipeId: Int,

    @ColumnInfo(name = "order") val order: Int,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "duration") val duration: Int
)