package mk.finki.ukim.menageyourtime.Repository

import androidx.lifecycle.LiveData
import mk.finki.ukim.menageyourtime.Model.Task
import mk.finki.ukim.menageyourtime.Model.TaskDao
import java.text.SimpleDateFormat
import java.util.*

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    fun findTasksByDate(date: String): LiveData<List<Task>> {
        return taskDao.findTasksByDate(date)
    }

}