package mk.finki.ukim.menageyourtime

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mk.finki.ukim.menageyourtime.Adapters.TextAdapter
import mk.finki.ukim.menageyourtime.ViewModels.TextViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TextViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TextAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        var textViewModel = ViewModelProvider(this).get(TextViewModel::class.java)
//
//        val title = findViewById<EditText>(R.id.Title)
//        val displayText = findViewById<TextView>(R.id.DisplayText)
//        val buttonSave = findViewById<Button>(R.id.btnAddText)
//
//        buttonSave.setOnClickListener {
//            val text = title.text.toString()
//            textViewModel.saveText(text)
//
//        }
//
//
//        textViewModel.getAllTexts().observe(this, { texts ->
//            val allTexts = texts.joinToString("\n")
//            displayText.text = allTexts
//        })
//    }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textViewModel = ViewModelProvider(this).get(TextViewModel::class.java)

        val editText = findViewById<EditText>(R.id.Title)
        val buttonSave = findViewById<Button>(R.id.btnAddText)

        recyclerView = findViewById(R.id.recycler_view)
        adapter = TextAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonSave.setOnClickListener {
            val text = editText.text.toString()
            textViewModel.saveText(text)
            editText.text.clear()
        }

        textViewModel.getAllTexts().observe(this, { texts ->
            adapter.texts = texts
            adapter.notifyDataSetChanged()
        })
    }
}
