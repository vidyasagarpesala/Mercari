package com.example.domain.repository

import com.example.domain.model.AddressDomainModel
import com.example.domain.model.OrdersListModel
import com.example.domain.network.ResultWrapper

interface OrderRepository {
    suspend fun placeOrder(addressDomainModel: com.example.domain.model.AddressDomainModel): com.example.domain.network.ResultWrapper<Long>
    suspend fun getOrderList(): com.example.domain.network.ResultWrapper<com.example.domain.model.OrdersListModel>
}