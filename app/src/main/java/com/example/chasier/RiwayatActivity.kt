package com.example.chasier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chasier.adapter.AdapterMainBuy
import com.example.chasier.adapter.AdapterRiwayat
import com.example.chasier.api.ApiMain
import com.example.chasier.model.Penjualan
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_riwayat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatActivity : AppCompatActivity() {
    val apiMain = ApiMain()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        RV_riw.setHasFixedSize(true)
        RV_riw.layoutManager = LinearLayoutManager(this)

        apiMain.services.getPenjualan("bd0b3ae6651538fac2515baafc9326c5").enqueue(object : Callback<ArrayList<Penjualan>>{
            override fun onResponse(
                call: Call<ArrayList<Penjualan>>,
                response: Response<ArrayList<Penjualan>>
            ) {
                if (response.isSuccessful){
                    val penjualan : ArrayList<Penjualan> = response.body()!!
                    val adapter2 = AdapterRiwayat(penjualan)
                    adapter2.notifyDataSetChanged()
                    RV_riw.adapter = adapter2
                }
            }

            override fun onFailure(call: Call<ArrayList<Penjualan>>, t: Throwable) {
                Log.d("response gagal : ", t.message.toString())
            }
        })


    }
}