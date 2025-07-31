package com.baszczyk.intentioapp.presentation.screen.configurator.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baszczyk.intentioapp.presentation.components.BorderCard
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePage(
    viewModel: ConfiguratorViewModel,
    pagerState: PagerState
) {

    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadMasses()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.masses) { mass ->
            BorderCard (
                onCardClick = {
                    viewModel.setIntentDateAndHourType(mass.date, mass.massPattern.hour, mass)
                    scope.launch {
                        pagerState.animateScrollToPage(viewModel.resolveNextPageAfterDate().ordinal)
                    }
                },
               content = {
                   Row(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(8.dp),
                       horizontalArrangement = Arrangement.SpaceBetween
                   ) {
                       Text(text = mass.getFormatDate())
                       Text(text = mass.massPattern.hour)
                   }
               }
            )

        }
    }
}