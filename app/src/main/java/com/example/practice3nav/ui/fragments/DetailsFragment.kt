package com.example.practice3nav.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practice3nav.R
import com.example.practice3nav.databinding.FragmentDetailsBinding
import com.example.practice3nav.viewmodel.CatalogViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: CatalogViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        val itemId = args.itemId

        viewModel.getItemById(itemId).observe(viewLifecycleOwner) { item ->
            if (item == null) return@observe

            binding.itemTitle.text = item.title
            binding.itemDesc.text = item.desc
            binding.itemPrice.text = "Цена: ${"%.0f".format(item.price)} тг"
            binding.favButton.text = if (item.isFav) {
                getString(R.string.remove_fav)
            } else {
                getString(R.string.toggle_fav)
            }
        }

        binding.favButton.setOnClickListener {
            viewModel.toggleFav(itemId)
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
