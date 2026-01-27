package com.example.practice3nav.ui.fragments

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class DetailsFragmentArgs(
  public val itemId: Int,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("itemId", this.itemId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("itemId", this.itemId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DetailsFragmentArgs {
      bundle.setClassLoader(DetailsFragmentArgs::class.java.classLoader)
      val __itemId : Int
      if (bundle.containsKey("itemId")) {
        __itemId = bundle.getInt("itemId")
      } else {
        throw IllegalArgumentException("Required argument \"itemId\" is missing and does not have an android:defaultValue")
      }
      return DetailsFragmentArgs(__itemId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): DetailsFragmentArgs {
      val __itemId : Int?
      if (savedStateHandle.contains("itemId")) {
        __itemId = savedStateHandle["itemId"]
        if (__itemId == null) {
          throw IllegalArgumentException("Argument \"itemId\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"itemId\" is missing and does not have an android:defaultValue")
      }
      return DetailsFragmentArgs(__itemId)
    }
  }
}
