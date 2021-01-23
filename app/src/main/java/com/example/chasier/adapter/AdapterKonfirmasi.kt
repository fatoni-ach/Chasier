package com.example.chasier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chasier.R
import com.example.chasier.`class`.ConvertNominal
import com.example.chasier.model.Produk
import kotlinx.android.synthetic.main.activity_konfirmasi.view.*
import kotlinx.android.synthetic.main.view_konfirmasi.view.*

class AdapterKonfirmasi (private val list: ArrayList<Produk>? ,
                         qty : IntArray?) : RecyclerView.Adapter<AdapterKonfirmasi.Holder>(){
    var qty = qty
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return AdapterKonfirmasi.Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_konfirmasi, parent, false)
        )
    }

    override fun getItemCount(): Int = list?.size!!

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var id = list?.get(position)?.id
        var nama = list?.get(position)?.nama
        var harga = list?.get(position)?.harga
        holder.view.V_konf_nama.text = nama
        holder.view.V_konf_harga.text = (qty?.get(position)?.toInt()!!).toString() +" * "+ConvertNominal().formatRupiah(harga)
        holder.view.V_konf_jumlah.text = ConvertNominal().formatRupiah(qty?.get(position)!! * harga!!)
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}