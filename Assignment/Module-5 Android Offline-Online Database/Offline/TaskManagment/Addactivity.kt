package com.example.taskmanagment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.UpdateAppearance
import android.widget.Toast
import com.example.taskmanagment.databinding.ActivityAddactivityBinding
import java.util.Calendar

class Addactivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddactivityBinding
    lateinit var dbHelper: DbHelper
    lateinit var priority:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddactivityBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        dbHelper= DbHelper(applicationContext)

        binding.high.setOnCheckedChangeListener { compoundButton,b->
            if (binding.high.isChecked)
            {
               priority="High"
            }

        }
        binding.average.setOnCheckedChangeListener { compoundButton,b ->
            if (binding.average.isChecked)
            {
                priority="Average"
            }
        }
        binding.low.setOnCheckedChangeListener { compoundButton,b ->
            if (binding.low.isChecked)
            {
                priority="Low"
            }
        }
        binding.createbutton.setOnClickListener {
            var taskname=binding.edttaskname.text.toString()
            var description=binding.edttaskdes.text.toString()
            var date=binding.date.text.toString()
            var time=binding.time.text.toString()

            var m=Model()

            m.name=taskname
            m.description=description
            m.status=""
            m.priority=priority
            m.time=time
            m.date=date

            var id=dbHelper.insertdata(m)
            Toast.makeText(applicationContext, "Task Successfully added", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }
        //on below code  i am doing the instance of our calander
        //first we have to set click listener of date

        binding.date.setOnClickListener {
            val c=Calendar.getInstance()
            //on below code we are doing our day ,month and year
            val day=c.get(Calendar.DAY_OF_MONTH)
            val month=c.get(Calendar.MONTH)
            val year=c.get(Calendar.YEAR)



            val datePickerDialog = DatePickerDialog(
                //  below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.date.setText(dat)
                },
                //day month year
                year,
                month,
                day
            )

            datePickerDialog.show()


        }
        binding.time.setOnClickListener {
            val calander = Calendar.getInstance()

            //  we are getting our hour, minute.
            val minute = calander.get(Calendar.MINUTE)
            val hour = calander.get(Calendar.HOUR_OF_DAY)


            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->

                    binding.time.setText("$hourOfDay:$minute")
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }

    }

}