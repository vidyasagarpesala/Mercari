package com.example.domain.usecase

import com.example.domain.model.CartItemModel
import com.example.domain.repository.CartRepository

class DeleteProductUseCase(private val cartRepository: com.example.domain.repository.CartRepository) {
    suspend fun execute(cartItemId: Int, userId: Int) = cartRepository.deleteItem(cartItemId, userId)
}