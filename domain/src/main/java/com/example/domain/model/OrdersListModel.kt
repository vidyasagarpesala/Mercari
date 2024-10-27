package com.example.domain.model

data class OrdersListModel(
    val `data`: List<com.example.domain.model.OrdersData>,
    val msg: String
)