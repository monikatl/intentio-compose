package com.baszczyk.intentio.presentation.screen.activation.pages

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.baszczyk.intentio.domain.model.Person
import com.baszczyk.intentio.presentation.screen.activation.components.ActivationInputCard
import com.baszczyk.intentioapp.R

@Composable
fun PriestsPage(
    onSaveButtonClick: (List<Person>) -> Unit
) {
    //Obraz <a href="https://pixabay.com/pl/users/openclipart-vectors-30363/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=2026319"> OpenClipart-Vectors</a> z <a href="https://pixabay.com/pl//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=2026319"> Pixabay</a>
    Button(
        onClick = {},
        content = {
            Text(
                text = "Dodaj duchownego"
            )
        }
    )
}