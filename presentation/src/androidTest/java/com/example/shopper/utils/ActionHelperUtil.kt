package com.example.shopper.utils

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class ActionHelperUtil (private val composeTestRule: ComposeTestRule, private val  screenName:String) {

    // This is the class where we keep common actions or validations that will be used across the screen pages.
    // Here I'm also ensuring that waiting for the element to be loaded before clicking

    private val waitUtil = WaitUtil()

    fun click(locator: String) {
        waitUtil.waitForScreenToLoad(composeTestRule, locator, screenName)
        composeTestRule.onNodeWithContentDescription(locator).performClick()
    }

    fun isElementDisplayed(locator: String) {
        waitUtil.waitForScreenToLoad(composeTestRule, locator, screenName)
        composeTestRule.onNodeWithContentDescription(locator).assertIsDisplayed()
    }

    fun enterText(locator: String, value: String) {
        waitUtil.waitForScreenToLoad(composeTestRule, locator, screenName)
        composeTestRule.onNodeWithContentDescription(locator).performTextInput(value)
    }
}