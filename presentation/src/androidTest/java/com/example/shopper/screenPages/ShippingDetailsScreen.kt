package com.example.shopper.screenPages

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.example.shopper.utils.ActionHelperUtil

class ShippingDetailsScreen(private val composeTestRule: ComposeTestRule) {
    private val actionHelperUtil = ActionHelperUtil(composeTestRule, SHIPPING_SCREEN)

    companion object {
        const val SHIPPING_SCREEN = "Shipping Screen"
        const val SHIPPING_ADDRESS = "Shipping Address"
        const val ADDRESS_LINE = "Address Line"
        const val CITY = "City"
        const val STATE = "State"
        const val POSTAL_CODE = "Postal Code"
        const val COUNTRY = "Country"
        const val SAVE_BUTTON = "Save Button"
    }



    fun addUserAddress(){
        actionHelperUtil.click(SHIPPING_ADDRESS)
        actionHelperUtil.enterText(ADDRESS_LINE, "29-2-1218")
        actionHelperUtil.enterText(CITY,"BANGALORE")
        actionHelperUtil.enterText(STATE,"KARNATAKA")
        actionHelperUtil.enterText(POSTAL_CODE,"560087")
        actionHelperUtil.enterText(COUNTRY,"INDIA")
        actionHelperUtil.click(SAVE_BUTTON)
    }

}