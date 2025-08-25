package org.example.app.ui.categories

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.example.app.R
import org.example.app.data.entity.Category

/**
 * PUBLIC_INTERFACE
 * CategoriesFragment allows listing, adding, and deleting categories.
 */
class CategoriesFragment : Fragment() {

    private val vm: CategoriesViewModel by viewModels()
    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_categories)
        val input = view.findViewById<EditText>(R.id.input_category_name)
        val add = view.findViewById<Button>(R.id.btn_add_category)

        adapter = CategoriesAdapter { cat -> vm.deleteCategory(cat) }
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        vm.categories.observe(viewLifecycleOwner) { list -> adapter.submit(list) }

        add.setOnClickListener {
            vm.addCategory(input.text.toString())
            input.setText("")
        }
    }
}

class CategoriesAdapter(private val onDelete: (Category) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.VH>() {

    private val items = mutableListOf<Category>()

    fun submit(data: List<Category>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val c = items[position]
        holder.name.text = c.name
        holder.delete.setOnClickListener { onDelete(c) }
    }

    override fun getItemCount(): Int = items.size

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.text_name)
        val delete: ImageButton = v.findViewById(R.id.btn_delete_category)
    }
}
