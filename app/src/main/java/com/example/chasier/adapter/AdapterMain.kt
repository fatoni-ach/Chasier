package com.example.chasier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chasier.R
import com.example.chasier.model.Produk
import kotlinx.android.synthetic.main.view_produk.view.*

class AdapterMain (private val list: ArrayList<Produk>, private val list2: ArrayList<Produk>,
                    adapter2 : AdapterMainBuy, qty : IntArray) : RecyclerView.Adapter<AdapterMain.Holder>() {
    var qty = qty
    val adapter2 = adapter2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.view_produk ,parent,false))
    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var id = list?.get(position)?.id
        var nama = list?.get(position)?.nama
        var harga = list?.get(position)?.harga
        holder.view.TV_V_nama.text = nama
        holder.view.TV_V_harga.text = "Rp. "+harga.toString()
        holder.view.CV_V.setOnClickListener( object : View.OnClickListener{
            override fun onClick(p0: View?) {
                list2.add(Produk(id, nama, harga))
                for (i in 0..19){
                    qty[i] = 0
                }
                for (i in 0..list2.size-1){
                    adapter2.notifyItemChanged(i)
                }
                adapter2.notifyDataSetChanged()
            }
        })
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}