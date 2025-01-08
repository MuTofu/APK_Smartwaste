package com.example

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserData(
    val email : String? = null,
    val password : String? = null,
)
