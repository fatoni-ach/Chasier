package com.example.chasier

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chasier.`class`.ConvertNominal
import com.example.chasier.adapter.AdapterDetailP
import com.example.chasier.adapter.AdapterKonfirmasi
import com.example.chasier.api.ApiMain
import com.example.chasier.model.Code
import com.example.chasier.model.Produk
import com.example.chasier.viewmodel.ActivityViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_konfirmasi.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KonfirmasiActivity : AppCompatActivity() {
    val apiMain = ApiMain()
    lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi)

        activityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)

        val produkList: ArrayList<Produk>? =
            this.intent.extras!!.getParcelableArrayList<Produk>("produk")
        val qty: IntArray? = this.intent.extras!!.getIntArray("jumlah")

        RV_konfirmasi.setHasFixedSize(true)
        RV_konfirmasi.layoutManager = LinearLayoutManager(this)

        val list2 = ArrayList<Produk>()
        val adapter2 = AdapterKonfirmasi(produkList, qty)
        adapter2.notifyDataSetChanged()
        RV_konfirmasi.adapter = adapter2

        BT_konf_selesai.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                save_pembelian(produkList, qty);
            }
        })

        var total : Int = 0
        for (i in 0..produkList?.size!! - 1){
            var jumlah = produkList?.get(i).harga * qty?.get(i)!!
            total = total+jumlah
        }
        TV_konf_total.text = ConvertNominal().formatRupiah(total)
    }

    private fun save_pembelian(produklist : ArrayList<Produk>? , qty : IntArray?) {
        var id = IntArray(produklist!!.size)
        for (i in 0..produklist!!.size-1){
            id[i] = produklist.get(i).id
        }

        activityViewModel.setPembelian("bd0b3ae6651538fac2515baafc9326c5", id,  qty)!!.
        observe(this, Observer { serviceSetterGetter ->
            val code = serviceSetterGetter
            showDialog(code)
        })

    }

    fun showDialog(code : Code){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(code.msg)
        builder.setMessage("Berhasil menyimpan")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}