package com.example.chasier.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.chasier.adapter.AdapterRiwayat
import com.example.chasier.api.ApiMain
import com.example.chasier.api.RetrofitClient
import com.example.chasier.model.Code
import com.example.chasier.model.Penjualan
import com.example.chasier.model.Produk
import com.example.chasier.model.ProdukDetail
import kotlinx.android.synthetic.main.activity_riwayat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityRepository {

    fun getServicesRiwayat(key : String): MutableLiveData<ArrayList<Penjualan>> {
        val serviceSetterGetter = MutableLiveData<ArrayList<Penjualan>>()

        val call = ApiMain().services.getPenjualan(key)
        call.enqueue(object : Callback<ArrayList<Penjualan>>{
            override fun onResponse(
                call: Call<ArrayList<Penjualan>>,
                response: Response<ArrayList<Penjualan>>
            ) {
                if (response.isSuccessful){
                    val penjualan : ArrayList<Penjualan> = response.body()!!
                    serviceSetterGetter.value = penjualan
                }
            }

            override fun onFailure(call: Call<ArrayList<Penjualan>>, t: Throwable) {
                Log.d("response gagal : ", t.message.toString())
            }
        })

        return serviceSetterGetter
    }

    fun getServiceDetail(key : String, id : Int?): MutableLiveData<ArrayList<ProdukDetail>> {
        val serviceSetterGetter = MutableLiveData<ArrayList<ProdukDetail>>()

        val call = ApiMain().services.getDetailPenjualan(key, id)

        call.enqueue(object : Callback<ArrayList<ProdukDetail>> {
            override fun onResponse(
                call: Call<ArrayList<ProdukDetail>>,
                response: Response<ArrayList<ProdukDetail>>
            ) {
                if (response.isSuccessful){
                    val produkDetail : ArrayList<ProdukDetail> = response.body()!!
                    serviceSetterGetter.value = produkDetail
                }
            }
            override fun onFailure(call: Call<ArrayList<ProdukDetail>>, t: Throwable) {
                Log.d("Response gagal : ", t.message.toString())
            }
        })

        return serviceSetterGetter
    }

    fun setPembelian(key : String, id : IntArray, qty:IntArray?): MutableLiveData<Code> {
        val serviceSetterGetter = MutableLiveData<Code>()

        val call = ApiMain().services.savePembelian(key, id, qty )
        call.enqueue(object : Callback<Code> {
            override fun onResponse(call: Call<Code>, response: Response<Code>) {
                if (response.isSuccessful){
                    val code : Code = response.body()!!
                    serviceSetterGetter.value = code
                }
            }

            override fun onFailure(call: Call<Code>, t: Throwable) {
                Log.d("Response gagal api : ", t.message.toString())
            }
        })

        return serviceSetterGetter
    }


    fun getServiceProduk(context: Context): MutableLiveData<ArrayList<Produk>> {
        val serviceSetterGetter = MutableLiveData<ArrayList<Produk>>()

        val call = ApiMain().services.getProduct()
        call.enqueue(object : Callback<ArrayList<Produk>> {
            override fun onResponse(
                call: Call<ArrayList<Produk>>,
                response: Response<ArrayList<Produk>>
            ) {
                if (response.isSuccessful){
                    val produk: ArrayList<Produk> = response.body()!!
                    serviceSetterGetter.value = produk
                }
            }

            override fun onFailure(call: Call<ArrayList<Produk>>, t: Throwable) {
                Log.d("Failure response : ", t.message.toString())
                Toast.makeText(context, "Periksa koneksi internet anda !!", Toast.LENGTH_SHORT).show()
            }
        })

        return serviceSetterGetter
    }
}