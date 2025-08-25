package org.example.app.data.repo

import androidx.lifecycle.LiveData
import org.example.app.data.AppDatabase
import org.example.app.data.entity.Category

/**
 * PUBLIC_INTERFACE
 * Repository for categories.
 */
class CategoriesRepository(private val db: AppDatabase) {
    fun observeCategories(): LiveData<List<Category>> = db.categoryDao().observeAll()
    suspend fun save(category: Category): Long = db.categoryDao().upsert(category)
    suspend fun delete(category: Category) = db.categoryDao().delete(category)
}
