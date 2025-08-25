package org.example.app.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.example.app.R

/**
 * PUBLIC_INTERFACE
 * NoteEditorFragment allows creating/editing a single note.
 * Accepts optional "noteId" in arguments to edit existing.
 * For simplicity, loads only via ViewModel observe list and populates fields when present.
 */
class NoteEditorFragment : Fragment() {

    private val vm: NotesViewModel by viewModels()

    private var noteId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteId = arguments?.getLong("noteId", 0L)?.takeIf { it > 0 }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_note_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<EditText>(R.id.input_title)
        val content = view.findViewById<EditText>(R.id.input_content)
        val tags = view.findViewById<EditText>(R.id.input_tags)
        val save = view.findViewById<Button>(R.id.btn_save)

        // If editing, try to prefill from current list
        vm.notes.observe(viewLifecycleOwner) { list ->
            noteId?.let { id ->
                val n = list.firstOrNull { it.id == id }
                if (n != null) {
                    title.setText(n.title)
                    content.setText(n.content)
                    tags.setText(n.tags)
                }
            }
        }

        save.setOnClickListener {
            vm.saveNote(
                id = noteId,
                title = title.text.toString(),
                content = content.text.toString(),
                categoryId = null,
                tags = tags.text.toString()
            )
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
