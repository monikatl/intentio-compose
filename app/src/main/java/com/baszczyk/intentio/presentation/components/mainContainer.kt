package com.baszczyk.intentioapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baszczyk.intentio.presentation.navigation.ActivationDestination
import com.baszczyk.intentioapp.presentation.navigation.AppNavigation
import com.baszczyk.intentio.presentation.navigation.Destination


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer (modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.HOME

    var selectedDestination by rememberSaveable { mutableStateOf(startDestination) }

    var showIntentionBottomSheet = remember { mutableStateOf(false) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Destination.HOME.route,
        Destination.SETTINGS.route,
        Destination.CALENDAR.route
    )

    val showLogoutButton = currentRoute in listOf(
        Destination.HOME.route,
        Destination.SETTINGS.route
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(currentRoute?.uppercase() ?: "") },
                navigationIcon = {
                    if(currentRoute == "configurator")
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                actions = {
                    if(showLogoutButton) {
                        IconButton(
                            onClick = { navController.navigate(route = ActivationDestination.LOGIN.route)  }
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "show")
                        }
                    }
                }
            )
        },
        bottomBar = {
            if(showBottomBar) {
                NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                    Destination.entries.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = selectedDestination.ordinal == index,
                            onClick = {
                                navController.navigate(route = destination.route)
                                selectedDestination = destination
                            },
                            icon = {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.contentDescription
                                )
                            },
                            label = {
                                Text(
                                    text = destination.label,
                                    fontSize = 9.sp
                                )
                            }
                        )
                    }
                }
            }
        }
    ){ contentPadding ->
        AppNavigation(navController, contentPadding)
    }
}