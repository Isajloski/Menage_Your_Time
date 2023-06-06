package mk.finki.ukim.menageyourtime.Repository

import androidx.lifecycle.LiveData
import mk.finki.ukim.menageyourtime.Model.TextDao
import mk.finki.ukim.menageyourtime.Model.TextModel

class TextRepository(private val textDao: TextDao) {
    suspend fun saveText(text: String) {
        val textModel = TextModel(0, text)
        textDao.saveText(textModel)
    }

//    suspend fun getText(): String? {
//        val textModel = textDao.getText()
//        return textModel?.text
//    }

    val allTexts: LiveData<List<TextModel>> = textDao.getAllTexts()

}
