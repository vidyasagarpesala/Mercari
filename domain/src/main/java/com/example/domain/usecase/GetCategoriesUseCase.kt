package com.example.domain.usecase

import com.example.domain.repository.CategoryRepository

class GetCategoriesUseCase (private val repository: com.example.domain.repository.CategoryRepository) {
    suspend fun execute() = repository.getCategories()
}