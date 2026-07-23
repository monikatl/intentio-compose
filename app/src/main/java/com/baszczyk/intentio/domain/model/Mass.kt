package com.baszczyk.intentioapp.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.baszczyk.intentio.domain.model.MassPattern
import java.time.LocalDate

data class Mass (
    val date: LocalDate,
    val massPattern: MassPattern
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormatDate() = date.dayOfMonth.toString() + " " + date.month?.name + " " + date.year
}

