package com.baszczyk.intentioapp

import android.os.Build
import com.baszczyk.intentioapp.presentation.components.MainContainer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.baszczyk.intentioapp.ui.theme.IntentioAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentioAppTheme {
                val LightGrayBackground = Color(0xFFFBFBFD)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightGrayBackground
                ) {
                    MainContainer()
                }
            }
        }
    }
}
