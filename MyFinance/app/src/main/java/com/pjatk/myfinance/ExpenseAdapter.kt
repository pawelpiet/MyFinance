package com.pjatk.myfinance


import android.app.AlertDialog

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.pjatk.myfinance.databinding.ItemExpenseBinding


class ExpenseAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<ExpenseViewHolder>() {


    private var expenseList = SharedMemory.expenseList


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return ExpenseViewHolder(binding).also { holder ->
            binding.root.setOnLongClickListener { remove(holder.layoutPosition, parent) }


            binding.root.setOnClickListener {
                val intentEdit = Intent(Intent(parent.context, AddActivity::class.java))
                SharedMemory.expenseList.removeAt(holder.layoutPosition)
                mainActivity.startAddActivity(intentEdit)
            }

        }
    }

    override fun getItemCount(): Int = expenseList.size

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expenseList[position])
        var expense = SharedMemory.expenseList.get(position)

    }

    fun refresh() = notifyDataSetChanged()

    fun add(expense: Expense) {
        expenseList.add(expense)
        notifyItemInserted(expenseList.size - 1)
    }


    fun remove(position: Int, parent: ViewGroup): Boolean {

        val builder = AlertDialog.Builder(parent.context)
        builder.setMessage("Delete this postion?")
                .setCancelable(true)
                .setPositiveButton("Yes") { i, w ->
                    SharedMemory.expenseList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemChanged(position)

                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
        val alert = builder.create()
        alert.show()
        refresh()


        return true
    }


}