package com.pjatk.myfinance

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.pjatk.myfinance.databinding.ActivityChartBinding
import java.time.LocalDate
import java.util.*


class ChartActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityChartBinding.inflate(layoutInflater)
    }

    val expenseAdapter by lazy { ExpenseAdapter(MainActivity()) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        updateSum()
        binding.graphView.setData(getPoints())

        binding.backButton.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getPoints(): List<DataPoint> {
        val random = Random()
        val chartList: MutableList<Expense> = mutableListOf()
        val month = LocalDate.now().month
        var l = 30
        for (expense in SharedMemory.expenseList) {
            if (expense.date.month == month) {
                chartList.add(expense)
            }
        }
        //chartList.sortByDescending { month }
        return chartList.map {
            DataPoint(it.date.dayOfMonth, it.cost.toInt())
        }
    }


    private fun updateSum() {

        var income = 0.0
        var spend = 0.0
        var tot = 0.0
        for (expense in SharedMemory.expenseList) {
            if (expense.category == "Income") {
                income += expense.cost
            } else {
                spend += expense.cost
            }
            tot = income + spend
        }
        findViewById<TextView>(R.id.textView14).text = tot.toString()


    }

}