package mk.finki.ukim.menageyourtime.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.finki.ukim.menageyourtime.Model.TextDatabase
import mk.finki.ukim.menageyourtime.Model.TextModel
import mk.finki.ukim.menageyourtime.Repository.TextRepository

class TextViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TextRepository

    init {
        val textDao = TextDatabase.getDatabase(application).textDao()
        repository = TextRepository(textDao)
    }

    fun saveText(text: String) {
        viewModelScope.launch {
            repository.saveText(text)
        }
    }

//    fun getText(): LiveData<String?> {
//        val textLiveData = MutableLiveData<String?>()
//        viewModelScope.launch {
//            val text = repository.getText()
//            textLiveData.postValue(text)
//        }
//        return textLiveData
//    }

    fun getAllTexts(): LiveData<List<TextModel>> {
        return repository.allTexts
    }
}

