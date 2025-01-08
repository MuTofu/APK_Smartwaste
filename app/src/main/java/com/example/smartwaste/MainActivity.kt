package com.example.smartwaste

import android.content.Intent
import android.widget.Button
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference


class MainActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_page) // Set content view to the choose page layout




        val loginButton: Button = findViewById(R.id.admin_button)
        val registerButton: Button = findViewById(R.id.user_button)

        // Navigate to login page when login button is clicked
        loginButton.setOnClickListener {
            val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(loginIntent)

        }

        // Navigate to register page when register button is clicked
        registerButton.setOnClickListener {
            val registerIntent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(registerIntent)

        }



    }







//    fun databaseTest() {
//        val database = Firebase.database
//        val myRef = database.getReference("message")
//        myRef.setValue("Hellio, Wolrds")
//
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue<String>()
//                Log.d("MaiDatabase", "Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w("MaiDatabase", "Failed to read value.", error.toException())
//            }
//        })
//    }
//
//    fun databaseTest2() {
//        val database = Firebase.database
//        val myRef = database.getReference("message")
//
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue<String>()
//                Log.d("MaiDatabase", "Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w("MaiDatabase", "Failed to read value.", error.toException())
//            }
//        })
//    }


}
