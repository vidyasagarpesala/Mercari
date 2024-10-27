package com.example.domain.model

data class ProductListModel(
    val products: List<com.example.domain.model.Product>,
    val msg: String
)