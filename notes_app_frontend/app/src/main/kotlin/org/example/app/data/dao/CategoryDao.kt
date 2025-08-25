package org.example.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.example.app.data.entity.Category

/**
 * PUBLIC_INTERFACE
 * Room DAO for Category CRUD and queries.
 */
@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun observeAll(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(category: Category): Long

    @Delete
    suspend fun delete(category: Category)
}
