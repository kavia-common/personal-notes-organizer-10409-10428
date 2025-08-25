package org.example.app.data.repo

import androidx.lifecycle.LiveData
import org.example.app.data.AppDatabase
import org.example.app.data.entity.Note

/**
 * PUBLIC_INTERFACE
 * Repository for notes providing an abstraction layer over Room.
 */
class NotesRepository(private val db: AppDatabase) {

    fun observeNotes(): LiveData<List<Note>> = db.noteDao().observeAll()

    fun searchNotes(q: String): LiveData<List<Note>> = db.noteDao().search(q)

    suspend fun getNote(id: Long): Note? = db.noteDao().findById(id)

    suspend fun saveNote(note: Note): Long = db.noteDao().upsert(note)

    suspend fun deleteNote(note: Note) = db.noteDao().delete(note)
}
