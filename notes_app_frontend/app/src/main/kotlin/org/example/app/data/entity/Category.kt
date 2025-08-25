package org.example.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * PUBLIC_INTERFACE
 * Category entity for grouping notes.
 */
@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)
