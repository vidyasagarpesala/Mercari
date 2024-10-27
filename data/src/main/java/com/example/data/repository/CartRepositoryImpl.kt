package com.example.data.repository

import com.example.domain.model.CartItemModel
import com.example.domain.model.CartModel
import com.example.domain.model.CartSummary
import com.example.domain.model.request.AddCartRequestModel
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.CartRepository

class CartRepositoryImpl(val networkService: com.example.domain.network.NetworkService) :
    com.example.domain.repository.CartRepository {
    override suspend fun addProductToCart(request: com.example.domain.model.request.AddCartRequestModel): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel> {
        return networkService.addProductToCart(request)
    }

    override suspend fun getCart(): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel> {
        return networkService.getCart()
    }

    override suspend fun updateQuantity(cartItemModel: com.example.domain.model.CartItemModel): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel> {
        return networkService.updateQuantity(cartItemModel)
    }

    override suspend fun deleteItem(cartItemId: Int, userId: Int): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel> {
        return networkService.deleteItem(cartItemId, userId)
    }

    override suspend fun getCartSummary(userId: Int): com.example.domain.network.ResultWrapper<com.example.domain.model.CartSummary> {
        return networkService.getCartSummary(userId)
    }
}