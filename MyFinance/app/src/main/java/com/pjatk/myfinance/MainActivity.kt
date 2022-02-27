package com.pjatk.myfinance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.pjatk.myfinance.databinding.ActivityMainBinding
import java.util.*

const val REQUESTCONRENER = 1

class MainActivity : AppCompatActivity() {
    private val binding by lazy {

        ActivityMainBinding.inflate(layoutInflater)
    }

    val expenseAdapter by lazy { ExpenseAdapter(this) }


    //...
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.expenses.apply {
            adapter = expenseAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.chartFloatButton.setOnClickListener { startActivity(Intent(this, ChartActivity::class.java)) }


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
        findViewById<TextView>(R.id.textView12).text = tot.toString()

        findViewById<TextView>(R.id.textView10).text = income.toString()
        findViewById<TextView>(R.id.textView11).text = spend.toString()


    }


    override fun onResume() {
        super.onResume()
        expenseAdapter.refresh()
        updateSum()
    }

    fun onClick(view: View) {
        startActivity(Intent(this, AddActivity::class.java))

    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        updateSum()
    }

    override fun onContentChanged() {
        super.onContentChanged()
        updateSum()
    }


    fun startAddActivity(intent: Intent) {
        startActivityForResult(intent, REQUESTCONRENER)
    }


}