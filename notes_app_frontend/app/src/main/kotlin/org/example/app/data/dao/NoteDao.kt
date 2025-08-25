package org.example.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.example.app.data.entity.Note

/**
 * PUBLIC_INTERFACE
 * Room DAO for Note CRUD and queries.
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY updatedAt DESC")
    fun observeAll(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun findById(id: Long): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(note: Note): Long

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :q || '%' OR content LIKE '%' || :q || '%' OR tags LIKE '%' || :q || '%' ORDER BY updatedAt DESC")
    fun search(q: String): LiveData<List<Note>>
}
