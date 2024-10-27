package com.example.data.di

import com.example.data.repository.CategoryRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.domain.repository.CartRepository
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<com.example.domain.repository.ProductRepository> { ProductRepositoryImpl(get()) }
    single<com.example.domain.repository.CategoryRepository> { com.example.data.repository.CategoryRepositoryImpl(get()) }
    single<com.example.domain.repository.CartRepository> { com.example.data.repository.CartRepositoryImpl(get()) }
    single<com.example.domain.repository.OrderRepository> { com.example.data.repository.OrderRepositoryImpl(get()) }
}