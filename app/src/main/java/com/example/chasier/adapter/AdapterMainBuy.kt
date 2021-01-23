package com.example.chasier.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.chasier.R
import com.example.chasier.`class`.ConvertNominal
import com.example.chasier.model.Produk
import kotlinx.android.synthetic.main.view_produk_buy.view.*

class AdapterMainBuy (private val list: ArrayList<Produk>,
                      TV_M_total : TextView,
                        BT_M_total : Button,
                        qty : IntArray,
                        jumlah :IntArray) : RecyclerView.Adapter<AdapterMainBuy.Holder>() {
    val TV_M_total = TV_M_total
    val BT_M_total = BT_M_total
    var jumlah = jumlah
    var qty = qty

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return AdapterMainBuy.Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_produk_buy, parent, false)
        )
    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var id = list?.get(position)?.id
        var harga = list?.get(position)?.harga
        var nama = list?.get(position)?.nama

        holder.view.TV_M_NamaProduk.text = nama
        holder.view.TV_M_Harga.text = ConvertNominal().formatRupiah(harga)
        holder.view.ET_M_qty.text = Editable.Factory.getInstance().newEditable(qty.get(position).toString())
        holder.view.ET_M_qty.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if ( holder.view.ET_M_qty.text.toString() == "" ){
                    qty[position] = 0
                } else {
                    qty[position] = holder.view.ET_M_qty.text.toString().toInt()
                }
                jumlah[position] = qty[position]*harga

                var total:Int = 0
                for(i in 0..list.size-1){
                    total = total+jumlah[i]
                }
                if (total > 0){
                    BT_M_total.isEnabled = true
                } else {
                    BT_M_total.isEnabled = false
                }
                TV_M_total.text = ConvertNominal().formatRupiah(total)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
        holder.view.TV_M_NamaProduk.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                Toast.makeText(holder.view.context, itemCount.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}