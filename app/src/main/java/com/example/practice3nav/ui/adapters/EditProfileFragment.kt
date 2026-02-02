package com.example.practice3nav.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.practice3nav.databinding.FragmentEditProfileBinding
import com.example.practice3nav.viewmodel.ProfileViewModel

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    // ✅ ЕҢ МАҢЫЗДЫ ЖЕР
    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔙 Toolbar back
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        // 👤 Алдын ала мәліметтерді толтырып қоямыз
        binding.etName.setText(viewModel.user.name)
        binding.etUsername.setText(viewModel.user.username)
        binding.etPhone.setText(viewModel.user.phone)
        binding.etEmail.setText(viewModel.user.email)

        // 🚻 Gender dropdown
        val genders = listOf("Male", "Female", "Other")
        val genderAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, genders)
        binding.spGender.setAdapter(genderAdapter)
        binding.spGender.setText(viewModel.user.gender, false)

        binding.spGender.setOnClickListener { binding.spGender.showDropDown() }


        // 💾 Save
        binding.btnSave.setOnClickListener {

            // ✅ ViewModel-ды жаңартамыз
            viewModel.updateUser(
                name = binding.etName.text.toString(),
                username = binding.etUsername.text.toString(),
                gender = binding.spGender.text.toString(),
                phone = binding.etPhone.text.toString(),
                email = binding.etEmail.text.toString()
            )

            Toast.makeText(requireContext(), "Saved ✅", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
