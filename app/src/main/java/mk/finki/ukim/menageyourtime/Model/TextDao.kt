package mk.finki.ukim.menageyourtime.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveText(textModel: TextModel)



    @Query("SELECT * FROM text_table ORDER BY isChecked ASC, id DESC")
    fun getAllTexts(): LiveData<List<TextModel>>

    @Update
    fun updateText(text: TextModel)

    @Delete
    fun deleteText(text: TextModel)

}
