package com.example.domain.usecase

import com.example.domain.model.CartItemModel
import com.example.domain.repository.CartRepository

class UpdateQuantityUseCase(private val cartRepository: com.example.domain.repository.CartRepository) {
    suspend fun execute(cartItemModel: com.example.domain.model.CartItemModel) = cartRepository.updateQuantity(cartItemModel)
}