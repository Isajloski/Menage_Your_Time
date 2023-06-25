package mk.finki.ukim.menageyourtime.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mk.finki.ukim.menageyourtime.Model.Task
import mk.finki.ukim.menageyourtime.R
import mk.finki.ukim.menageyourtime.TaskUpdate

class ListTaskAdapter(var tasks: List<Task>) : RecyclerView.Adapter<ListTaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        // Bind the task data to the ViewHolder views
        holder.bindTask(currentTask)

        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, TaskUpdate::class.java)
            intent.putExtra("id", currentTask.id)
            intent.putExtra("time", currentTask.time)
            intent.putExtra("date", currentTask.date)
            intent.putExtra("description", currentTask.description)
            intent.putExtra("title", currentTask.title)
            intent.putExtra("reminder", currentTask.reminder)
            intent.putExtra("repeat", currentTask.repeat)
            intent.putExtra("task", currentTask)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = tasks.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTitleTextView: TextView = itemView.findViewById(R.id.taskTitleTextView)
        private val taskTimeTextView: TextView = itemView.findViewById(R.id.taskTimeTextView)

        fun bindTask(task: Task) {
            // Bind only the title and time to the views
            taskTitleTextView.text = task.title
            taskTimeTextView.text = task.time
        }
    }
}

