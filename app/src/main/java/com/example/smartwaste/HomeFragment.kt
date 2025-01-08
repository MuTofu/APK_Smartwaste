package com.example.smartwaste

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.PegawaiData
import com.example.ScheduleData
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class HomeFragment : Fragment() {

    private lateinit var data : ArrayList<ScheduleData>
    private lateinit var database : DatabaseReference
    private lateinit var recyclerku : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerku = view.findViewById<RecyclerView>(R.id.Recycler_ViewKu)

        database = Firebase.database.reference.child("jadwal")


        data = ArrayList<ScheduleData>()
        getDataList()
        Log.e("TAG,",data.toString(), )




        return view

    }


    fun getDataList() {
        val listListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data.clear()

                for (i in snapshot.children) {
                    val item = i.getValue(ScheduleData::class.java)
                    if (item != null) {
                        data.add(item)
                    }
                }
                val adapter = HomeAdapter(data)
                recyclerku.layoutManager = LinearLayoutManager(context)
                recyclerku.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebaskyu", "loadPost:onCancelled", error.toException())
            }
        }
        database.addValueEventListener(listListener)
    }

}