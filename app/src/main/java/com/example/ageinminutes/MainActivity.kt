package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener { view ->
            clickDatePicker(view)
            Toast.makeText(this, "Button works", Toast.LENGTH_SHORT) }
    }


    fun clickDatePicker(view : View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, selectedYear, selectedMonth , selectedDay ->

            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date = sdf.parse(selectedDate)

            val selectedDateInMinutes = date!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time /60000
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            tvAgeInMinutes.setText(differenceInMinutes.toString())
        },
            year,
            month,
            day)
        dpd.datePicker.maxDate = (Date().time - 86400000)
        dpd.show()
        }

    }


