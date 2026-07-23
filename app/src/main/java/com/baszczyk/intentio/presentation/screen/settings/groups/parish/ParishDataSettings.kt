package com.baszczyk.intentioapp.presentation.screen.settings.groups.parish

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baszczyk.intentio.domain.model.MassDay
import com.baszczyk.intentio.domain.model.MassPattern
import com.baszczyk.intentioapp.presentation.components.BasicOutlinedButton
import com.baszczyk.intentio.presentation.components.BorderCard
import com.baszczyk.intentioapp.presentation.screen.settings.groups.parish.components.AddMassDialog
import com.baszczyk.intentioapp.presentation.screen.settings.groups.parish.components.EditDialogElements
import com.baszczyk.intentioapp.presentation.screen.settings.groups.parish.components.EditParishDataDialog
import com.baszczyk.intentioapp.ui.theme.Dimension
import org.koin.androidx.compose.koinViewModel

@Composable
fun ParishDataSettings() {

    val viewModel: ParishDataViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    val openAddDialog = remember { mutableStateOf<MassDay?>(null) }
    val openEditDialog = remember { mutableStateOf<EditDialogElements?>(null) }

    if(openAddDialog.value != null) {
        AddMassDialog(
            onDismissRequest = { openAddDialog.value = null },
            onConfirmation = { massPattern ->
                openAddDialog.value = null
                viewModel.addMassPattern(massPattern)
            },
            dialogTitle = "Dodaj nową Mszę Św.",
            massDay = openAddDialog.value!!
        )
    }

    if(openEditDialog.value != null) {
        EditParishDataDialog(
            dialogTitle = openEditDialog.value!!.label,
            currentName = openEditDialog.value!!.value,
            onDismissRequest = { openEditDialog.value = null}
        ) {
            viewModel.editParishData(openEditDialog.value!!.label, it)
        }
    }

    Column(
        modifier = Modifier
            .padding(Dimension.small),
        verticalArrangement = Arrangement.spacedBy(Dimension.small)
    ) {
        state.parish?.let {
            ParishDataCard(
                label = "Nazwa parafii",
                value = it.name,
                onEditIconClick = {
                    openEditDialog.value = EditDialogElements("Nazwa parafii", it.name)
                }
            )
            ParishDataCard(
                label = "Adres parafii",
                value = it.address,
                onEditIconClick = {
                    openEditDialog.value = EditDialogElements("Adres parafii", it.address)
                }
            )
            ParishDataCard(
                label = "Proboszcz",
                value = it.parishPriest.getFullName(),
                onEditIconClick = {
                    openEditDialog.value = EditDialogElements("Proboszcz", it.parishPriest.getFullName())
                }
            )
            MassesGroup(
                label = "Msze Św. w niedziele i święta",
                masses = it.massesPattern.filter { mass -> mass.day == MassDay.FEAST },
                onAddButtonClick = {
                    openAddDialog.value = MassDay.FEAST
                },
                onItemDelete = { massPattern ->
                    viewModel.deleteMassPattern(massPattern)
                }
            )
            MassesGroup(
                label = "Msze Św. w dni powszednie",
                masses = it.massesPattern.filter { mass -> mass.day == MassDay.ORDINARY_DAY },
                onAddButtonClick = {
                    openAddDialog.value = MassDay.ORDINARY_DAY
                },
                onItemDelete = { massPattern ->
                    viewModel.deleteMassPattern(massPattern)
                }
            )
        }
    }
}

@Composable
fun ParishDataCard(
    label: String,
    value: String,
    onEditIconClick: () -> Unit
) {
    var isEditMode by remember { mutableStateOf(false) }

    BorderCard(
       onCardClick = {
           isEditMode = !isEditMode
           onEditIconClick()
       }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimension.small)
        ) {
            LabelWithEditIcon(label, isEditMode)
            ValueTextField(value)
        }
    }
}

@Composable
fun LabelWithEditIcon(
    label: String,
    isEditMode: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LabelTextField(label)
        Icon(
            imageVector = if(isEditMode) Icons.Default.Check else Icons.Default.Edit,
            contentDescription = "edit"
        )
    }
}

@Composable
fun ValueTextField(
    value: String
) {
    Text(
        text  = value,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun LabelTextField(
    label: String
) {
    Text(
        text  = label,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp
    )
}

@Composable
fun MassesGroup(
    label: String,
    masses: List<MassPattern>,
    onAddButtonClick: () -> Unit,
    onItemDelete: (MassPattern) -> Unit
) {
    var isEditMode by remember { mutableStateOf(false) }
    var showWarningDialog by remember { mutableStateOf(false) }

    BorderCard(
      onCardClick = {
          if(isEditMode) {
              showWarningDialog = true
          }
          isEditMode = !isEditMode
      }
    ) {
        Column(
            modifier = Modifier.padding(Dimension.medium)
        ) {
            LabelWithEditIcon(label, isEditMode)
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 90.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(masses) { mass ->
                    MassChip(
                        hour = mass.hour,
                        isEditMode = isEditMode,
                        onDeleteClick = { onItemDelete(mass) }
                    )
                }
            }
            if(isEditMode) {
                BasicOutlinedButton(
                    label = "dodaj",
                    onClick = onAddButtonClick,
                )
            }
        }
    }
}

@Composable
fun MassChip(
    hour: String,
    isEditMode: Boolean,
    onDeleteClick: () -> Unit
) {
    InputChip(
        onClick = { },
        label = { Text(hour) },
        selected = true,
        trailingIcon = {
            if(isEditMode) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Localized description",
                    Modifier
                        .size(InputChipDefaults.AvatarSize)
                        .clickable { onDeleteClick() }
                )
            }
        },
    )
}



