package com.example.domain.model

data class SummaryData(
    val discount: Double,
    val items: List<com.example.domain.model.CartItemModel>,
    val shipping: Double,
    val subtotal: Double,
    val tax: Double,
    val total: Double
)