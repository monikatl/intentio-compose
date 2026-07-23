package com.baszczyk.intentioapp.presentation.screen.activation.pages

import androidx.compose.runtime.Composable
import com.baszczyk.intentioapp.R
import com.baszczyk.intentio.domain.model.Person
import com.baszczyk.intentio.presentation.screen.activation.components.ActivationInputCard

@Composable
fun ParishPriestPage(
    onSaveButtonClick: (Person) -> Unit
) {
    //Obraz <a href="https://pixabay.com/pl/users/openclipart-vectors-30363/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=2026319"> OpenClipart-Vectors</a> z <a href="https://pixabay.com/pl//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=2026319"> Pixabay</a>
    ActivationInputCard (
        label = "Proboszcz",
        image = R.drawable.person,
        onSave = {
            onSaveButtonClick(Person(it, it, it))
        }
    )
}