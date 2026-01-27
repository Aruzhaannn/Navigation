package com.example.practice3nav.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice3nav.databinding.ItemCatalogBinding
import com.example.practice3nav.model.CatalogItem

class CatalogAdapter(
    private val onItemClick: (CatalogItem) -> Unit
) : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private var items: List<CatalogItem> = emptyList()

    fun submitList(newItems: List<CatalogItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val binding = ItemCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class CatalogViewHolder(
        private val binding: ItemCatalogBinding,
        private val onItemClick: (CatalogItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CatalogItem) {
            binding.titleText.text = item.title
            // Бағаны форматтау және шығару
            binding.priceText.text = "Цена: ${"%.0f".format(item.price)} тг"
            // Таңдаулы белгісі
            binding.favMark.text = if (item.isFav) "★" else "☆"

            // СУРЕТТІ ОРНАТУ (осы жол қосылды)
            binding.itemImage.setImageResource(item.imageResId)

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}