package com.baszczyk.intentio.presentation.screen.activation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.baszczyk.intentio.presentation.components.LargeVerticalSpacer
import com.baszczyk.intentioapp.presentation.components.BasicOutlinedButton
import com.baszczyk.intentio.presentation.navigation.ActivationDestination
import com.baszczyk.intentio.presentation.navigation.Destination
import org.koin.androidx.compose.koinViewModel

@Composable
fun SuccessScreen(
    navHostController: NavHostController
) {
    val viewModel: SuccessViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if(true) viewModel.setSuccess() else viewModel.setFailure()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom

    ) {
        Image(
            painter = painterResource(id = state.image),
            contentDescription = "result"
        )
        Text(
            text = state.message,
            fontSize = 18.sp
        )

        LargeVerticalSpacer()

        Column {
            state.contactButton?.let {
                BasicOutlinedButton(label = it) {

                }
            }
            BasicOutlinedButton(label = state.buttonLabel) {
                resolveNavDestination(
                    isSuccess = state.contactButton == null,
                    navHostController = navHostController
                )
            }
        }
    }
}

fun resolveNavDestination(
    isSuccess: Boolean,
    navHostController: NavHostController
) {
    if(isSuccess) {
        navHostController.navigate(Destination.HOME.route)
    } else {
        navHostController.navigate(ActivationDestination.ACTIVATION.route)
    }
}
