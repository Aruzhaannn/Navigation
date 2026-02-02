package com.example.practice3nav.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        // 👤 Пайдаланушы деректері (алғашқы көрсетілім)
        binding.tvName.text = viewModel.user.name
        binding.tvRole.text = "${viewModel.user.gender} • ${viewModel.user.email}"

        // ✏️ Edit Profile → EditProfileFragment
        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_editProfile)
        }

        // ✅ Notification → NotificationFragment
        binding.btnNotification.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_notification)
        }

        // ✅ Shipping Address → AddressFragment
        binding.btnAddress.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_address)
        }

        // ✅ Change Password → ChangePasswordFragment
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
        binding.switchDarkMode.isChecked = currentMode == AppCompatDelegate.MODE_NIGHT_YES

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            val mode = if (isChecked)
                AppCompatDelegate.MODE_NIGHT_YES
            else
                AppCompatDelegate.MODE_NIGHT_NO

            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }

    // ✅ МІНЕ ОСЫ ЖЕР ҚОСЫЛДЫ: EditProfile-дан қайтқанда UI жаңарады
    override fun onResume() {
        super.onResume()
        binding.tvName.text = viewModel.user.name
        binding.tvRole.text = "${viewModel.user.gender} • ${viewModel.user.email}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
