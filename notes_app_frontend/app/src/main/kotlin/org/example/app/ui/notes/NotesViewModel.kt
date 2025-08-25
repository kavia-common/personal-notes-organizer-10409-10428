package org.example.app.ui.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.app.data.AppDatabase
import org.example.app.data.entity.Note
import org.example.app.data.repo.NotesRepository

/**
 * PUBLIC_INTERFACE
 * NotesViewModel exposes notes, search, and CRUD operations.
 */
class NotesViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = NotesRepository(AppDatabase.getInstance(app))

    private val query: MutableLiveData<String> = MutableLiveData("")
    val notes: LiveData<List<Note>> = query.switchMap { q: String ->
        if (q.isBlank()) repo.observeNotes() else repo.searchNotes(q)
    }

    // PUBLIC_INTERFACE
    fun setQuery(q: String) {
        /** Update search query to filter notes. */
        query.value = q
    }

    // PUBLIC_INTERFACE
    fun saveNote(
        id: Long? = null,
        title: String,
        content: String,
        categoryId: Long?,
        tags: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val now = System.currentTimeMillis()
            val note = Note(
                id = id ?: 0L,
                title = title,
                content = content,
                categoryId = categoryId,
                tags = tags,
                updatedAt = now
            )
            repo.saveNote(note)
        }
    }

    // PUBLIC_INTERFACE
    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteNote(note)
        }
    }
}
