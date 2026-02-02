package com.example.practice3nav.ui.fragments

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.practice3nav.R

public class ProfileFragmentDirections private constructor() {
  public companion object {
    public fun actionProfileToEditProfile(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profile_to_editProfile)

    public fun actionProfileToNotification(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profile_to_notification)

    public fun actionProfileToAddress(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profile_to_address)

    public fun actionProfileToChangePassword(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profile_to_changePassword)
  }
}
