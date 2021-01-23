package com.example.chasier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chasier.`class`.ConvertNominal
import com.example.chasier.adapter.AdapterDetailP
import com.example.chasier.api.ApiMain
import com.example.chasier.viewmodel.ActivityViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    val apiMain = ApiMain()
    lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var id : Int? = intent?.getIntExtra("id", 0)
        activityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)

        RV_detail.setHasFixedSize(true)
        RV_detail.layoutManager = LinearLayoutManager(this)

        activityViewModel.getdetailPenjualan("bd0b3ae6651538fac2515baafc9326c5", id)!!.
        observe(this, Observer { serviceSetterGetter ->
            val produkDetail = serviceSetterGetter
            val adapter2 = AdapterDetailP(produkDetail)
            adapter2.notifyDataSetChanged()
            RV_detail.adapter = adapter2

            var total = 0
            for (i in 0..produkDetail.size-1){
                var jumlah :Int
                jumlah = produkDetail.get(i).harga * produkDetail.get(i).qty
                total = total + jumlah
            }
            TV_detail_total.text = ConvertNominal().formatRupiah(total)

        })

    }
}