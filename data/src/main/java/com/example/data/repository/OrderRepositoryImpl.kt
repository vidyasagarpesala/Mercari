package com.example.data.repository

import com.example.domain.model.AddressDomainModel
import com.example.domain.model.OrdersListModel
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.OrderRepository

class OrderRepositoryImpl(private val networkService: com.example.domain.network.NetworkService) :
    com.example.domain.repository.OrderRepository {
    override suspend fun placeOrder(addressDomainModel: com.example.domain.model.AddressDomainModel): com.example.domain.network.ResultWrapper<Long> {
        return networkService.placeOrder(addressDomainModel, 1)
    }

    override suspend fun getOrderList(): com.example.domain.network.ResultWrapper<com.example.domain.model.OrdersListModel> {
        return networkService.getOrderList()
    }
}