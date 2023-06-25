package mk.finki.ukim.menageyourtime.Adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import mk.finki.ukim.menageyourtime.Model.TextModel
import mk.finki.ukim.menageyourtime.R
import mk.finki.ukim.menageyourtime.ViewModels.TextViewModel

class TextAdapter(var texts: List<TextModel>,     private val recyclerView: RecyclerView, private val textViewModel: TextViewModel) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val textView: TextView = view.findViewById(R.id.text_view_item)
          val textView: CheckBox = view.findViewById(R.id.text_view_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = texts[position]
        holder.textView.text = text.text

        holder.textView.isChecked = text.isChecked

        if (text.isChecked) {
            holder.textView.paintFlags = holder.textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.textView.paintFlags = holder.textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }


        holder.textView.setOnClickListener {
            notifyItemChanged(position)
            updateText(text)
        }
    }

    private fun updateText(text: TextModel) {
        println(text.isChecked)
        text.isChecked = !text.isChecked
        textViewModel.updateText(text)
    }

    fun deleteItem(text: TextModel) {
//        textViewModel.deleteText(text)
        println(text.text)
    }

    override fun getItemCount() = texts.size


    fun enableSwipeToDelete(recyclerView: RecyclerView) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedItem = texts[position]

                textViewModel.deleteItem(deletedItem)
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

}
