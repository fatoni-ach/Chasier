package com.example.chasier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chasier.`class`.ConvertNominal
import com.example.chasier.adapter.AdapterDetailP
import com.example.chasier.adapter.AdapterMainBuy
import com.example.chasier.api.ApiMain
import com.example.chasier.model.ProdukDetail
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    val apiMain = ApiMain()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var id : Int? = intent?.getIntExtra("id", 0)

        RV_detail.setHasFixedSize(true)
        RV_detail.layoutManager = LinearLayoutManager(this)

        apiMain.services.getDetailPenjualan("bd0b3ae6651538fac2515baafc9326c5", id).
        enqueue( object : Callback<ArrayList<ProdukDetail>>{
            override fun onResponse(
                call: Call<ArrayList<ProdukDetail>>,
                response: Response<ArrayList<ProdukDetail>>
            ) {
                if (response.isSuccessful){
                    val produkDetail : ArrayList<ProdukDetail> = response.body()!!
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
                }
            }

            override fun onFailure(call: Call<ArrayList<ProdukDetail>>, t: Throwable) {
                Log.d("Response gagal : ", t.message.toString())
            }
        })

    }
}