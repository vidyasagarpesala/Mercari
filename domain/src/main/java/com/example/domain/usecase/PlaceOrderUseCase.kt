package com.example.domain.usecase

import com.example.domain.model.AddressDomainModel
import com.example.domain.repository.OrderRepository

class PlaceOrderUseCase(val orderRepository: com.example.domain.repository.OrderRepository) {
    suspend fun execute(addressDomainModel: com.example.domain.model.AddressDomainModel) =
        orderRepository.placeOrder(addressDomainModel)
}