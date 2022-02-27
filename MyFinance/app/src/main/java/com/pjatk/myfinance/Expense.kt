package com.pjatk.myfinance

import java.io.Serializable
import java.time.LocalDate

data class Expense(
        val placeName: String,
        var cost: Double,
        val date: LocalDate,
        val category: String
) : Serializable {init {
    if (category != "Income") {
        cost = -cost
    }
}
}