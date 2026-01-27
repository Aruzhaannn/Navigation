package com.example.practice3nav.ui.fragments

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.practice3nav.R
import kotlin.Int

public class CatalogFragmentDirections private constructor() {
  private data class ActionCatalogToDetails(
    public val itemId: Int,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_catalog_to_details

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("itemId", this.itemId)
        return result
      }
  }

  public companion object {
    public fun actionCatalogToDetails(itemId: Int): NavDirections = ActionCatalogToDetails(itemId)
  }
}
