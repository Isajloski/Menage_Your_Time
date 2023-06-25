package mk.finki.ukim.menageyourtime

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mk.finki.ukim.menageyourtime.Adapters.TaskAdapter
import mk.finki.ukim.menageyourtime.Model.Task
import mk.finki.ukim.menageyourtime.R
import mk.finki.ukim.menageyourtime.ViewModels.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*

class TaskActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter
    private var selectedDate: Date? = null
    private var selectedTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        taskAdapter = TaskAdapter()
//        recyclerView.adapter = taskAdapter
//        recyclerView.layoutManager = LinearLayoutManager(this)

        taskViewModel.allTasks.observe(this, Observer { tasks ->
            taskAdapter.submitList(tasks)
        })


        val deleteButton: Button = findViewById(R.id.deleteButton)
        deleteButton.visibility = View.GONE

        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val titleEditText: EditText = findViewById(R.id.titleEditText)
            val descriptionEditText: EditText = findViewById(R.id.descriptionEditText)
            val reminderSpinner: Spinner = findViewById(R.id.reminderSpinner)
            val reminderOptions = resources.getStringArray(R.array.reminder_options)
            val reminderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, reminderOptions)
            reminderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            reminderSpinner.adapter = reminderAdapter
            val repeatSpinner: Spinner = findViewById(R.id.repeatSpinner)
            val repeatOptions = resources.getStringArray(R.array.repeat_options)
            val repeatAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, repeatOptions)
            
            repeatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            repeatSpinner.adapter = repeatAdapter

            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()




            if (title.isNotBlank() && selectedTime != null) {
                val reminder = reminderSpinner.selectedItem.toString()
                val selectedReminder = getSelectedReminder(reminder)
                val repeat = repeatSpinner.selectedItem.toString()
                val selectedRepeat = getSelectedRepeat(repeat)

                if (selectedReminder != null) {
                    val task = Task(
                        title,
                        description,
                        formatDate(selectedDate),
                        selectedTime!!,
                        selectedReminder,
                        selectedRepeat
                    )
                    taskViewModel.insert(task)

                    titleEditText.text.clear()
                    descriptionEditText.text.clear()
                    selectedTime = null
                } else {
                    Toast.makeText(this, "Invalid reminder option", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Title and time cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }



        val dateButton: Button = findViewById(R.id.dateButton)
        dateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(year, month, dayOfMonth)
                    selectedDate = selectedCalendar.time

                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    dateButton.text = dateFormat.format(selectedDate)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        val timeButton: Button = findViewById(R.id.timeButton)
        timeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                { _: TimePicker?, hourOfDay: Int, minute: Int ->
                    selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                    timeButton.text = selectedTime
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }
    }

    private fun getSelectedRepeat(repeat: String): Task.Repeat? {
        return when (repeat) {
            "Daily" -> Task.Repeat.DAILY
            "Weekly" -> Task.Repeat.WEEKLY
            "Monthly" -> Task.Repeat.MONTHLY
            "Yearly" -> Task.Repeat.YEARLY
            else -> null
        }
    }
    private fun formatDate(date: Date?): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return if (date != null) dateFormat.format(date) else ""
    }

    private fun getSelectedReminder(reminder: String): Task.Reminder? {
        return when (reminder) {
            "10 minutes" -> Task.Reminder.MINUTES_10
            "30 minutes" -> Task.Reminder.MINUTES_30
            "1 hour" -> Task.Reminder.HOURS_1
            else -> null
        }
    }

}
