package com.example.smartwaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.ScheduleData

class HomeAdapter(private val data : ArrayList<ScheduleData>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val alamat = view.findViewById<TextView>(R.id.item_alamat)
        val waktu = view.findViewById<TextView>(R.id.item_waktu)
        val tanggal = view.findViewById<TextView>(R.id.item_tanggal)
        val kepada = view.findViewById<TextView>(R.id.item_Kepada)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fetch_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.apply {
            val jadwal = data[position]
            holder.tanggal.text = jadwal.tanggal
            holder.waktu.text = jadwal.jam
            holder.alamat.text = jadwal.alamat
            holder.kepada.text = jadwal.kepada
        }

    }

    override fun getItemCount(): Int {
        var dataku = data.size
        return dataku
    }
}