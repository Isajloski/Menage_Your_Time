package mk.finki.ukim.menageyourtime.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mk.finki.ukim.menageyourtime.Model.TextModel
import mk.finki.ukim.menageyourtime.R

class TextAdapter(var texts: List<TextModel>) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

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
    }



    override fun getItemCount() = texts.size
}
