package com.baszczyk.intentioapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.baszczyk.intentioapp.domain.model.Content
import com.baszczyk.intentioapp.domain.model.Hour
import com.baszczyk.intentioapp.domain.model.Intent
import com.baszczyk.intentioapp.domain.model.IntentKind
import com.baszczyk.intentioapp.domain.model.IntentType
import com.baszczyk.intentioapp.domain.model.Mass
import com.baszczyk.intentioapp.domain.model.MassDay
import com.baszczyk.intentioapp.domain.model.Person
import java.time.LocalDate

val hours = listOf(
    Hour("7:00"),
    Hour("17:00"),
    Hour("18:00"),
    Hour("9:00"),
    Hour("10:30"),
    Hour("12:00"),
)

@RequiresApi(Build.VERSION_CODES.O)
val masses = listOf(
    Mass(Hour("7:00"), LocalDate.now(), MassDay.ORDINARY_DAY),
    Mass(Hour("17:00"), LocalDate.now(), MassDay.ORDINARY_DAY),
    Mass(Hour("18:00"), LocalDate.now(), MassDay.ORDINARY_DAY),
    Mass(Hour("18:00"), LocalDate.now(), MassDay.FEAST),
    Mass(Hour("7:00"), LocalDate.now(), MassDay.FEAST),
    Mass(Hour("9:00"), LocalDate.now(), MassDay.FEAST),
    Mass(Hour("10:30"), LocalDate.now(), MassDay.FEAST),
    Mass(Hour("12:00"), LocalDate.now(), MassDay.FEAST),
)

@RequiresApi(Build.VERSION_CODES.O)
val intents = mutableListOf(
    Intent(
        type = IntentType.SINGLE,
        mass = masses[0],
        content = Content(
            kind = IntentKind.SUPPLICATORY,
            header = "O Boże błogosławieństwo",
            persons = listOf(Person("Nowak", "Agnieszka", "email@gmail.com")),
            text = "w piątą rocznicę urodzin"
        ),
        orderer = Person("Nowak", "Piotr", "email@gmail.com")
    ),
    Intent(
        type = IntentType.SINGLE,
        mass = masses[0],
        content = Content(
            kind = IntentKind.SUPPLICATORY,
            header = "O Boże błogosławieństwo",
            persons = listOf(Person("Nowak", "Agnieszka", "email@gmail.com")),
            text = "w piątą rocznicę urodzin"
        ),
        orderer = Person("Nowak", "Piotr", "email@gmail.com")
    )
)

val for_dead_headers = listOf(
    "Za śp. [imię i nazwisko], prosząc o dar życia wiecznego",
    "Za duszę śp. [imię i nazwisko], aby Pan przyjął ją do swojej chwały",
    "Za zmarłych z rodziny [nazwisko], o pokój wieczny dla nich",
    "Za śp. [imię], w kolejną rocznicę śmierci",
    "Za zmarłych rodziców, dziadków i krewnych",
    "Za wszystkich wiernych zmarłych",
    "Za tych, którzy odeszli nagle i tragicznie",
    "Za dusze w czyśćcu cierpiące",
    "Za zmarłych kapłanów i duszpasterzy",
    "Za zmarłych wskutek choroby",
    "Za śp. [imię], prosząc o przebaczenie grzechów i radość nieba",
    "Za zmarłych przyjaciół i dobroczyńców",
    "Za ofiary wojen i prześladowań",
    "Za zmarłych parafian",
    "Za zmarłych spoczywających na tym cmentarzu",
    "Za śp. [imię], z okazji dnia imienin",
    "Za śp. [imię], w miesiąc po śmierci",
    "Za tych, o których nikt się już nie modli",
    "Za zmarłych, których znaliśmy i kochaliśmy",
    "Za zmarłych członków wspólnoty [nazwa]"
)

val supplicator_headers = listOf(
    "O zdrowie i Boże błogosławieństwo",
    "O pomyślne rozwiązanie trudnej sprawy",
    "O łaskę nawrócenia dla bliskich",
    "O dar potomstwa",
    "O zgodę i miłość w rodzinie",
    "O ochronę przed złem i niebezpieczeństwem",
    "O światło Ducha Świętego w podejmowaniu decyzji dla [imię] oraz [nazwa]",
    "O uwolnienie z nałogów",
    "O zdanie egzaminów i dobry wybór drogi życiowej",
    "O dobrą i stabilną pracę",
    "O pokój serca i przezwyciężenie lęków",
    "O szczęśliwą operację i powrót do zdrowia dla [imię]",
    "O opiekę Matki Bożej nad rodziną",
    "O łaskę przebaczenia i pojednania",
    "O ochronę przed wojną i kataklizmami",
    "O miłość i dobrego męża / dobrą żonę",
    "O błogosławieństwo w nowym domu / mieszkaniu",
    "O powodzenie w sprawach zawodowych i finansowych",
    "O łaskę wiary i wytrwałość w modlitwie",
    "O rozeznanie powołania"
)

val thanksqiving_headers = listOf(
    "Dziękczynna za otrzymane łaski",
    "Dziękczynna za dar życia i zdrowia",
    "Dziękczynna za pomyślnie zdany egzamin",
    "Dziękczynna za dar nawrócenia",
    "Dziękczynna za dar rodziny i miłości",
    "Dziękczynna z okazji urodzin / jubileuszu",
    "Dziękczynna za opiekę Matki Bożej",
    "Dziękczynna za szczęśliwe rozwiązanie",
    "Dziękczynna za dar pracy / nową pracę",
    "Dziękczynna za Boże błogosławieństwo w trudnym czasie",
    "Dziękczynna za sakrament małżeństwa",
    "Dziękczynna za dar kapłaństwa / powołania",
    "Dziękczynna za łaskę przebaczenia",
    "Dziękczynna za uratowanie z wypadku / choroby",
    "Dziękczynna za pokój w rodzinie",
    "Dziękczynna z okazji rocznicy ślubu",
    "Dziękczynna za wysłuchaną modlitwę",
    "Dziękczynna za wsparcie duchowe w czasie próby",
    "Dziękczynna za zakończone leczenie i powrót do zdrowia",
    "Dziękczynna za obecność Boga w codzienności"
)

val fromWhomMap = mapOf(
    "Rodzina" to listOf(
        "żony",
        "męża",
        "dzieci",
        "rodziców",
        "rodzeństwa",
        "dziadków",
        "wnuków",
        "kuzynów",
        "chrzestnej",
        "chrzestnego",
        "teściowej",
        "teścia",
        "siostry",
        "brata",
        "synowej",
        "zięcia"
    ),
    "Znajomi i sąsiedzi" to listOf(
        "przyjaciół",
        "sąsiadów",
        "koleżanek",
        "kolegów",
        "znajomych z pracy",
        "sąsiadów z klatki",
        "sąsiadów z osiedla",
        "współlokatorów"
    ),
    "Wspólnoty i grupy" to listOf(
        "pracowników firmy",
        "uczniów",
        "studentów",
        "klasy",
        "nauczycieli",
        "parafian",
        "grupy modlitewnej",
        "członków wspólnoty religijnej",
        "mieszkańców dzielnicy",
        "mieszkańców hospicjum",
        "mieszkańców domu opieki"
    ),
    "Inne" to listOf(
        "proboszcza",
        "zakonników",
        "sióstr zakonnych",
        "osoby duchownej",
        "grupy pielgrzymkowej",
        "fundacji katolickiej",
        "anonimowego ofiarodawcy"
    )
)



val regex = listOf(
    "[imię]",
    "[imię i nazwisko]",
    "[nazwa]"
)

