package mk.finki.ukim.menageyourtime.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_table")
data class TextModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String
)
