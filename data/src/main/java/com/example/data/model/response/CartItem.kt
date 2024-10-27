package com.example.data.model.response

import com.example.domain.model.CartItemModel
import kotlinx.serialization.Serializable

@Serializable
class CartItem(
    val id: Int,
    val productId: Int,
    val price: Double,
    val imageUrl: String? = null,
    val quantity: Int,
    val productName: String
) {
    fun toCartItemModel(): com.example.domain.model.CartItemModel {
        return com.example.domain.model.CartItemModel(
            id = id,
            productId = productId,
            price = price,
            imageUrl = imageUrl,
            quantity = quantity,
            productName = productName
        )
    }
}