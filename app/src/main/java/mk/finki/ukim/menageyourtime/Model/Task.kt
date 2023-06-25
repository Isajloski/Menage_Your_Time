package mk.finki.ukim.menageyourtime.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks")
data class Task(
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val reminder: Reminder,
    val repeat: Repeat?,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) : Serializable {
    enum class Reminder {
        MINUTES_10,
        MINUTES_30,
        HOURS_1
    }

    enum class Repeat {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }
}
