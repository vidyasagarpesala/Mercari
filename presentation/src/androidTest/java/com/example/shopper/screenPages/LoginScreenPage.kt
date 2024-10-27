package com.example.shopper.screenPages

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.example.shopper.utils.ActionHelperUtil

class LoginScreenPage (private val composeTestRule: ComposeTestRule){

    private val actionHelperUtil = ActionHelperUtil(composeTestRule, LOGIN_SCREEN)


    companion object {
        const val LOGIN_SCREEN = "Login Screen"
        const val USERNAME = "UserName TextField"
        const val PASSWORD = "Password TextField"
        const val LOGIN_BUTTON = "Login Button"
    }

    open fun login(userName:String, password:String){
        actionHelperUtil.enterText(USERNAME, userName)
        actionHelperUtil.enterText(PASSWORD, password)
        actionHelperUtil.click(LOGIN_BUTTON)
    }
}