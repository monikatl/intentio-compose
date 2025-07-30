package com.baszczyk.intentioapp.presentation.screen.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baszczyk.intentioapp.domain.model.Intent
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    Column {
        IntentList(state.intents)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IntentList(intents: List<Intent>) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(intents) { item ->
            IntentItem(item)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IntentItem(intent: Intent) {
    Card (
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.DarkGray,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White
        ),
        border = BorderStroke(1.dp, color = Color.DarkGray),
        shape = ShapeDefaults.Small
    ) {
       Column(
           modifier = Modifier.padding(8.dp)
       ) {
           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween
           ) {
               Text(text = intent.mass?.date?.dayOfMonth.toString() + " " + intent.mass?.date?.month?.name + " " + intent.mass?.date?.year)
               Text(
                   text = intent.mass?.hour?.value ?: "",
               )
           }
           HorizontalDivider()
           Column {
               Text(
                   text = intent.content?.header + " dla " + intent.content?.persons?.get(0)?.firstName + " " + intent.content?.text,
                   fontSize = 18.sp
               )
               Text(
                   text = intent.content?.kind.toString()
               )
           }
       }
    }
}
