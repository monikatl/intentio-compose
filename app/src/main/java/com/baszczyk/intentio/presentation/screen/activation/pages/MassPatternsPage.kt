package com.baszczyk.intentioapp.presentation.screen.activation.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.baszczyk.intentio.domain.model.MassDay
import com.baszczyk.intentio.domain.model.MassPattern
import com.baszczyk.intentioapp.presentation.screen.settings.groups.parish.components.AddMassDialog

@Composable
fun MassPatternsPage (
    onSaveButtonClick: (List<MassPattern>) -> Unit
) {
    var showAddMassDialog by remember { mutableStateOf<MassDay?>(null) }
    val masses = remember {  mutableListOf<MassPattern>() }

    Column {
        PatterTypeSection(
            text = "Msze Święte w niedzielę",
            masses = masses.filter { it.day == MassDay.FEAST },
            onClick = { showAddMassDialog = MassDay.FEAST }
        )
        PatterTypeSection(
            text = "Msze Święte w dni powszednie",
            masses = masses.filter { it.day == MassDay.ORDINARY_DAY },
            onClick = { showAddMassDialog = MassDay.ORDINARY_DAY }
        )
    }
    showAddMassDialog?.let {
        AddMassDialog(
            dialogTitle = "Dodaj godzinę mszy",
            massDay = it,
            onDismissRequest =  { showAddMassDialog = null },
            onConfirmation = { massPattern ->
                masses.add(massPattern)
                onSaveButtonClick(masses)
                showAddMassDialog = null
            }
        )
    }
}

@Composable
fun PatterTypeSection(
    text: String,
    masses: List<MassPattern>,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)
        OutlinedButton(
            onClick = onClick
        ) { Text(text = "+") }
    }
    LazyRow {
        items(masses) { mass ->
            Text(mass.hour)
        }
    }
}