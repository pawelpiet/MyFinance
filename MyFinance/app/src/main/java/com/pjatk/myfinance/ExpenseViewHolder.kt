package com.pjatk.myfinance

import androidx.recyclerview.widget.RecyclerView
import com.pjatk.myfinance.databinding.ItemExpenseBinding

class ExpenseViewHolder(
        private val layoutBinding: ItemExpenseBinding
) : RecyclerView.ViewHolder(layoutBinding.root) {
    fun bind(expense: Expense) = with(layoutBinding) {


        placeName.text = expense.placeName
        cost.text = expense.cost.toString()
        data.text = expense.date.toString()
        spCategoeires.text = expense.category


    }

}