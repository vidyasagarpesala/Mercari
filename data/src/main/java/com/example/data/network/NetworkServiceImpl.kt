package com.example.data.network

import com.example.data.model.CategoryDataModel
import com.example.data.model.DataProductModel
import com.example.data.model.request.AddToCartRequest
import com.example.data.model.request.AddressDataModel
import com.example.data.model.response.CartResponse
import com.example.data.model.response.CartSummaryResponse
import com.example.data.model.response.CategoriesListResponse
import com.example.data.model.response.OrdersListResponse
import com.example.data.model.response.PlaceOrderResponse
import com.example.data.model.response.ProductListResponse
import com.example.domain.model.AddressDomainModel
import com.example.domain.model.CartItemModel
import com.example.domain.model.CartModel
import com.example.domain.model.CartSummary
import com.example.domain.model.CategoriesListModel
import com.example.domain.model.OrdersListModel
import com.example.domain.model.Product
import com.example.domain.model.ProductListModel
import com.example.domain.model.request.AddCartRequestModel
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import io.ktor.utils.io.errors.IOException

class NetworkServiceImpl(val client: HttpClient) : com.example.domain.network.NetworkService {
    private val baseUrl = "https://ecommerce-ktor-4641e7ff1b63.herokuapp.com/v2"
    override suspend fun getProducts(category: Int?): com.example.domain.network.ResultWrapper<com.example.domain.model.ProductListModel> {
        val url =
            if (category != null) "$baseUrl/products/category/$category" else "$baseUrl/products"
        return makeWebRequest(url = url,
            method = HttpMethod.Get,
            mapper = { dataModels: ProductListResponse ->
                dataModels.toProductList()
            })
    }

    override suspend fun getCategories(): com.example.domain.network.ResultWrapper<com.example.domain.model.CategoriesListModel> {
        val url = "$baseUrl/categories"
        return makeWebRequest(url = url,
            method = HttpMethod.Get,
            mapper = { categories: CategoriesListResponse ->
                categories.toCategoriesList()
            })
    }

    override suspend fun addProductToCart(request: com.example.domain.model.request.AddCartRequestModel): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel> {
        val url = "$baseUrl/cart/1"
        return makeWebRequest(url = url,
            method = HttpMethod.Post,
            body = AddToCartRequest.fromCartRequestModel(request),
            mapper = { cartItem: com.example.data.model.response.CartResponse ->
                cartItem.toCartModel()
            })
    }

    override suspend fun getCart(): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel> {
        val url = "$baseUrl/cart/1"
        return makeWebRequest(url = url,
            method = HttpMethod.Get,
            mapper = { cartItem: com.example.data.model.response.CartResponse ->
                cartItem.toCartModel()
            })
    }

    override suspend fun updateQuantity(cartItemModel: com.example.domain.model.CartItemModel): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel> {
        val url = "$baseUrl/cart/1/${cartItemModel.id}"
        return makeWebRequest(url = url,
            method = HttpMethod.Put,
            body = AddToCartRequest(
                productId = cartItemModel.productId,
                quantity = cartItemModel.quantity
            ),
            mapper = { cartItem: com.example.data.model.response.CartResponse ->
                cartItem.toCartModel()
            })
    }

    override suspend fun deleteItem(cartItemId: Int, userId: Int): com.example.domain.network.ResultWrapper<com.example.domain.model.CartModel> {
        val url = "$baseUrl/cart/$userId/$cartItemId"
        return makeWebRequest(url = url,
            method = HttpMethod.Delete,
            mapper = { cartItem: com.example.data.model.response.CartResponse ->
                cartItem.toCartModel()
            })
    }

    override suspend fun getCartSummary(userId: Int): com.example.domain.network.ResultWrapper<com.example.domain.model.CartSummary> {
        val url = "$baseUrl/checkout/$userId/summary"
        return makeWebRequest(url = url,
            method = HttpMethod.Get,
            mapper = { cartSummary: CartSummaryResponse ->
                cartSummary.toCartSummary()
            })
    }

    override suspend fun placeOrder(address: com.example.domain.model.AddressDomainModel, userId: Int): com.example.domain.network.ResultWrapper<Long> {
        val dataModel = AddressDataModel.fromDomainAddress(address)
        val url = "$baseUrl/orders/$userId"
        return makeWebRequest(url = url,
            method = HttpMethod.Post,
            body = dataModel,
            mapper = { orderRes: PlaceOrderResponse ->
                orderRes.data.id
            })
    }

    override suspend fun getOrderList(): com.example.domain.network.ResultWrapper<com.example.domain.model.OrdersListModel> {
        val url = "$baseUrl/orders/1"
        return makeWebRequest(url = url,
            method = HttpMethod.Get,
            mapper = { ordersResponse: OrdersListResponse ->
                ordersResponse.toDomainResponse()
            })
    }

    suspend inline fun <reified T, R> makeWebRequest(
        url: String,
        method: HttpMethod,
        body: Any? = null,
        headers: Map<String, String> = emptyMap(),
        parameters: Map<String, String> = emptyMap(),
        noinline mapper: ((T) -> R)? = null
    ): com.example.domain.network.ResultWrapper<R> {
        return try {
            val response = client.request(url) {
                this.method = method
                // Apply query parameters
                url {
                    this.parameters.appendAll(Parameters.build {
                        parameters.forEach { (key, value) ->
                            append(key, value)
                        }
                    })
                }
                // Apply headers
                headers.forEach { (key, value) ->
                    header(key, value)
                }
                // Set body for POST, PUT, etc.
                if (body != null) {
                    setBody(body)
                }

                // Set content type
                contentType(ContentType.Application.Json)
            }.body<T>()
            val result: R = mapper?.invoke(response) ?: response as R
            com.example.domain.network.ResultWrapper.Success(result)
        } catch (e: ClientRequestException) {
            com.example.domain.network.ResultWrapper.Failure(e)
        } catch (e: ServerResponseException) {
            com.example.domain.network.ResultWrapper.Failure(e)
        } catch (e: IOException) {
            com.example.domain.network.ResultWrapper.Failure(e)
        } catch (e: Exception) {
            com.example.domain.network.ResultWrapper.Failure(e)
        }
    }

}