package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
class Ingredient(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "recipeId") val recipeId: Int,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "value") val value: Int,

    @ColumnInfo(name = "unit") val unit: String
)