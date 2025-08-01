package com.baszczyk.intentioapp.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.baszczyk.intentioapp.MainViewModel
import com.baszczyk.intentioapp.datastore.DataStoreManager
import com.baszczyk.intentioapp.presentation.screen.configurator.ConfiguratorViewModel
import com.baszczyk.intentioapp.presentation.screen.home.HomeViewModel
import com.baszczyk.intentioapp.presentation.screen.settings.SettingsViewModel
import com.baszczyk.intentioapp.presentation.screen.settings.groups.parish.ParishDataViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val appModule = module {
    single { DataStoreManager(androidContext()) }

    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { ConfiguratorViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { ParishDataViewModel() }
}