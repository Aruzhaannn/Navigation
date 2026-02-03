package com.example.practice3nav.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.practice3nav.R
import com.example.practice3nav.databinding.FragmentProfileBinding
import com.example.practice3nav.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by activityViewModels()

    // 📸 Галереядан фото таңдау
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                viewModel.updateAvatar(uri)
                binding.imgAvatar.setImageURI(uri)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 👤 Пайдаланушы деректері
        binding.tvName.text = viewModel.user.name
        binding.tvRole.text = "${viewModel.user.gender} • ${viewModel.user.email}"

        // 📸 АВАТАРДЫ БАСҚАНДА — ФОТО ТАҢДАУ
        binding.imgAvatar.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        // ✏️ Edit Profile
        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_editProfile)
        }

        // 🔔 Notification
        binding.btnNotification.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_notification)
        }

        // 📦 Address
        binding.btnAddress.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_address)
        }

        // 🔒 Change Password
        binding.btnChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_changePassword)
        }

        // 🚪 Sign Out
        binding.btnSignOut.setOnClickListener {
            Toast.makeText(requireContext(), "Sign Out", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        // 🌙 Dark / ☀ Light mode
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        binding.switchDarkMode.isChecked =
            currentMode == AppCompatDelegate.MODE_NIGHT_YES

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            val mode = if (isChecked)
                AppCompatDelegate.MODE_NIGHT_YES
            else
                AppCompatDelegate.MODE_NIGHT_NO

            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }

    // ✅ ЭКРАНҒА ҚАЙТА КЕЛГЕНДЕ ФОТО + ДЕРЕК ЖАҢАРАДЫ
    override fun onResume() {
        super.onResume()

        binding.tvName.text = viewModel.user.name
        binding.tvRole.text = "${viewModel.user.gender} • ${viewModel.user.email}"

        viewModel.avatarUri?.let {
            binding.imgAvatar.setImageURI(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
