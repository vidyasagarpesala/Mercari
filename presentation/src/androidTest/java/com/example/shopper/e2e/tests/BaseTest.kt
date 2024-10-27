package com.example.shopper.e2e.tests

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.shopper.BottomNavigationBar
import com.example.shopper.MainActivity
import com.example.shopper.model.UiProductModel
import com.example.shopper.navigation.CartScreen
import com.example.shopper.navigation.CartSummaryScreen
import com.example.shopper.navigation.HomeScreen
import com.example.shopper.navigation.OrdersScreen
import com.example.shopper.navigation.ProductDetails
import com.example.shopper.navigation.ProfileScreen
import com.example.shopper.navigation.UserAddressRoute
import com.example.shopper.navigation.UserAddressRouteWrapper
import com.example.shopper.navigation.productNavType
import com.example.shopper.navigation.userAddressNavType
import com.example.shopper.ui.feature.cart.CartScreen
import com.example.shopper.ui.feature.home.HomeScreen
import com.example.shopper.ui.feature.orders.OrdersScreen
import com.example.shopper.ui.feature.product_details.ProductDetailsScreen
import com.example.shopper.ui.feature.summary.CartSummaryScreen
import com.example.shopper.ui.feature.user_address.UserAddressScreen
import com.example.shopper.ui.theme.ShopperTheme
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import kotlin.reflect.typeOf

open class BaseTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    open fun setUp() {
        composeRule.setContent {
            ShopperTheme {
                val shouldShowBottomNav = remember {
                    mutableStateOf(true)
                }
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedVisibility(visible = shouldShowBottomNav.value, enter = fadeIn()) {
                            BottomNavigationBar(navController)
                        }

                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavHost(navController = navController, startDestination = HomeScreen) {
                            composable<HomeScreen> {
                                HomeScreen(navController)
                                shouldShowBottomNav.value = true
                            }
                            composable<CartScreen> {
                                shouldShowBottomNav.value = true
                                CartScreen(navController)
                            }
                            composable<OrdersScreen> {
                                shouldShowBottomNav.value = true
                                OrdersScreen()
                            }
                            composable<ProfileScreen> {
                                shouldShowBottomNav.value = true
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Profile")
                                }
                            }
                            composable<CartSummaryScreen> {
                                shouldShowBottomNav.value = false
                                CartSummaryScreen(navController = navController)
                            }
                            composable<ProductDetails>(
                                typeMap = mapOf(typeOf<UiProductModel>() to productNavType)
                            ) {
                                shouldShowBottomNav.value = false
                                val productRoute = it.toRoute<ProductDetails>()
                                ProductDetailsScreen(navController, productRoute.product)
                            }
                            composable<UserAddressRoute>(
                                typeMap = mapOf(typeOf<UserAddressRouteWrapper>() to userAddressNavType)
                            ) {
                                shouldShowBottomNav.value = false
                                val userAddressRoute = it.toRoute<UserAddressRoute>()
                                UserAddressScreen(
                                    navController = navController,
                                    userAddress = userAddressRoute.userAddressWrapper.userAddress
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}