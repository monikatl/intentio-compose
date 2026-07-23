package com.baszczyk.intentioapp.presentation.navigation

import LoginScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.baszczyk.intentio.presentation.navigation.ActivationDestination
import com.baszczyk.intentio.presentation.navigation.Destination
import com.baszczyk.intentio.presentation.screen.calendar.CalendarScreen
import com.baszczyk.intentio.presentation.screen.activation.ActivationScreen
import com.baszczyk.intentio.presentation.screen.activation.SuccessScreen
import com.baszczyk.intentio.presentation.screen.configurator.ConfiguratorScreen
import com.baszczyk.intentioapp.presentation.screen.home.HomeScreen
import com.baszczyk.intentioapp.presentation.screen.settings.SettingsScreen
import com.baszczyk.intentioapp.presentation.screen.welcome.WelcomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = ActivationDestination.WELCOME.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        activationGraph(navController)
        mainGraph(navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(Destination.HOME.route) {
        HomeScreen()
    }
    composable(Destination.CONFIGURATOR.route) {
        ConfiguratorScreen(navController)
    }
    composable(Destination.SETTINGS.route) {
        SettingsScreen(navController)
    }
    composable(Destination.CALENDAR.route) {
        CalendarScreen()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.activationGraph(navController: NavHostController) {
    composable(ActivationDestination.WELCOME.route) {
        WelcomeScreen(navController)
    }
    composable(ActivationDestination.ACTIVATION.route) {
        ActivationScreen(navController)
    }
    composable(ActivationDestination.LOGIN.route) {
        LoginScreen(navController, { _, _ -> })
    }
    composable(ActivationDestination.SUCCESS.route) {
        SuccessScreen(navController)
    }
}
