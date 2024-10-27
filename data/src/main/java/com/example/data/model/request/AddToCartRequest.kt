package com.example.data.model.request

import com.example.domain.model.request.AddCartRequestModel
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequest(
    val productId: Int,
    val quantity: Int
) {
    companion object {
        fun fromCartRequestModel(addCartRequestModel: com.example.domain.model.request.AddCartRequestModel) = AddToCartRequest(
            productId = addCartRequestModel.productId,
            quantity = addCartRequestModel.quantity,
        )
    }
}