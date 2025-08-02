package com.baszczyk.intentioapp.presentation.screen.activation

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.baszczyk.intentioapp.presentation.navigation.Destination
import com.baszczyk.intentioapp.presentation.screen.activation.pages.MassPatternsPage
import com.baszczyk.intentioapp.presentation.screen.activation.pages.Pages
import com.baszczyk.intentioapp.presentation.screen.activation.pages.ParishAddressPage
import com.baszczyk.intentioapp.presentation.screen.activation.pages.ParishNamePage
import com.baszczyk.intentioapp.presentation.screen.activation.pages.ParishPriestPage
import com.baszczyk.intentioapp.presentation.screen.activation.pages.SummaryPage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActivationScreen(
    navHostController: NavHostController
) {

    val viewModel = koinViewModel<ActivationViewModel>()
    val pagerState = rememberPagerState(pageCount = {
        Pages.entries.size
    })
    val pageState = remember { mutableIntStateOf(Pages.NAME.ordinal) }
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
    iteration: Int
): Color {
    return if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResolvePage(
    index: Int,
    viewModel: ActivationViewModel,
    pagerState: PagerState,
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()

    when(index) {
        Pages.NAME.ordinal -> ParishNamePage {
            scope.launch {
                pagerState.animateScrollToPage(Pages.ADDRESS.ordinal)
            }
        }
        Pages.ADDRESS.ordinal -> ParishAddressPage {
            scope.launch {
                pagerState.animateScrollToPage(Pages.PARISH_PRIEST.ordinal)
            }
        }
        Pages.PARISH_PRIEST.ordinal -> ParishPriestPage {
            scope.launch {
                pagerState.animateScrollToPage(Pages.MASS_PATTERNS.ordinal)
            }
        }
        Pages.MASS_PATTERNS.ordinal -> MassPatternsPage {
            scope.launch {
                pagerState.animateScrollToPage(Pages.SUMMARY.ordinal)
            }
        }
        Pages.SUMMARY.ordinal -> SummaryPage(viewModel) {
            scope.launch {
                navHostController.navigate(Destination.HOME.route)
            }
        }
    }
}

