package org.example.app.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.example.app.R
import org.example.app.data.entity.Note
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * PUBLIC_INTERFACE
 * RecyclerView adapter for notes list with delete action and click to edit.
 */
class NotesAdapter(
    private val onClick: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.VH>() {

    private val items = mutableListOf<Note>()
    private val df = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    fun submit(data: List<Note>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val note = items[position]
        holder.title.text = note.title
        holder.subtitle.text = "${df.format(note.updatedAt)}  •  ${note.tags}"
        holder.itemView.setOnClickListener { onClick(note) }
        holder.delete.setOnClickListener { onDelete(note) }
    }

    override fun getItemCount(): Int = items.size

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.text_title)
        val subtitle: TextView = v.findViewById(R.id.text_subtitle)
        val delete: ImageButton = v.findViewById(R.id.btn_delete)
    }
}
