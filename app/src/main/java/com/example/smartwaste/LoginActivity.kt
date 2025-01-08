package com.example.smartwaste

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private lateinit var database : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Set content view to the login page layout



        val button = findViewById<Button>(R.id.btn_LoginSubmit)
        val ed_email = findViewById<EditText>(R.id.ed_LoginEmail)
        val ed_password = findViewById<EditText>(R.id.ed_LoginPassword)



        database = Firebase.auth

        button.setOnClickListener{
            var email = ed_email.text.toString()
            var pass = ed_password.text.toString()
            loginAuth(email, pass)


        }




    }

    fun loginAuth(email : String, password : String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            database.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                if (it.isSuccessful) {
                    Toast.makeText(this, "Berhasil login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Home_Admin_Page::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Gagal login", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Silahkan Isi Form", Toast.LENGTH_SHORT).show()
        }


    }

}
