package com.baszczyk.intentio.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.baszczyk.intentio.domain.repository.AuthorRepository
import com.baszczyk.intentio.domain.repository.IntentRepository
import com.baszczyk.intentio.domain.repository.ParishRepository
import com.baszczyk.intentio.presentation.screen.calendar.CalendarViewModel
import com.baszczyk.intentioapp.datastore.DataStoreManager
import com.baszczyk.intentio.presentation.screen.activation.ActivationViewModel
import com.baszczyk.intentio.presentation.screen.activation.SuccessViewModel
import com.baszczyk.intentio.presentation.screen.configurator.ConfiguratorViewModel
import com.baszczyk.intentioapp.presentation.screen.home.HomeViewModel
import com.baszczyk.intentioapp.presentation.screen.login.LoginViewModel
import com.baszczyk.intentioapp.presentation.screen.settings.SettingsViewModel
import com.baszczyk.intentioapp.presentation.screen.settings.groups.parish.ParishDataViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val appModule = module {

    single { DataStoreManager(androidContext()) }
    single { FirebaseFirestore.getInstance() }
    singleOf(::ParishRepository)
    singleOf(::IntentRepository)
    singleOf(::AuthorRepository)

    viewModel { LoginViewModel() }
    viewModel { ActivationViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ConfiguratorViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { ParishDataViewModel(get()) }
    viewModel { CalendarViewModel(get()) }
    viewModel { SuccessViewModel() }
}