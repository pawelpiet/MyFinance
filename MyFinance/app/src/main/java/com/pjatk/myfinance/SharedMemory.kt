package com.pjatk.myfinance

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

object SharedMemory {
    @RequiresApi(Build.VERSION_CODES.O)
    val expenseList: MutableList<Expense> = mutableListOf(
            Expense("McDonald's", 35.0, LocalDate.now(), "Food"),
            Expense("Salary", 2000.0, LocalDate.now().minusDays(1), "Income"),
            Expense("Lidl", 150.0, LocalDate.now().minusDays(2), "Food"),
            Expense("Gym", 69.0, LocalDate.now(), "Recreation"),
            Expense("Payback", 1000.0, LocalDate.now().minusDays(2), "Income"),
            Expense("Dinner", 200.0, LocalDate.now().minusDays(5), "Food")
    )
}