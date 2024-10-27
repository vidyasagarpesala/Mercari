package com.example.shopper.screenPages

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onChildAt
import com.example.shopper.screenPages.CartScreenPage.Companion.PRODUCT_NAME
import com.example.shopper.screenPages.CartScreenPage.Companion.PRODUCT_PRICE
import com.example.shopper.utils.ActionHelperUtil

class CartSummaryScreen(private val composeTestRule: ComposeTestRule)  {

    private val actionHelperUtil = ActionHelperUtil(composeTestRule, CART_SUMMARY_SCREEN)


    companion object {
        const val CART_SUMMARY_SCREEN = "Cart Summary Screen"
        const val CART_SUMMARY_HEADER = "Cart Summary Header"
        const val ORDER_PLACED = "Order Placed:"
        const val CONTINUE_SHOPPING = "Continue Shopping"
        const val CHECKOUT_BUTTON = "Checkout_button"
    }

    fun validateCartSummaryScreen() {
        actionHelperUtil.isElementDisplayed(CART_SUMMARY_HEADER)
    }


    fun validateCartSummaryItems(expectedProducts: List<Pair<String, Double>>) {
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

    fun validateOrderIsPlacedSuccess(){
        actionHelperUtil.click(ORDER_PLACED)
        actionHelperUtil.click(CONTINUE_SHOPPING)
    }

}