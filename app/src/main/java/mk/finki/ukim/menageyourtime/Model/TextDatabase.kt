package mk.finki.ukim.menageyourtime.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TextModel::class], version = 1, exportSchema = false)
abstract class TextDatabase : RoomDatabase() {
    abstract fun textDao(): TextDao

    companion object {
        @Volatile
        private var INSTANCE: TextDatabase? = null

        fun getDatabase(context: Context): TextDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TextDatabase::class.java,
                    "text_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
