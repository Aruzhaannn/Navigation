package com.example.practice3nav.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.practice3nav.R

class DetailsFragment : Fragment(R.layout.fragment_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ CatalogFragment-тен келген itemId осылай алынады
        val itemId = requireArguments().getInt("itemId")

        // itemId-мен әрі қарай жұмыс жасайсың
        // мысалы: viewModel.loadItem(itemId)
    }
}
