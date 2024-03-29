package mk.finki.ukim.menageyourtime.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.finki.ukim.menageyourtime.Model.Task
import mk.finki.ukim.menageyourtime.Model.TaskDatabase
import mk.finki.ukim.menageyourtime.Repository.TaskRepository
import java.util.*


class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
    }

    fun insert(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    fun findTaskByDate(date: String): LiveData<List<Task>> {
        return repository.findTasksByDate(date)
    }


//    fun findTaskByDate(threeDaysBeforeToday: Date) {
//        return repository.findTaskByDate(threeDaysBeforeToday)
//    }

    val allTasks: LiveData<List<Task>> = repository.allTasks
}
