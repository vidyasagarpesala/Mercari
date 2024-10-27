package com.example.shopper.ui.feature.user_address

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shopper.model.UserAddress

const val USER_ADDRESS_SCREEN = "user_address"

@Composable
fun UserAddressScreen(navController: NavController, userAddress: UserAddress?) {

    val addressLine = remember {
        mutableStateOf(userAddress?.addressLine ?: "")
    }
    val city = remember {
        mutableStateOf(userAddress?.city ?: "")
    }
    val state = remember {
        mutableStateOf(userAddress?.state ?: "")
    }
    val postalCode = remember {
        mutableStateOf(userAddress?.postalCode ?: "")
    }
    val country = remember {
        mutableStateOf(userAddress?.country ?: "")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = addressLine.value,
            onValueChange = { addressLine.value = it },
            label = { Text(text = "Address Line") },
            modifier = Modifier.fillMaxWidth().semantics { contentDescription = "Address Line" }
        )
        OutlinedTextField(
            value = city.value,
            onValueChange = { city.value = it },
            label = { Text(text = "City") },
            modifier = Modifier.fillMaxWidth().semantics { contentDescription = "City" }
        )
        OutlinedTextField(
            value = state.value,
            onValueChange = { state.value = it },
            label = { Text(text = "State") },
            modifier = Modifier.fillMaxWidth().semantics { contentDescription = "State" }
        )
        OutlinedTextField(
            value = postalCode.value,
            onValueChange = { postalCode.value = it },
            label = { Text(text = "Postal Code") },
            modifier = Modifier.fillMaxWidth().semantics { contentDescription = "Postal Code" }
        )
        OutlinedTextField(
            value = country.value,
            onValueChange = { country.value = it },
            label = { Text(text = "Country") },
            modifier = Modifier.fillMaxWidth().semantics { contentDescription = "Country" }
        )

        Button(
            onClick = {
                val address = UserAddress(
                    addressLine = addressLine.value,
                    city = city.value,
                    state = state.value,
                    postalCode = postalCode.value,
                    country = country.value
                )
                val previousBackStack = navController.previousBackStackEntry
                previousBackStack?.savedStateHandle?.set(USER_ADDRESS_SCREEN, address)
                navController.popBackStack()

            }, modifier = Modifier.fillMaxWidth().semantics { contentDescription = "Save Button" },
            enabled = addressLine.value.isNotEmpty() && city.value.isNotEmpty() && state.value.isNotEmpty() && postalCode.value.isNotEmpty() && country.value.isNotEmpty()
        ) {
            Text(text = "Save")
        }
    }
}