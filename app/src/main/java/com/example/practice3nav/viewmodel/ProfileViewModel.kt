package com.example.practice3nav.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.practice3nav.model.User


class ProfileViewModel : ViewModel() {

    var user = User(
        name = "Aruzhan Kozhakhmetova",
        username = "aru",
        gender = "Female",
        phone = "",
        email = "aru@example.com"
    )
        private set

    // ✅ Аватар URI (галереядан таңдалған фото)
    var avatarUri: Uri? = null
        private set

    fun updateAvatar(uri: Uri?) {
        avatarUri = uri
    }

    fun updateUser(
        name: String,
        username: String,
        gender: String,
        phone: String,
        email: String
    ) {
        user = user.copy(
            name = name,
            username = username,
            gender = gender,
            phone = phone,
            email = email
        )
    }
}
