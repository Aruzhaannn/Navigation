package com.example.practice3nav.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.practice3nav.R
import com.example.practice3nav.model.CatalogItem

class CatalogViewModel : ViewModel() {

    private val _items = MutableLiveData<List<CatalogItem>>(createInitialItems())
    val items: LiveData<List<CatalogItem>> = _items

    fun toggleFav(id: Int) {
        val current = _items.value.orEmpty()
        val updated = current.map { item ->
            if (item.id == id) item.copy(isFav = !item.isFav) else item
        }
        _items.value = updated
    }

    fun getItemById(id: Int): LiveData<CatalogItem?> {
        return items.switchMap { list: List<CatalogItem> ->
            val result = MutableLiveData<CatalogItem?>()
            result.value = list.firstOrNull { item -> item.id == id }
            result
        }
    }

    private fun createInitialItems(): List<CatalogItem> {
        return listOf(
            CatalogItem(1, "Кради как художник", "Остин Клеон — как находить идеи и вдохновение", 8900.0, R.drawable.book_image_1, false),
            CatalogItem(2, "Путь художника", "Джулия Кэмерон — пробуждение творческих способностей", 11900.0, R.drawable.book_image_2, false),
            CatalogItem(3, "Думай как дизайнер", "Тим Браун — креативное мышление и инновации", 14900.0, R.drawable.book_image_3, false),
            CatalogItem(4, "Гениально просто", "Кен Сигал — сила простоты в идеях", 12900.0, R.drawable.book_image_4, false),
            CatalogItem(5, "Big Magic", "Элизабет Гилберт — жизнь без страха и с вдохновением", 13900.0, R.drawable.book_image_1, false),
            CatalogItem(6, "Как работает креативность", "Дэвид Иглмен — нейронаука идей", 15900.0, R.drawable.book_image_2, false),
            CatalogItem(7, "Искусство мыслить масштабно", "Дэвид Шварц — смелые цели и мышление", 10900.0, R.drawable.book_image_3, false),
            CatalogItem(8, "Оригиналы", "Адам Грант — как рождаются нестандартные идеи", 14900.0, R.drawable.book_image_4, false),
            CatalogItem(9, "Креативная уверенность", "Том и Дэвид Келли — вера в свои идеи", 12900.0, R.drawable.book_image_1, false),
            CatalogItem(10, "Поток", "Михай Чиксентмихайи — состояние вдохновения", 16900.0, R.drawable.book_image_2, false)
        )
    }
}