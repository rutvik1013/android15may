package com.example.taskmanassign

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanassign.databinding.ActivityAddactivityBinding
import java.sql.Date
import java.util.Calendar

class Addactivity : AppCompatActivity() {
    //view Binding

    private lateinit var binding: ActivityAddactivityBinding
    lateinit var priority:String
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddactivityBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        dbHelper= DbHelper(applicationContext)

        binding.high.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.high.isChecked)
            {
                priority="High"
            }

        }
        binding.avg.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.avg.isChecked)
            {
                priority="Average"
            }

        }
        binding.low.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.low.isChecked)
            {
                priority="Low"
            }
        }
        binding.create.setOnClickListener {
            var name=binding.taskname.text.toString()
            var des=binding.taskdescription.text.toString()
            var date=binding.date.text.toString()
            var time=binding.time.text.toString()

            var m=Model()
            m.name=name
            m.description=des
            m.priority=priority
            m.date=date
            m.time=time
            m.status="A"

            var id=dbHelper.insertdata(m)
            Toast.makeText(applicationContext, "Task Inserted", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }
        // on below line we are creating date click event
        binding.date.setOnClickListener {
            //on below line we are creating day,month,year
            var c=Calendar.getInstance()
            var day=c.get(Calendar.DAY_OF_MONTH)
            var month=c.get(Calendar.MONTH)
            var year=c.get(Calendar.YEAR)

            //datepicker Variable
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val date = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.date.setText(date)
                },
                year,
                month,
                day
            )
            // at last we are calling show  to display our date picker dialog.
            datePickerDialog.show()
        }
        binding.time.setOnClickListener {
            val c = Calendar.getInstance()

            // on below line we are getting our hour, minute.
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)


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
