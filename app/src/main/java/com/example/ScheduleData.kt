package com.example

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class ScheduleData (
    val tanggal : String? = null,
    val jam : String? = null,
    val alamat : String? = null,
    val kepada : String? = null,
) {
    @Exclude
    fun toMap() : Map<String, Any?> {
        return mapOf(
            "tanggal" to tanggal,
            "jam" to jam,
            "alamat" to alamat,
            "kepada" to kepada,
        )
    }
}