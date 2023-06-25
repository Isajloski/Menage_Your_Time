package mk.finki.ukim.menageyourtime

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import mk.finki.ukim.menageyourtime.Adapters.TextAdapter
import mk.finki.ukim.menageyourtime.ViewModels.TextViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TextViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TextAdapter
    private lateinit var navigationView: BottomNavigationView

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



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigationView = findViewById(R.id.navigation)
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        var textViewModel = ViewModelProvider(this).get(TextViewModel::class.java)

        val editText = findViewById<EditText>(R.id.Title)
        val buttonSave = findViewById<Button>(R.id.btnAddText)


        recyclerView = findViewById(R.id.recycler_view)
        adapter = TextAdapter(emptyList(), recyclerView, textViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.enableSwipeToDelete(recyclerView)



        buttonSave.setOnClickListener {
            val text = editText.text.toString()
            textViewModel.saveText(text, false)
            editText.text.clear()
        }

        textViewModel.getAllTexts().observe(this, { texts ->
            adapter.texts = texts
            adapter.notifyDataSetChanged()
        })
    }
}
