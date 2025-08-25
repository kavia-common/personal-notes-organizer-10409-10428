package org.example.app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.example.app.R

/**
 * PUBLIC_INTERFACE
 * Simple authentication screen allowing sign-in/register (same behavior for demo).
 * Navigates to notes screen on success.
 */
class AuthFragment : Fragment() {
    private val vm: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = view.findViewById<EditText>(R.id.input_email)
        val password = view.findViewById<EditText>(R.id.input_password)
        val signIn = view.findViewById<Button>(R.id.btn_sign_in)
        val register = view.findViewById<Button>(R.id.btn_register)

        val nav = findNavController()
        vm.isAuthenticated.observe(viewLifecycleOwner) { ok ->
            if (ok == true) nav.navigate(R.id.action_authFragment_to_notesListFragment)
        }

        val click: (View) -> Unit = {
            vm.login(email.text.toString(), password.text.toString())
        }
        signIn.setOnClickListener(click)
        register.setOnClickListener(click)
    }
}
