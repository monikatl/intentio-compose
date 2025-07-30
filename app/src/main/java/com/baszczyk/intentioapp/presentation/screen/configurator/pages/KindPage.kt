package com.baszczyk.intentioapp.presentation.screen.configurator.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baszczyk.intentioapp.domain.model.IntentKind
import com.baszczyk.intentioapp.presentation.components.BorderCard
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorViewModel
import kotlinx.coroutines.launch


@Composable
fun KindPage(
    viewModel: ConfiguratorViewModel,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        items(IntentKind.entries) { item ->
            IntentKindItem(
                item = item,
                onChoseButtonClick = {
                    viewModel.setIntentKind(item)
                    scope.launch {
                        pagerState.animateScrollToPage(Pages.CONTENT.ordinal)
                    }
                }
            )
        }
    }
}

@Composable
fun IntentKindItem(
    item: IntentKind,
    onChoseButtonClick: () -> Unit
) {
    val showDescription = remember { mutableStateOf(false) }
    BorderCard(
        onCardClick = onChoseButtonClick
    )  {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = item.text)
                if(showDescription.value) {
                    Text(
                        text = item.description,
                        modifier = Modifier.width(250.dp)
                    )
                    Button(
                        onClick = onChoseButtonClick,
                    ) {
                        Text(text = "WYBIERZ")
                    }
                }
            }
            IconButton(
                onClick = { showDescription.value = !showDescription.value },
                content = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "info"
                    )
                }
            )
        }
    }
}