package com.baszczyk.intentio.presentation.screen.configurator.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baszczyk.intentioapp.data.fromWhomMap
import com.baszczyk.intentio.presentation.components.BorderCard
import com.baszczyk.intentio.presentation.screen.configurator.ConfiguratorViewModel
import kotlinx.coroutines.launch

@Composable
fun FromWhomPage(
    viewModel: ConfiguratorViewModel,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadIntentFromWhom()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        fromWhomMap.forEach { (key, value) ->
            Column {
                Text(
                    text = key,
                    modifier = Modifier.padding(8.dp),
                    letterSpacing = 2.sp
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(value) { item ->
                        BorderCard(
                            onCardClick = {
                                viewModel.setIntentFromWhom(item)
                                scope.launch {
                                    pagerState.animateScrollToPage(Pages.ORDERER.ordinal)
                                }
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .wrapContentWidth()
                            ) {
                                Text(
                                    text = item,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
