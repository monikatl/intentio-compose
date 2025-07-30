package com.baszczyk.intentioapp.presentation.screen.configurator.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.baszczyk.intentioapp.presentation.components.BorderCard
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SummaryPage(
    viewModel: ConfiguratorViewModel,
    pagerState: PagerState
) {
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            BorderCard(
                onCardClick = {

                }
            ) {
                Text(
                    text = "Parafia Św. Urszuli Ledóchowskiej\nw Częstochowie",
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 8.dp, bottom = 16.dp)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = ("Intencja " + state.type?.text)
                    )
                    Text(
                        text = (state.date?.dayOfMonth.toString() + " " + state.date?.month?.name + " " + state.date?.year),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = state.hour?.value ?: "",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = state.header ?: "",
                        fontStyle = FontStyle.Italic
                    )

                    Text(
                        text = "od " + state.fromWhom
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            BorderCard(
                onCardClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(Pages.ORDERER.ordinal)
                    }
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = (state.orderer?.firstName ?: "")
                    )
                    Text(
                        text = (state.orderer?.name ?: "")
                    )
                    Text(
                        text = state.orderer?.email ?: ""
                    )
                }
            }
        }

        OutlinedButton(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(Pages.PAYMENT.ordinal)
                    viewModel.createAndSendIntent()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = ShapeDefaults.Small
        ) {
            Text(
                text = "ZAMÓW INTENCJĘ"
            )
        }
    }
}