package com.baszczyk.intentio.presentation.screen.activation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.baszczyk.intentioapp.presentation.components.BasicOutlinedButton
import com.baszczyk.intentio.presentation.navigation.ActivationDestination
import com.baszczyk.intentioapp.presentation.screen.activation.pages.MassPatternsPage
import com.baszczyk.intentioapp.presentation.screen.activation.pages.Pages
import com.baszczyk.intentio.presentation.screen.activation.pages.ParishAddressPage
import com.baszczyk.intentio.presentation.screen.activation.pages.PriestsPage
import com.baszczyk.intentioapp.presentation.screen.activation.pages.ParishNamePage
import com.baszczyk.intentioapp.presentation.screen.activation.pages.ParishPriestPage
import com.baszczyk.intentioapp.presentation.screen.activation.pages.SummaryPage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ActivationScreen(
    navHostController: NavHostController
) {

    val viewModel: ActivationViewModel = koinViewModel()
    val pagerState = rememberPagerState(pageCount = {
        Pages.entries.size
    })
    val pageState = remember { mutableIntStateOf(Pages.NAME.ordinal) }
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
    ) { page ->
        ResolvePage(
            index = page,
            viewModel = viewModel,
        )
        pageState.intValue = page
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = resolveClipColor(pagerState, iteration)
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(20.dp),
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            val currentPage = pagerState.currentPage
            if(currentPage != 0) {
                BasicOutlinedButton(
                    modifier = Modifier.weight(1f),
                    label = "WRÓĆ",
                    onClick = {
                        if (pagerState.currentPage > 0) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                )
            }
            BasicOutlinedButton(
                modifier = Modifier.weight(1f),
                label = if(currentPage < Pages.entries.size - 1) "DALEJ" else "ZAPISZ",
                onClick = {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        viewModel.collectParishDataAndSend()
                        navHostController.navigate(route = ActivationDestination.SUCCESS.route)
                    }
                }
            )
        }

    }
}

fun resolveClipColor(
    pagerState: PagerState,
    iteration: Int
): Color {
    return if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResolvePage(
    index: Int,
    viewModel: ActivationViewModel,
) {
    val state by viewModel.uiState.collectAsState()

    when(index) {
        Pages.NAME.ordinal -> ParishNamePage {
            viewModel.saveParishName(it)
        }
        Pages.ADDRESS.ordinal -> ParishAddressPage {
            viewModel.saveParishAddress(it)
        }
        Pages.PARISH_PRIEST.ordinal -> ParishPriestPage {
            viewModel.saveParishPriest(it)
        }
        Pages.MASS_PATTERNS.ordinal -> MassPatternsPage {
            viewModel.saveParishMassPatterns(it)
        }
        Pages.PRIESTS.ordinal -> PriestsPage {
            viewModel.savePriests(it)
        }
        Pages.SUMMARY.ordinal -> SummaryPage(state)
    }
}
