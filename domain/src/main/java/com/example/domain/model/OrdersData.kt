package com.example.domain.model

data class OrdersData(
    val id: Int,
    val items: List<com.example.domain.model.OrderProductItem>,
    val orderDate: String,
    val status: String,
    val totalAmount: Double,
    val userId: Int
)