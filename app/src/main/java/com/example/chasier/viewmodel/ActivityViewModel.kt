package com.example.chasier.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chasier.model.Code
import com.example.chasier.model.Penjualan
import com.example.chasier.model.Produk
import com.example.chasier.model.ProdukDetail
import com.example.chasier.repository.ActivityRepository

class ActivityViewModel : ViewModel(){
    var servicesLiveData: MutableLiveData<ArrayList<Penjualan>>? = null
    var serviceLiveProdukDetail : MutableLiveData<ArrayList<ProdukDetail>>? = null
    var serviceLiveKonfirmasi : MutableLiveData<Code>? = null
    var serviceLiveProduk : MutableLiveData<ArrayList<Produk>>? = null

    fun getRiwayat(key : String) : LiveData<ArrayList<Penjualan>>? {
        servicesLiveData = ActivityRepository().getServicesRiwayat(key)
        return servicesLiveData
    }

    fun getdetailPenjualan(key : String, id : Int?) : LiveData<ArrayList<ProdukDetail>>? {
        serviceLiveProdukDetail = ActivityRepository().getServiceDetail(key, id)
        return serviceLiveProdukDetail
    }

    fun setPembelian(key : String, id : IntArray, qty : IntArray?) : LiveData<Code>? {
        serviceLiveKonfirmasi = ActivityRepository().setPembelian(key, id, qty)
        return serviceLiveKonfirmasi
    }

    fun getProduk(context: Context) : LiveData<ArrayList<Produk>>? {
        serviceLiveProduk = ActivityRepository().getServiceProduk(context)
        return serviceLiveProduk
    }
}