package com.example.smartwaste

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import java.util.Calendar
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.PegawaiData
import com.example.ScheduleData
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class CreateActivity : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var databasePegawai : DatabaseReference
    private lateinit var databaseSchedule : DatabaseReference
    private lateinit var dataPegawai : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spin = findViewById<Spinner>(R.id.Spinner_ku)
        val btnSubmit = findViewById<Button>(R.id.btn_CreateSubmit)
        val tanggal = findViewById<TextView>(R.id.Create_date)
        val jam = findViewById<TextView>(R.id.Create_time)
        val alamat = findViewById<EditText>(R.id.ed_JadwalAlamat)


        dataPegawai = ArrayList<String>()
        databasePegawai = Firebase.database.reference.child("pegawai")
        databaseSchedule = Firebase.database.reference.child("jadwal")

        databasePegawai.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dataPegawai.clear()

                for (i in snapshot.children) {
                    val item = i.getValue(PegawaiData::class.java)
                    if (item != null) {
                        dataPegawai.add(item.nama!!)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })







        spin.onItemSelectedListener = this
        val ad:ArrayAdapter<*> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            dataPegawai
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spin.adapter = ad






        val timeForm = findViewById<TextView>(R.id.Create_time)
        timeForm.setOnClickListener{
            val c = Calendar.getInstance()

            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val timepickerdialog = TimePickerDialog (
                this,
                {view, hourOfDay, minute ->
                    timeForm.setText("$hourOfDay:$minute")
                },
                hour,
                minute,
                false
            )
            timepickerdialog.show()

        }

        val dateForm = findViewById<TextView>(R.id.Create_date)
        dateForm.setOnClickListener{
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datepickerdialog = DatePickerDialog(
                this,
                {view, year, monthYear, dayMonth ->
                    dateForm.text = (dayMonth.toString() + "-" + (monthYear) + "-" + year)
                },
                year,
                month,
                day
            )
            datepickerdialog.show()
        }


        btnSubmit.setOnClickListener {
//            createPegawai("bb3", "martino")
            createJadwal(tanggal.text.toString(), jam.text.toString(),alamat.text.toString(), "Martono", )
            val intent = Intent(this, Home_Admin_Page::class.java)
            startActivity(intent)



        }
    }


    fun createPegawai( id : String, nama : String) {
        val pegawai = PegawaiData(nama)

        databasePegawai.child(id).setValue(pegawai).addOnSuccessListener {
            Log.e("FirebaseKyu", "Succsesss")
        }.addOnFailureListener {
            Log.e("FirebaseKyu", "Failed")
        }
    }

    fun createJadwal(tanggal : String, jam : String, alamat : String, kepada : String) {
        val key = databaseSchedule.push().key
        var data = ScheduleData(tanggal, jam, alamat, kepada)

        if (key != null) {
            databaseSchedule.child(key).setValue(data)
        }



    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var data = dataPegawai
        Log.e("TAG", "onItemSelected: $data", )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}