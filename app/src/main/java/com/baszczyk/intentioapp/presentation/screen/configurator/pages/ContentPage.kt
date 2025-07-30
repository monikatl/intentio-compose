package com.baszczyk.intentioapp.presentation.screen.configurator.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baszczyk.intentioapp.presentation.components.BorderCard
import com.baszczyk.intentioapp.presentation.components.OutlinedTextInput
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorViewModel
import com.baszczyk.intentioapp.ui.theme.Dimension
import kotlinx.coroutines.launch

@Composable
fun ContentPage(
    viewModel: ConfiguratorViewModel,
    pagerState: PagerState
) {
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    var showAddDialog by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadIntentHeaders()
        viewModel.loadIntentRegex()
    }

    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimension.medium),
            verticalArrangement = Arrangement.spacedBy(Dimension.medium)
        ) {
            items(state.headers) { header ->
                BorderCard(
                    onCardClick = {
                        viewModel.setIntentHeader(header)
                        if(viewModel.checkIfHeaderHasRegex()) {
                            showAddDialog = true
                        } else {
                            scope.launch {
                                pagerState.animateScrollToPage(Pages.FROM_WHOM.ordinal)
                            }
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimension.medium)
                    ) {
                        Text(text = header)
                    }
                }

            }
        }
    }

    if(showAddDialog) {
        AdditionalContentInfoDialog(
            header = state.intentElements.header ?: "",
            labels = state.currentRegex,
            inputText = inputText,
            onInputTextValueChange = { inputText = it },
            onDismissRequest = { text ->
                viewModel.setIntentHeader(text)
                showAddDialog = false
                scope.launch {
                    pagerState.animateScrollToPage(Pages.FROM_WHOM.ordinal)
                }
            }
        )
    }
}

@Composable
fun AdditionalContentInfoDialog(
    header: String,
    labels: List<String>,
    inputText: String,
    onInputTextValueChange: (String) -> Unit,
    onDismissRequest: (String) -> Unit
) {
    val regex = "\\[(.*?)]".toRegex()
    val match = regex.find(header)

    val (before, after) = if (match != null) {
        val start = header.substring(0, match.range.first).trim().removeSuffix(",")
        val end = header.substring(match.range.last + 1).trimStart(',', ' ')
        start to end
    } else {
        header to ""
    }

    AlertDialog(
        onDismissRequest = { onDismissRequest("") },
        title = {
            Text(text = "Uzupełnij informacje:")
        },
        text = {
            Column {
                Text(text = before)
                labels.forEach { label ->
                    OutlinedTextInput(
                        label = label,
                        text = inputText,
                        onValueChange = { onInputTextValueChange(it) }
                    )
                }
                Text(
                    text = after,
                )
            }
        },
        shape = ShapeDefaults.Small,
        confirmButton = {
            TextButton(
                onClick = { onDismissRequest("$before $inputText $after") }
            ) {
                Text("ZATWIERDŹ")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissRequest("") }
            ) {
                Text("ANULUJ")
            }
        }
    )
}