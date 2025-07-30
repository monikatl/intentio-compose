package com.baszczyk.intentioapp.presentation.screen.configurator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.ContentPage
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.DatePage
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.FromWhomPage
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.KindPage
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.OrdererPage
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.Pages
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.PaymentPage
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.SummaryPage
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.TypePage
import kotlinx.coroutines.launch

@Composable
fun ConfiguratorPager(
    pagerState: PagerState,
    viewModel: ConfiguratorViewModel,
    navHostController: NavHostController
) {
    val pageState = remember { mutableIntStateOf(0) }
    val state by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) { page ->
        ResolvePage(
            index = page,
            viewModel = viewModel,
            pagerState = pagerState,
            navHostController = navHostController
        )
        pageState.intValue = page
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = {
                if (pagerState.currentPage > 0) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                modifier = Modifier.size(40.dp),
                contentDescription = "Poprzedni"
            )
        }


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = resolveClipColor(pagerState, iteration, state)
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(20.dp),
                )
            }
        }

        // Strzałka w prawo
        IconButton(
            onClick = {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                modifier = Modifier.size(40.dp),
                contentDescription = "Następny"
            )
        }
    }

}

fun resolveClipColor(
    pagerState: PagerState,
    iteration: Int,
    state: ConfiguratorUiState
): Color {
    return if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResolvePage(
    index: Int,
    viewModel: ConfiguratorViewModel,
    pagerState: PagerState,
    navHostController: NavHostController
) {
    when(index) {
        Pages.TYPE.ordinal -> TypePage(viewModel, pagerState)
        Pages.DATE.ordinal -> DatePage(viewModel, pagerState)
        Pages.KIND.ordinal -> KindPage(viewModel, pagerState)
        Pages.CONTENT.ordinal -> ContentPage(viewModel, pagerState)
        Pages.FROM_WHOM.ordinal -> FromWhomPage(viewModel, pagerState)
        Pages.ORDERER.ordinal -> OrdererPage(viewModel, pagerState)
        Pages.SUMMARY.ordinal -> SummaryPage(viewModel, pagerState)
        Pages.PAYMENT.ordinal -> PaymentPage(viewModel, pagerState, navHostController)
    }
}