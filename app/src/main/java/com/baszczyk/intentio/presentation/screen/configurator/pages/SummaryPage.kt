package com.baszczyk.intentio.presentation.screen.configurator.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.baszczyk.intentio.domain.model.Person
import com.baszczyk.intentio.presentation.components.BorderCard
import com.baszczyk.intentio.presentation.screen.configurator.ConfiguratorUiState
import com.baszczyk.intentio.presentation.screen.configurator.ConfiguratorViewModel
import com.baszczyk.intentioapp.ui.theme.Dimension
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SummaryPage(
    viewModel: ConfiguratorViewModel,
    pagerState: PagerState
) {
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Box {
        if(state.parish.isLoading) {
            CircularProgressIndicator()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimension.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                IntentCard(
                    item = state.intentElements,
                    onCardClick = {}
                )
                Spacer(modifier = Modifier.height(Dimension.medium))
                state.intentElements.orderer?.let {
                    OrdererCard(
                        orderer = it,
                        onCardClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(Pages.ORDERER.ordinal)
                            }
                        }
                    )
                }
            }
            OrderIntentButton {
                scope.launch {
                    viewModel.uiState.value.parish.isLoading = true
                    viewModel.createAndSendIntent()
                    pagerState.animateScrollToPage(Pages.PAYMENT.ordinal)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IntentCard(
    item: ConfiguratorUiState.IntentElements,
    onCardClick: () -> Unit
) {
    BorderCard(
        onCardClick = onCardClick
    ) {
        ParishCard()
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(
                    top = Dimension.medium,
                    bottom = Dimension.big
                )
                .padding(
                    horizontal = Dimension.big
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = ("Intencja " + item.type?.text)
            )
            Text(
                text = (item.getFormatDate()),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.mass?.massPattern?.hour ?: "",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.header ?: "",
                fontStyle = FontStyle.Italic
            )

            Text(
                text = "od " + item.fromWhom
            )
        }
    }
}

@Composable
fun OrdererCard(
    orderer: Person,
    onCardClick: () -> Unit
) {
    BorderCard(
        onCardClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimension.large),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = orderer.firstName)
            Text(text = orderer.name)
            Text(text = orderer.email)
        }
    }
}

@Composable
fun ColumnScope.ParishCard() {
    Text(
        text = "Parafia Św. Urszuli Ledóchowskiej\nw Częstochowie",
        fontWeight = FontWeight.Thin,
        modifier = Modifier
            .padding(Dimension.medium)
            .align(Alignment.CenterHorizontally)
    )
}

@Composable
fun OrderIntentButton(
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = ShapeDefaults.Small
    ) {
        Text(
            text = "ZAMÓW INTENCJĘ"
        )
    }
}