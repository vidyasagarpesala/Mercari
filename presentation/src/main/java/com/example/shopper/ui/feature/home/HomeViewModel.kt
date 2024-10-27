package com.example.shopper.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductUseCase: com.example.domain.usecase.GetProductUseCase,
    private val categoryUseCase: com.example.domain.usecase.GetCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            /**
             *  -- To Launch all request's at the same time to get data from server --
             * async{} will launch all the request's in parallel at the same time
             * and await() will be wait for the every request result response
             * using this approach our network calling will be fast and it will take less time to
             * show the data on screen to user
             */
            val featured = async {
                getProducts(1)
            }
            val popularProducts = async {
                getProducts(2)
            }

            val categories = async {
                getCategory()
            }
            val featuredResponse = featured.await()
            val popularProductsResponse = popularProducts.await()
            val categoriesResponse = categories.await()

            if (featuredResponse.isEmpty() && popularProductsResponse.isEmpty() && categoriesResponse.isNotEmpty()) {
                _uiState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }
            _uiState.value = HomeScreenUIEvents.Success(
                featuredResponse,
                popularProductsResponse,
                categoriesResponse
            )
        }
    }

    private suspend fun getCategory(): List<String> {
        categoryUseCase.execute().let { result ->
            when (result) {
                is com.example.domain.network.ResultWrapper.Success -> {
                    return (result).value.categories.map { it.title }
                }

                is com.example.domain.network.ResultWrapper.Failure -> {
                    return emptyList()
                }
            }
        }
    }

    private suspend fun getProducts(category: Int?): List<com.example.domain.model.Product> {
        getProductUseCase.execute(category).let { result ->
            when (result) {
                is com.example.domain.network.ResultWrapper.Success -> {
                    return (result).value.products
                }

                is com.example.domain.network.ResultWrapper.Failure -> {
                    return emptyList()
                }
            }
        }
    }
}

sealed class HomeScreenUIEvents {
    data object Loading : HomeScreenUIEvents()
    data class Success(
        val featured: List<com.example.domain.model.Product>,
        val popularProducts: List<com.example.domain.model.Product>,
        val categories: List<String>
    ) :
        HomeScreenUIEvents()

    data class Error(val message: String) : HomeScreenUIEvents()
}