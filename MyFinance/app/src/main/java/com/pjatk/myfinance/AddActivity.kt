package com.pjatk.myfinance

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.pjatk.myfinance.databinding.ActivityAddBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")


        binding.saveButton.setOnClickListener {
            // validate

            if (binding.placeName.text.toString().isBlank()) {
                Toast.makeText(this, "cant be empty", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AddActivity::class.java))
            }
            if (binding.cost.text.toString().isBlank()) {
                Toast.makeText(this, "cant be empty", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AddActivity::class.java))
            }
            if (binding.data.text.toString().isBlank()) {
                Toast.makeText(this, "cant be empty", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AddActivity::class.java))
            }

            val expense = Expense(
                    binding.placeName.text.toString(),
                    binding.cost.text.toString().toDouble(),
                    LocalDate.parse(binding.data.text.toString()),
                    binding.spCategoeires.selectedItem as String
            )
            SharedMemory.expenseList.add(expense)
            Toast.makeText(this, "Added!!!", Toast.LENGTH_LONG).show()
            finish()
        }

        binding.cancelButton.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }


}
