package com.example.domain.repository

import com.example.domain.model.CartItemModel
import com.example.domain.model.CartModel
import com.example.domain.model.CartSummary
import com.example.domain.model.request.AddCartRequestModel
import com.example.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        request: com.example.domain.model.request.AddCartRequestModel
    ): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel>

    suspend fun getCart(): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel>
    suspend fun updateQuantity(cartItemModel: com.example.domain.model.CartItemModel): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Int): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel>
    suspend fun getCartSummary(userId: Int): com.example.domain.network.ResultWrapper<com.example.domain.model.CartSummary>
}