package com.baszczyk.intentioapp.presentation.screen.configurator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.Pages
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConfiguratorScreen(navHostController: NavHostController) {

    val viewModel = koinViewModel<ConfiguratorViewModel>()

    val showConfigurator = remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(pageCount = {
        Pages.entries.size
    })

    if(showConfigurator.value) {
        ConfiguratorPager(pagerState, viewModel, navHostController)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            OutlinedButton(
                onClick = {
                    showConfigurator.value = !showConfigurator.value
               },
                shape = ShapeDefaults.ExtraSmall
            ) {
                Text(
                    text = "Zamów intencję"
                )
            }
        }
    }

//    if(showIntentBottomSheet.value) {
//        IntentBottomSheet(viewModel) { }
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentBottomSheet(
    viewModel: ConfiguratorViewModel,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest
    ) {
        IntentCard(viewModel)
    }

}

@Composable
fun IntentCard(viewModel: ConfiguratorViewModel) {

    Card {
        Text(text = viewModel.uiState.value.intentElements.type?.text.toString())
    }
}

