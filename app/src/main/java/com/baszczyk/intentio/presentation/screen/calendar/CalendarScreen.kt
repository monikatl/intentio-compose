package com.baszczyk.intentio.presentation.screen.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun CalendarScreen(viewModel: CalendarViewModel = koinViewModel()) {
    val day by viewModel.day.observeAsState()
    val author by viewModel.author.observeAsState()

    LaunchedEffect(author) {
        viewModel.loadData()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Dzisiejszy dzień liturgiczny:")
            day?.let {
                Text("Data: ${it.date}")
                Text("Okres: ${it.season}")
                Text("Celebracja: ${it.celebration}")
            } ?: Text("Ładowanie...")
        }
        Column {
            Text(
                text = author?.name ?: "ff"
            )
            Text(
                text = author?.quotes?.get(0) ?: "ff"
            )
        }
    }

}