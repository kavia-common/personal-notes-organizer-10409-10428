package org.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * PUBLIC_INTERFACE
 * MainActivity
 * This is the single-activity host for the Notes app. It contains a BottomNavigationView
 * with three destinations: Notes, Categories, and Settings, and a FloatingActionButton
 * used to create a new note.
 *
 * Parameters: none
 * Returns: none (Activity lifecycle)
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NotesApp_Light_Base) // ensure light theme applied before setContentView
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)

        val fab = findViewById<FloatingActionButton>(R.id.fab_add_note)
        fab.setOnClickListener {
            // Navigate to NoteEditorFragment for creating a new note
            val actionRes = R.id.action_global_noteEditorFragment
            navController.navigate(actionRes)
        }
    }

    // PUBLIC_INTERFACE
    fun navigateToSettings() {
        /** Navigate to the settings tab programmatically if needed. */
        findNavController(R.id.nav_host_fragment).navigate(R.id.settingsFragment)
    }
}
