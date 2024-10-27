package com.example.shopper.utils

import android.util.Log
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule

class WaitUtil {

    fun waitForScreenToLoad(
        composeTestRule: ComposeTestRule,
        uniqueElementTag: String,
        screenName: String,
        timeoutMillis: Long = 5000L
    ) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            // Check if the element with the unique test tag is present on the screen
            if (composeTestRule.onAllNodes(hasTestTag(uniqueElementTag)).fetchSemanticsNodes().isNotEmpty()) {
                Log.d("ScreenUtils", "User is currently on the $screenName screen.")
                return
            }
            // Allow a short delay to prevent constant polling
            Thread.sleep(200)
        }
        // Log an error if the screen does not load in the expected time
        throw AssertionError("Screen $screenName did not load within $timeoutMillis ms.")
    }
}