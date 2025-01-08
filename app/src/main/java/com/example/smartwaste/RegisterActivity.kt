package com.example.smartwaste

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class RegisterActivity : AppCompatActivity() {
    private lateinit var databaseAuth : FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // Set content view to the register page layout


        val btnRegister = findViewById<Button>(R.id.btn_Register)
        val ed_username = findViewById<EditText>(R.id.ed_RegisterUsername)
        val ed_email = findViewById<EditText>(R.id.ed_RegisterEmail)
        val ed_password = findViewById<EditText>(R.id.ed_RegisterPassword)
        val ed_reenterPassword = findViewById<EditText>(R.id.ed_RegisterReEnterPassword)
        val chkBx = findViewById<CheckBox>(R.id.chkbx_RegTermCon)

        databaseAuth = Firebase.auth
        database = Firebase.database.reference

        btnRegister.setOnClickListener {
            val username = ed_username.text.toString()
            val email = ed_email.text.toString()
            val pass = ed_password.text.toString()
            val repass = ed_reenterPassword.text.toString()

            if (pass == repass && username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && repass.isNotEmpty()) {
                if (pass.length >= 8) {
                    if (chkBx.isChecked) {
                        createUser(username, email, pass)
                        createUserAuth(email, pass)
                    } else {
                        Toast.makeText(this, "Cek kotak Persyaratan", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Password Minimal 8 Karakter", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Isi form dengan benar", Toast.LENGTH_SHORT).show()
            }


        }



    }


    fun createUserAuth(email : String, password : String) {
        databaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "gagal mendaftar Mendaftar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun createUser(username : String, email : String, password : String) {


        database.child("users").child(username).get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            var user = it.value

            if (user == null) {
            val data = UserData(email, password)
            database.child("users").child(username).setValue(data).addOnSuccessListener {
                Toast.makeText(this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, "Username Already Registered", Toast.LENGTH_SHORT).show()
        }

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }

    }


}
