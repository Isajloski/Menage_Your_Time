package mk.finki.ukim.menageyourtime.Repository

import androidx.lifecycle.LiveData
import mk.finki.ukim.menageyourtime.Model.TextDao
import mk.finki.ukim.menageyourtime.Model.TextModel

class TextRepository(private val textDao: TextDao) {
    suspend fun saveText(text: String, isChecked: Boolean) {
        val textModel = TextModel(0, text, isChecked)
        textDao.saveText(textModel)
    }

    suspend fun updateText(text: TextModel) {
        val textModel = TextModel(text.id, text.text, text.isChecked)
        textDao.saveText(textModel)
    }

    fun deleteText(text: TextModel) {
        textDao.deleteText(text)
    }


    val allTexts: LiveData<List<TextModel>> = textDao.getAllTexts()

}
