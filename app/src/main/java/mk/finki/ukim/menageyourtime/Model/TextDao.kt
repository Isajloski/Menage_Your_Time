package mk.finki.ukim.menageyourtime.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveText(textModel: TextModel)
//
//    @Query("SELECT * FROM text_table LIMIT 1")
//    suspend fun getText(): TextModel?

    @Query("SELECT * FROM text_table ORDER BY id DESC")
    fun getAllTexts(): LiveData<List<TextModel>>

}
