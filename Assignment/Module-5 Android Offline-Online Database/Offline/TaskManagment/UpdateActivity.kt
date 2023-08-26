package com.example.taskmanagment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagment.databinding.ActivityUpdateBinding
import java.util.Calendar

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    lateinit var dbHelper: DbHelper
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var i = intent

        var id = i.getIntExtra("id", 101)
        var name = i.getStringExtra("name")
        var description = i.getStringExtra("Description")
        var date = i.getStringExtra("Date")
        var status = i.getStringExtra("Status")
        var time = i.getStringExtra("Time")
        var priority = i.getStringExtra("Priority")


        binding.edttitle.setText(name)
        binding.edtdes.setText(description)
        binding.edtdate.setText(date)
        binding.edttime.setText(time)


        binding.btnupdate.setOnClickListener {
            var name = binding.edttitle.text.toString()
            var description = binding.edtdes.text.toString()
            var date = binding.edtdate.text.toString()
            var time = binding.edttime.text.toString()

            var model = Model()

            model.id = id
            model.name = name
            model.description = description
            model.date = date
            model.time = time

            Toast.makeText(applicationContext, "Updated Task", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
        binding.btndelete.setOnClickListener {
            dbHelper.deletedata(list.get(id).id)
            Toast.makeText(applicationContext, "Deleted Task", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, Addactivity::class.java))
        }
        binding.edtdate.setOnClickListener {
            val c = Calendar.getInstance()
            //on below code we are doing our day ,month and year
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)


            val datePickerDialog = DatePickerDialog(
                //  below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.edtdate.setText(dat)
                },
                //day month year
                year,
                month,
                day
            )

            datePickerDialog.show()


        }
        binding.edttime.setOnClickListener {
            val calander = Calendar.getInstance()

            //  we are getting our hour, minute.
            val minute = calander.get(Calendar.MINUTE)
            val hour = calander.get(Calendar.HOUR_OF_DAY)


            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->

                    binding.edttime.setText("$hourOfDay:$minute")
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }
    }
}