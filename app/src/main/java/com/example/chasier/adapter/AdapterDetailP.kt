package com.example.chasier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chasier.R
import com.example.chasier.`class`.ConvertNominal
import com.example.chasier.model.Produk
import com.example.chasier.model.ProdukDetail
import kotlinx.android.synthetic.main.view_detail.view.*

class AdapterDetailP (private val list: ArrayList<ProdukDetail>) : RecyclerView.Adapter<AdapterDetailP.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return AdapterDetailP.Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_detail, parent, false)
        )
    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var nama = list?.get(position).nama
        var qty = list?.get(position).qty
        var harga = list?.get(position).harga
        holder.view.V_detail_nama.text = nama
        holder.view.V_detail_harga.text = qty.toString()+" * "+ConvertNominal().formatRupiah(harga)
        holder.view.V_detail_jumlah.text = ConvertNominal().formatRupiah(qty*harga)

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}