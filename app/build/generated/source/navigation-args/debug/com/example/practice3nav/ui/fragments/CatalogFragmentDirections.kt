package com.example.practice3nav.ui.fragments

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.practice3nav.R

public class CatalogFragmentDirections private constructor() {
  public companion object {
    public fun actionCatalogToDetails(): NavDirections =
        ActionOnlyNavDirections(R.id.action_catalog_to_details)

    public fun actionCatalogToProfile(): NavDirections =
        ActionOnlyNavDirections(R.id.action_catalog_to_profile)
  }
}
