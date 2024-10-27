package com.example.domain.network

import com.example.domain.model.AddressDomainModel
import com.example.domain.model.CartItemModel
import com.example.domain.model.CartModel
import com.example.domain.model.CartSummary
import com.example.domain.model.CategoriesListModel
import com.example.domain.model.OrdersListModel
import com.example.domain.model.ProductListModel
import com.example.domain.model.request.AddCartRequestModel

interface NetworkService {
    suspend fun getProducts(category: Int?): com.example.domain.network.ResultWrapper<com.example.domain.model.ProductListModel>
    suspend fun getCategories(): com.example.domain.network.ResultWrapper<com.example.domain.model.CategoriesListModel>

    suspend fun addProductToCart(
        request: com.example.domain.model.request.AddCartRequestModel
    ): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel>

    suspend fun getCart(): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel>
    suspend fun updateQuantity(cartItemModel: com.example.domain.model.CartItemModel): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Int): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel>
    suspend fun getCartSummary(userId: Int): com.example.domain.network.ResultWrapper<com.example.domain.model.CartSummary>
    suspend fun placeOrder(address: com.example.domain.model.AddressDomainModel, userId: Int): com.example.domain.network.ResultWrapper<Long>
    suspend fun getOrderList(): com.example.domain.network.ResultWrapper<com.example.domain.model.OrdersListModel>
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : com.example.domain.network.ResultWrapper<T>()
    data class Failure(val exception: Exception) : com.example.domain.network.ResultWrapper<Nothing>()
}