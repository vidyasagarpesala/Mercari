package com.example.domain.usecase

import com.example.domain.repository.CartRepository

class GetCartUseCase(val cartRepository: com.example.domain.repository.CartRepository) {
    suspend fun execute() = cartRepository.getCart()
}