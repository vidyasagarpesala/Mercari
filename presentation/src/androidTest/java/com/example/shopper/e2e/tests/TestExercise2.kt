package com.example.shopper.e2e.tests

import androidx.test.espresso.Espresso
import com.example.shopper.screenPages.CartScreenPage
import com.example.shopper.screenPages.HomeScreenPage
import com.example.shopper.screenPages.LoginScreenPage
import com.example.shopper.screenPages.ShippingDetailsScreen
import org.junit.Test


class TestExercise2 : BaseTest() {

    private val loginScreenPage = LoginScreenPage(composeRule)
    private val homeScreenPage = HomeScreenPage(composeRule)
    private val cartScreenPage = CartScreenPage(composeRule)
    private val cartSummaryScreen = com.example.shopper.screenPages.CartSummaryScreen(composeRule)
    private val shippingDetailsScreen = ShippingDetailsScreen(composeRule)


    companion object {
        const val PRODUCT_1_TITLE = "Dell XPS 13"
        const val PRODUCT_1_PRICE = 1200.0
        const val PRODUCT_2_TITLE = "iPhone 14 Pro"
        const val PRODUCT_2_PRICE = 999.0
        const val USERNAME = "Mercari"
        const val PASSWORD = "Mercari@123"
    }

    val expectedProducts = listOf(
        PRODUCT_1_TITLE to PRODUCT_1_PRICE,
        PRODUCT_2_TITLE to PRODUCT_2_PRICE,
    )


    @Test
    fun testHomeScreenHeader() {
        //login using username and password
        loginScreenPage.login(USERNAME, PASSWORD)

        //Validating the home screen elements
        homeScreenPage.validateHomeScreen()

        //Selecting first product from the list
        homeScreenPage.selectProduct(0)

        //Going back to home screen
        Espresso.pressBack()

        //Validating that user lands on  home screen again
        homeScreenPage.validateHomeScreen()

        //Selecting second product from the list
        homeScreenPage.selectProduct(1)

        //Going back to home screen
        Espresso.pressBack()

        //Validating that user lands on  home screen again
        homeScreenPage.validateHomeScreen()

        //Selecting cart button
        homeScreenPage.tapOnCartIcon()

        //Validate cart screen header and added products
        cartScreenPage.validateCartHeader()
        cartScreenPage.validateItemsCountInCart(2)
        cartScreenPage.validateProductNamesAndPrices(expectedProducts)

        //Go to checkout page and add address
        cartScreenPage.tapOnCheckOutButton()

        cartSummaryScreen.validateCartSummaryScreen()
        shippingDetailsScreen.addUserAddress()
        cartSummaryScreen.validateCartSummaryItems(expectedProducts)

        //Tapping on checkout and Verify order is successful
        cartScreenPage.tapOnCheckOutButton()
        cartSummaryScreen.validateOrderIsPlacedSuccess()

    }
}