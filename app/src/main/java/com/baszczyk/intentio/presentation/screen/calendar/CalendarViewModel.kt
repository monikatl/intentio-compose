package com.baszczyk.intentio.presentation.screen.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baszczyk.intentio.domain.model.Author
import com.baszczyk.intentio.domain.repository.AuthorRepository
import com.baszczyk.intentio.network.httpClient
import com.baszczyk.intentio.network.model.LiturgicalDay
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.launch

class CalendarViewModel(
    private val authorRepository: AuthorRepository
) : ViewModel() {
    private val _day = MutableLiveData<LiturgicalDay>()
    val day: LiveData<LiturgicalDay> get() = _day

    private var _author = MutableLiveData<Author>()
    val author: LiveData<Author> get() = _author

    fun loadData() {
       // loadDay()
        getRandomAuthor()
    }

    private fun loadDay() {
        viewModelScope.launch {
            try {
                val today = getTodayLiturgicalDay()
                _day.value = today
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun getTodayLiturgicalDay(): LiturgicalDay {
        val year = 2025
        val country = "PL"
        val language = "pl"
        return httpClient.get("https://api.churchcalendar.org/calendars/$year/$country/$language").body()
    }

    private suspend fun getAuthors() {

    }

    private fun getRandomAuthor() {
        viewModelScope.launch {
            try {
                println("getRandomAuthor")
                authorRepository.getRandomAuthor { _author.value = it }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}