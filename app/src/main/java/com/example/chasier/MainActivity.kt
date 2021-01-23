package com.example.chasier

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chasier.`class`.ConvertNominal
import com.example.chasier.adapter.AdapterMain
import com.example.chasier.adapter.AdapterMainBuy
import com.example.chasier.api.ApiMain
import com.example.chasier.model.Produk
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_produk.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val apiMain = ApiMain()
    val Rp = ConvertNominal()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RV_M_produk.setHasFixedSize(true)
        RV_M_produk.layoutManager = LinearLayoutManager(this)

        BT_M_total.isEnabled = false
        var qty = IntArray(20)
        var jumlah = IntArray(20)
        val list2 = ArrayList<Produk>()
        val adapter2 = AdapterMainBuy(list2, TV_M_total, BT_M_total, qty, jumlah)
//        adapter2.notifyDataSetChanged()

        //tampilkan data dalam recycler view
        RV_M_produk.adapter = adapter2

        fab.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                list2.clear()
                adapter2.notifyDataSetChanged()
                for (i in 0..19){
                    qty[i] = 0
                }
                TV_M_total.text = "Rp. 0"
                apiMain.services.getProduct().enqueue( object : Callback<Array<Produk>> {
                    override fun onResponse(
                        call: Call<Array<Produk>>,
                        response: Response<Array<Produk>>
                    ) {
                        if (response.isSuccessful){
                            val list = ArrayList<Produk>()
                            val produk: Array<Produk> = response.body()!!

                            for (i in 0 until produk.size){

                                list.add(produk.get(i))
                            }
                            showDialog(list, list2, adapter2, qty)
                        }
                    }

                    override fun onFailure(call: Call<Array<Produk>>, t: Throwable) {
                        Log.d("Failure response : ", t.message.toString())
                        Toast.makeText(baseContext, "Periksa koneksi internet anda !!", Toast.LENGTH_SHORT).show()

                    }
                })
            }
        })

        BT_M_total.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                var intent = Intent(baseContext, KonfirmasiActivity::class.java)
                val bundle = Bundle()

                val produk = ArrayList<Produk>()
                val qty1 = IntArray(20)
                var x = 0;
                for ( i in 0..list2.size-1){
                    if (qty[i] > 0){
                        produk.add(list2[i])
                        qty1[x] = qty[i]
                        x = x+1
                    }
                }

                bundle.putParcelableArrayList("produk", produk);
                bundle.putIntArray("jumlah", qty1)
                intent.putExtras(bundle);
                startActivity(intent)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId();

        if (id == R.id.BT_history) {
            val intent = Intent(baseContext, RiwayatActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }



    private fun showDialog(list : ArrayList<Produk>, list2 : ArrayList<Produk>,
                           adapter2: AdapterMainBuy, qty : IntArray){
        val dialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_produk, null)
        dialog.setView(view)
        dialog.show()

        view.TV_DLG_title.text = "Daftar Produk"

        val adapter = AdapterMain(list, list2, adapter2, qty)
        adapter.notifyDataSetChanged()
        view.RV_DLG.adapter = adapter
        view.RV_DLG.layoutManager = LinearLayoutManager(this)

    }

}