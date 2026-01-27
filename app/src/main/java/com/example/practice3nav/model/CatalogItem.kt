package com.example.practice3nav.model

data class CatalogItem(
    val id: Int,
    val title: String,
    val desc: String,
    val price: Double,
    val imageResId: Int, // СУРЕТ ҮШІН ОСЫ ЖОЛ МІНДЕТТІ
    val isFav: Boolean
)