package com.example.domain.di

import org.koin.dsl.module

val domainModule = module {
    includes(com.example.domain.di.useCaseModule)
}