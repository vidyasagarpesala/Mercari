package com.example.domain.usecase

import com.example.domain.repository.CartRepository

class CartSummaryUseCase (private val repository: com.example.domain.repository.CartRepository) {
    suspend fun execute(userId: Int) = repository.getCartSummary(userId)
}