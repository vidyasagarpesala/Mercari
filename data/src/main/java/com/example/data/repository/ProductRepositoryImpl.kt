package com.example.data.repository

import com.example.domain.model.Product
import com.example.domain.model.ProductListModel
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.ProductRepository

class ProductRepositoryImpl(private val networkService: com.example.domain.network.NetworkService) :
    com.example.domain.repository.ProductRepository {
    override suspend fun getProducts(category: Int?): com.example.domain.network.ResultWrapper<com.example.domain.model.ProductListModel> {
        return networkService.getProducts(category)
    }
}