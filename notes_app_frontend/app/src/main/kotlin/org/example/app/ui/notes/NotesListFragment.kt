package org.example.app.ui.notes

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.example.app.R
import org.example.app.data.entity.Note

/**
 * PUBLIC_INTERFACE
 * NotesListFragment displays notes, supports search and navigation to editor.
 */
class NotesListFragment : Fragment() {

    private val vm: NotesViewModel by viewModels()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_notes)
        val search = view.findViewById<EditText>(R.id.input_search)

        adapter = NotesAdapter(onClick = { note -> openEditor(note) }, onDelete = { note ->
            vm.deleteNote(note)
        })
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        vm.notes.observe(viewLifecycleOwner) { list -> adapter.submit(list) }

        search.addTextChangedListener {
            vm.setQuery(it?.toString().orEmpty())
        }
    }

    private fun openEditor(note: Note) {
        val args = Bundle().apply { putLong("noteId", note.id) }
        findNavController().navigate(R.id.action_notesListFragment_to_noteEditorFragment, args)
    }
}
