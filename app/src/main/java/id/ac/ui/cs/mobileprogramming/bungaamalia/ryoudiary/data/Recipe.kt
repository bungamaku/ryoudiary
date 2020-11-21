package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "recipe_table")
class Recipe(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "image") val image: String,

    @ColumnInfo(name = "createdOn") val createdOn: Date
)