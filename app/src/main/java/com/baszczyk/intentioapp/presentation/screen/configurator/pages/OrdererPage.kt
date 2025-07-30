package com.baszczyk.intentioapp.presentation.screen.configurator.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baszczyk.intentioapp.presentation.components.BasicOutlinedButton
import com.baszczyk.intentioapp.presentation.components.OutlinedTextInput
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorViewModel
import kotlinx.coroutines.launch

@Composable
fun OrdererPage(
    viewModel: ConfiguratorViewModel,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    var firstName by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            OutlinedTextInput("Imię", firstName) { firstName = it }
            OutlinedTextInput("Nazwisko", name) { name = it }
            OutlinedTextInput("email", email) { email = it }
        }

        Spacer(modifier = Modifier.height(64.dp))

        BasicOutlinedButton(
           label = "PRZEJDŹ DO PODSUMOWANIA",
            onClick = {
                scope.launch {
                    viewModel.setIntentOrderer(firstName, name, email)
                    pagerState.animateScrollToPage(Pages.SUMMARY.ordinal)
                }
            }
        )
    }
}
