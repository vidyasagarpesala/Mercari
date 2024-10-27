package com.example.domain.usecase

import com.example.domain.model.OrdersListModel
import com.example.domain.repository.OrderRepository

class OrderListUseCase(
    private val repository: com.example.domain.repository.OrderRepository
) {
    suspend fun execute() = repository.getOrderList()
}