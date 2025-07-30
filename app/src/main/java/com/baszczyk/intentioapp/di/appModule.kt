package com.baszczyk.intentioapp.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.baszczyk.intentioapp.MainViewModel
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorViewModel
import com.baszczyk.intentioapp.presentation.screen.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val appModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { ConfiguratorViewModel() }
}