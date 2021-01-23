package com.example.chasier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chasier.api.ApiMain
import com.example.chasier.model.Produk
import kotlinx.android.synthetic.main.activity_list_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListProdukActivity : AppCompatActivity() {
    val apiMain = ApiMain()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_produk)

        val list = ArrayList<Produk>()
        val listProduk = arrayOf(
            Produk(1, "permen", 2000),
            Produk(2, "Roti", 3000)
        )
        RV_LS_produk.setHasFixedSize(true)
        RV_LS_produk.layoutManager = LinearLayoutManager(this)

//        for (i in 0 until listProduk.size){
//
//            list.add(listProduk.get(i))
//
//            if(listProduk.size - 1 == i){
//                // init adapter yang telah dibuat tadi
//                val adapter = AdapterMain(list)
//                adapter.notifyDataSetChanged()
//
//                //tampilkan data dalam recycler view
//                RV_LS_produk.adapter = adapter
//            }
//        }
        apiMain.services.getProduct().enqueue( object : Callback<Array<Produk>>{
            override fun onResponse(
                call: Call<Array<Produk>>,
                response: Response<Array<Produk>>
            ) {
//                if (response.isSuccessful){
//                    Toast.makeText(baseContext, "data sukses", Toast.LENGTH_SHORT).show()
//                    val produk: Array<Produk> = response.body()!!
//
//                    for (i in 0 until produk.size-1){
//
//                        list.add(produk.get(i))
//                    }
//                    // init adapter yang telah dibuat tadi
//                    val adapter = AdapterMain(list, list)
//                    adapter.notifyDataSetChanged()
//
//                    //tampilkan data dalam recycler view
//                    RV_LS_produk.adapter = adapter
//                }
            }

            override fun onFailure(call: Call<Array<Produk>>, t: Throwable) {
                Log.d("Failure response : ", t.message.toString())
                Toast.makeText(baseContext, "Periksa koneksi internet anda !!", Toast.LENGTH_SHORT).show()

            }
        })

    }
}