package com.example.chasier.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chasier.DetailActivity
import com.example.chasier.R
import com.example.chasier.`class`.ConvertNominal
import com.example.chasier.model.Penjualan
import com.example.chasier.model.Produk
import kotlinx.android.synthetic.main.view_riwayat.view.*

class AdapterRiwayat (private val list: ArrayList<Penjualan>) : RecyclerView.Adapter<AdapterRiwayat.Holder>() {



    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return AdapterRiwayat.Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_riwayat, parent, false)
        )

    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var id : Int = list?.get(position).id
        var tgl : String = list?.get(position).tanggal
        var harga : Int = list?.get(position).harga
        var penjualan : Penjualan = list?.get(position)

        holder.view.TV_riw_tgl.text = tgl
        holder.view.TV_riw_harga.text = ConvertNominal().formatRupiah(harga)
        holder.view.CV_riw.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(holder.view.context, DetailActivity::class.java)
                intent.putExtra("id", id )
                p0?.context?.startActivity(intent)
            }
        })
    }
}