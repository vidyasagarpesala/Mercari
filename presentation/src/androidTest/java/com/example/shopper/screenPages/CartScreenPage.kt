package com.example.shopper.screenPages

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onChildAt
import com.example.shopper.utils.ActionHelperUtil

class CartScreenPage (private val composeTestRule: ComposeTestRule){

    private val actionHelperUtil = ActionHelperUtil(composeTestRule, CART_SCREEN)


    companion object {
        const val CART_SCREEN = "Cart Screen"
        const val CART_HEADER = "Cart"
        const val CHECKOUT_BUTTON = "Checkout Button"
        const val PRODUCT_IMAGE = "product image"
        const val PRODUCT_NAME = "Product Title"
        const val PRODUCT_PRICE = "Product Price"
    }

    fun validateCartHeader() {
        actionHelperUtil.isElementDisplayed(CART_HEADER)
    }

    fun validateItemsCountInCart(count: Int) {
        val itemCount = composeTestRule.onAllNodesWithContentDescription(PRODUCT_IMAGE).fetchSemanticsNodes().size
        assert(itemCount == count)
    }

    fun validateProductNamesAndPrices(expectedProducts: List<Pair<String, Double>>) {
        expectedProducts.forEachIndexed { index, (expectedName, expectedPrice) ->
            //Validate product Name
            val nameNode = composeTestRule
                .onAllNodesWithContentDescription(PRODUCT_NAME)
                .get(index)
                ?.onChildAt(1)
            nameNode?.assert(hasText(expectedName))

            // Validate product price
            val priceNode = composeTestRule
                .onAllNodesWithContentDescription(PRODUCT_PRICE)
                .get(index)

                ?.onChildAt(2)
            priceNode?.assert(hasText("$${expectedPrice}"))
        }
    }

    fun tapOnCheckOutButton() {
        actionHelperUtil.click(CHECKOUT_BUTTON)
    }
}