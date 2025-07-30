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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baszczyk.intentioapp.domain.model.IntentType
import com.baszczyk.intentioapp.presentation.components.BorderCard
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorViewModel
import kotlinx.coroutines.launch


@Composable
fun TypePage(
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
        items(IntentType.entries) { item ->
            IntentTypeItem(
                item = item,
                onChoseButtonClick = {
                    viewModel.setIntentType(item)
                    scope.launch {
                        pagerState.animateScrollToPage(Pages.DATE.ordinal)
                    }
                }
            )
        }
    }
}

@Composable
fun IntentTypeItem(
    item: IntentType,
    onChoseButtonClick: () -> Unit
) {
    val showDescription = remember { mutableStateOf(true) }
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
                Text(
                    text = item.text.uppercase(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
                if(showDescription.value) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = item.description,
                            modifier = Modifier.width(300.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                    OutlinedButton(
                        onClick = onChoseButtonClick,
                        shape = ShapeDefaults.Small,
                        modifier = Modifier.align(Alignment.End)
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
