package com.baszczyk.intentioapp.presentation.screen.configurator.pages

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorViewModel

@Composable
fun PaymentPage(
    viewModel: ConfiguratorViewModel,
    pagerState: PagerState,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Intencja została zamówiona")
        Text(text = "Możesz teraz dokonać ofiary lub uczynić to w zakrystii przed Mszą Świętą")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            OutlinedButton(
                onClick = { navHostController.navigateUp() },
                modifier = Modifier.weight(1f),
                shape = ShapeDefaults.Small
            ) {
                Text(text = "Pomiń")
            }

            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                shape = ShapeDefaults.Small
            ) {
                Text(text = "Wpłać ofiarę")
            }
        }
    }
}