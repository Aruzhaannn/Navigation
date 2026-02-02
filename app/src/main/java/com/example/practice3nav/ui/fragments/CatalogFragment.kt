package com.example.practice3nav.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice3nav.R
import com.example.practice3nav.databinding.FragmentCatalogBinding
import com.example.practice3nav.ui.adapters.CatalogAdapter
import com.example.practice3nav.viewmodel.CatalogViewModel

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatalogViewModel by activityViewModels()

    private lateinit var adapter: CatalogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCatalogBinding.bind(view)

        // ✅ Profile бетіне өту
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_catalog_to_profile)
        }

        // ✅ Каталог item → Details (SafeArgsсыз, Bundle арқылы)
        adapter = CatalogAdapter { item ->
            val bundle = Bundle().apply {
                putInt("itemId", item.id)
            }
            findNavController().navigate(R.id.action_catalog_to_details, bundle)
        }

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
