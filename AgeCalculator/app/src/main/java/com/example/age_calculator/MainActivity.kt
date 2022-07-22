package com.example.age_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView ? = null
    private var tvAgeInMin : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonDatePicker : Button = findViewById(R.id.btn_date_picker)
        tvSelectedDate = findViewById(R.id.tv_selected_date)
        tvAgeInMin = findViewById(R.id.tv_age_in_min)
        buttonDatePicker.setOnClickListener{
            onClickDatePicker()
        }
    }
    private fun onClickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
       val dbd =  DatePickerDialog(this,
           DatePickerDialog.OnDateSetListener{
                   view, selectedYear, selectedMonth, selectedDay ->
               Toast.makeText(this, "Year wad $selectedYear and \n month was ${selectedMonth+1} day is $selectedDay",Toast.LENGTH_LONG).show()
               val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
               tvSelectedDate?.text = selectedDate
               val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
               val theDate = sdf.parse(selectedDate)
             theDate?.let {
                 val selectedMin = theDate.time/60000
                 val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                 currentDate?.let {
                     val currentDateinMin = currentDate.time/60000
                     val differentInMin = currentDateinMin - selectedMin
                     tvAgeInMin?.text = differentInMin.toString()
                 }
             }
           },
           year, month, dayOfMonth)
        dbd.datePicker.maxDate = System.currentTimeMillis()-8640000
        dbd.show()

    }
}