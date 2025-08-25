package org.example.app.ui.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.app.data.AppDatabase
import org.example.app.data.entity.Category
import org.example.app.data.repo.CategoriesRepository

/**
 * PUBLIC_INTERFACE
 * CategoriesViewModel for observing and modifying categories.
 */
class CategoriesViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = CategoriesRepository(AppDatabase.getInstance(app))
    val categories: LiveData<List<Category>> = repo.observeCategories()

    // PUBLIC_INTERFACE
    fun addCategory(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            repo.save(Category(name = name))
        }
    }

    // PUBLIC_INTERFACE
    fun deleteCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(category)
        }
    }
}
