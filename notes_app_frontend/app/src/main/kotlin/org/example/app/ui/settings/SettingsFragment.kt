package org.example.app.ui.settings

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.example.app.R
import org.example.app.ui.auth.AuthViewModel

/**
 * PUBLIC_INTERFACE
 * SettingsFragment contains account actions like logout.
 */
class SettingsFragment : Fragment() {
    private val authVm: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            authVm.logout()
            requireActivity().finish()
        }
    }
}
