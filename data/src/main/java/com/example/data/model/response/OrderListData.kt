package com.example.data.model.response

import com.example.domain.model.OrdersData
import kotlinx.serialization.Serializable

@Serializable
data class OrderListData(
    val id: Int,
    val items: List<OrderItem>,
    val orderDate: String,
    val status: String,
    val totalAmount: Double,
    val userId: Int
) {
    fun toDomainResponse(): com.example.domain.model.OrdersData {
        return com.example.domain.model.OrdersData(
            id = id,
            items = items.map { it.toDomainResponse() },
            orderDate = orderDate,
            status = status,
            totalAmount = totalAmount,
            userId = userId
        )
    }
}