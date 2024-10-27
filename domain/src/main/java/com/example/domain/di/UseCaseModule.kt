package com.example.domain.di

import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.CartSummaryUseCase
import com.example.domain.usecase.DeleteProductUseCase
import com.example.domain.usecase.GetCartUseCase
import com.example.domain.usecase.GetCategoriesUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.domain.usecase.OrderListUseCase
import com.example.domain.usecase.PlaceOrderUseCase
import com.example.domain.usecase.UpdateQuantityUseCase
import org.koin.dsl.module


val useCaseModule = module {
    factory { com.example.domain.usecase.GetProductUseCase(get()) }
    factory { com.example.domain.usecase.GetCategoriesUseCase(get()) }
    factory { com.example.domain.usecase.AddToCartUseCase(get()) }
    factory { com.example.domain.usecase.GetCartUseCase(get()) }
    factory { com.example.domain.usecase.UpdateQuantityUseCase(get()) }
    factory { com.example.domain.usecase.DeleteProductUseCase(get()) }
    factory { com.example.domain.usecase.CartSummaryUseCase(get()) }
    factory { com.example.domain.usecase.PlaceOrderUseCase(get()) }
    factory { com.example.domain.usecase.OrderListUseCase(get()) }
}