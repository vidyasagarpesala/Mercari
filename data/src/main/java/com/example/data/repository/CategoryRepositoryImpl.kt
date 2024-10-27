package com.example.data.repository

import com.example.data.model.CategoryDataModel
import com.example.domain.model.CategoriesListModel
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.CategoryRepository

class CategoryRepositoryImpl(val networkService: com.example.domain.network.NetworkService) :
    com.example.domain.repository.CategoryRepository {
    override suspend fun getCategories(): com.example.domain.network.ResultWrapper<com.example.domain.model.CategoriesListModel> {
        return networkService.getCategories()
    }
}