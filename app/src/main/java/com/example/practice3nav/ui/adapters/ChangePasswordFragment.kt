package com.example.practice3nav.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.practice3nav.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {

    private val PREFS = "app_prefs"
    private val KEY_PASSWORD = "user_password"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Back кнопка
        view.findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val etCurrent = view.findViewById<TextInputEditText>(R.id.etCurrent)
        val etNew = view.findViewById<TextInputEditText>(R.id.etNew)
        val etConfirm = view.findViewById<TextInputEditText>(R.id.etConfirm)
        val btnUpdate = view.findViewById<MaterialButton>(R.id.btnUpdate)

        // ✅ Егер пароль әлі орнатылмаған болса, default қоямыз
        val prefs = requireContext().getSharedPreferences(PREFS, 0)
        if (!prefs.contains(KEY_PASSWORD)) {
            prefs.edit().putString(KEY_PASSWORD, "123456").apply() // бастапқы пароль
        }

        btnUpdate.setOnClickListener {
            val current = etCurrent.text?.toString().orEmpty()
            val newPass = etNew.text?.toString().orEmpty()
            val confirm = etConfirm.text?.toString().orEmpty()

            val savedPass = prefs.getString(KEY_PASSWORD, "123456") ?: "123456"

            if (current.isBlank() || newPass.isBlank() || confirm.isBlank()) {
                Toast.makeText(requireContext(), "Барлық өрісті толтыр", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (current != savedPass) {
                Toast.makeText(requireContext(), "Current password қате!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPass.length < 6) {
                Toast.makeText(requireContext(), "Жаңа пароль кемі 6 символ болсын", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPass != confirm) {
                Toast.makeText(requireContext(), "Парольдар сәйкес емес", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Міне пароль расымен сақталады
            prefs.edit().putString(KEY_PASSWORD, newPass).apply()

            Toast.makeText(requireContext(), "Пароль сәтті өзгертілді ✅", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }
}
