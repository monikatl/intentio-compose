package com.baszczyk.intentioapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorScreen
import com.baszczyk.intentioapp.presentation.screen.home.HomeScreen
import com.baszczyk.intentioapp.presentation.screen.settings.SettingsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    paddingValues: PaddingValues,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Destination.HOME.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Destination.HOME.route) { HomeScreen() }
        composable(Destination.CONFIGURATOR.route) { ConfiguratorScreen(navController) }
        composable(Destination.SETTINGS.route) { SettingsScreen() }
    }
}
