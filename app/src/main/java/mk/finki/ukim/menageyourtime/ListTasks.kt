package mk.finki.ukim.menageyourtime

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import mk.finki.ukim.menageyourtime.Adapters.ListTaskAdapter
import mk.finki.ukim.menageyourtime.ViewModels.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*

class ListTasks : AppCompatActivity() {

    private lateinit var navigationView: BottomNavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var listTasksAdapter: ListTaskAdapter
    private lateinit var tastViewModel: TaskViewModel


    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navTask -> {
                    startActivity(Intent(this, ListTasks::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navTodo -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navPomodoro -> {
                    startActivity(Intent(this, TaskActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navStatistics -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_tasks)

        navigationView = findViewById(R.id.navigation)
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        tastViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        val fabAdd: FloatingActionButton = findViewById(R.id.fabAdd)

        val linearLayout = findViewById<LinearLayout>(R.id.buttons)
        val btn1 = linearLayout.findViewById<Button>(R.id.btn1)
        val btn2 = linearLayout.findViewById<Button>(R.id.btn2)
        val btn3 = linearLayout.findViewById<Button>(R.id.btn3)
        val btn4 = linearLayout.findViewById<Button>(R.id.btn4)
        val btn5 = linearLayout.findViewById<Button>(R.id.btn5)
        val btn6 = linearLayout.findViewById<Button>(R.id.btn6)
        val btn7 = linearLayout.findViewById<Button>(R.id.btn7)

        val btn1Day: Calendar = Calendar.getInstance()
        btn2.text = (btn1Day.get(Calendar.DAY_OF_MONTH) - 3).toString()

        val btn2Day: Calendar = Calendar.getInstance()
        btn2.text = (btn2Day.get(Calendar.DAY_OF_MONTH) - 2).toString()

        val btn3Day: Calendar = Calendar.getInstance()
        btn3.text = (btn3Day.get(Calendar.DAY_OF_MONTH) - 1).toString()

        val btn4Day: Calendar = Calendar.getInstance()
        btn4.text = (btn4Day.get(Calendar.DAY_OF_MONTH)).toString()

        val btn5Day: Calendar = Calendar.getInstance()
        btn5.text = (btn5Day.get(Calendar.DAY_OF_MONTH) + 1).toString()


        val btn6Day: Calendar = Calendar.getInstance()
        btn6.text = (btn6Day.get(Calendar.DAY_OF_MONTH) + 2).toString()

        val btn7Day: Calendar = Calendar.getInstance()
        btn7.text = (btn7Day.get(Calendar.DAY_OF_MONTH) + 3).toString()

        recyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        listTasksAdapter = ListTaskAdapter(emptyList()) // Initially, the adapter is empty
        recyclerView.adapter = listTasksAdapter


        btn1.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, -3)
            val threeDaysBeforeToday = calendar.time

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(threeDaysBeforeToday)

            tastViewModel.findTaskByDate(formattedDate).observe(this, { tasks ->
                listTasksAdapter.tasks = tasks
                listTasksAdapter.notifyDataSetChanged()
            })
        }


        btn2.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, -2)
            val threeDaysBeforeToday = calendar.time

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(threeDaysBeforeToday)

            tastViewModel.findTaskByDate(formattedDate).observe(this, { tasks ->
                listTasksAdapter.tasks = tasks
                listTasksAdapter.notifyDataSetChanged()
            })
        }


        btn3.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val threeDaysBeforeToday = calendar.time

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(threeDaysBeforeToday)

            tastViewModel.findTaskByDate(formattedDate).observe(this, { tasks ->
                listTasksAdapter.tasks = tasks
                listTasksAdapter.notifyDataSetChanged()
            })
        }


        btn4.setOnClickListener {
            val calendar = Calendar.getInstance()
            val threeDaysBeforeToday = calendar.time

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(threeDaysBeforeToday)

            tastViewModel.findTaskByDate(formattedDate).observe(this, { tasks ->
                listTasksAdapter.tasks = tasks
                listTasksAdapter.notifyDataSetChanged()
            })
        }


        btn5.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, +1)
            val threeDaysBeforeToday = calendar.time

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(threeDaysBeforeToday)

            tastViewModel.findTaskByDate(formattedDate).observe(this, { tasks ->
                listTasksAdapter.tasks = tasks
                listTasksAdapter.notifyDataSetChanged()
            })
        }

        btn6.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, +2)
            val threeDaysBeforeToday = calendar.time

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(threeDaysBeforeToday)

            tastViewModel.findTaskByDate(formattedDate).observe(this, { tasks ->
                listTasksAdapter.tasks = tasks
                listTasksAdapter.notifyDataSetChanged()
            })
        }

        btn7.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, +3)
            val threeDaysBeforeToday = calendar.time

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(threeDaysBeforeToday)

            tastViewModel.findTaskByDate(formattedDate).observe(this, { tasks ->
                listTasksAdapter.tasks = tasks
                listTasksAdapter.notifyDataSetChanged()
            })
        }

        fabAdd.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }



    }
}