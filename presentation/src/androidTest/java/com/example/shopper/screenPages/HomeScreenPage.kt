package com.example.shopper.screenPages

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.example.shopper.utils.ActionHelperUtil


class HomeScreenPage (private val composeTestRule: ComposeTestRule) {

    private val actionHelperUtil = ActionHelperUtil(composeTestRule, HOME_SCREEN)


    companion object {
        const val HOME_SCREEN = "Home Screen"
        const val PROFILE_IMAGE_DESC = "profile image"
        const val GREETING_TEXT_DESC = "Greeting text"
        const val USER_NAME_TEXT_DESC = "User name text"
        const val PRODUCT_TITLE = "product title"
        const val NOTIFICATION_ICON_DESC = "Notification icon"
        const val BUY_NOW_BUTTON = "Buy Now Button"
        const val CART_BUTTON = "Cart Button"
    }
        fun validateHomeScreen() {
            actionHelperUtil.isElementDisplayed(PROFILE_IMAGE_DESC)
            actionHelperUtil.isElementDisplayed(GREETING_TEXT_DESC)
            actionHelperUtil.isElementDisplayed(USER_NAME_TEXT_DESC)
            actionHelperUtil.isElementDisplayed(NOTIFICATION_ICON_DESC)
        }

        fun selectProduct(index: Int) {
            composeTestRule.onAllNodesWithContentDescription(PRODUCT_TITLE, useUnmergedTree = true)[index].performClick()
            actionHelperUtil.click(BUY_NOW_BUTTON)
        }

        fun tapOnCartIcon() {
            actionHelperUtil.click(CART_BUTTON)
        }

}