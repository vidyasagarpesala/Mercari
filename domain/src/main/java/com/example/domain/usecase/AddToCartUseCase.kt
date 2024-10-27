package com.example.domain.usecase

import com.example.domain.model.request.AddCartRequestModel
import com.example.domain.repository.CartRepository

class AddToCartUseCase(private val cartRepository: com.example.domain.repository.CartRepository) {
    suspend fun execute(request: com.example.domain.model.request.AddCartRequestModel) = cartRepository.addProductToCart(request)
}