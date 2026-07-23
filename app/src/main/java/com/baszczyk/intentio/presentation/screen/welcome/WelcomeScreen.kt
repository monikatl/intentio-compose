package com.baszczyk.intentioapp.presentation.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.baszczyk.intentioapp.R
import com.baszczyk.intentioapp.presentation.components.BasicOutlinedButton
import com.baszczyk.intentio.presentation.navigation.ActivationDestination

@Composable
fun WelcomeScreen(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.pen),
            contentDescription = "Zdjęcie parafii",
            modifier = Modifier.weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Witaj w aplikacji do rezerwacji intencji!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Aby rozpocząć zarządzanie intencjami zarejestruj swoją parafię!",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            BasicOutlinedButton(
                label = "Zarejestruj parafię".uppercase()
            ) {
                navHostController.navigate(route = ActivationDestination.ACTIVATION.route)
            }
            BasicOutlinedButton(
                label = "Zaloguj się".uppercase()
            ) {
                navHostController.navigate(route = ActivationDestination.LOGIN.route)
            }
        }
    }
}