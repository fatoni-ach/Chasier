package com.example.chasier.api

import com.example.chasier.model.Code
import com.example.chasier.model.Penjualan
import com.example.chasier.model.Produk
import com.example.chasier.model.ProdukDetail
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @GET("kasir-app/public/api/produk")
    fun getProduct(): Call<Array<Produk>>

    @FormUrlEncoded
    @POST("kasir-app/public/api/penjualan/save")
    fun savePembelian(@Header("x-key") key:String,
                      @Field("id_produk[]") id_produk : IntArray,
                      @Field("qty[]")qty : IntArray?) : Call<Code>

    @GET("kasir-app/public/api/penjualan/")
    fun getPenjualan(@Header("x-key") key:String) : Call<ArrayList<Penjualan>>

    @GET("kasir-app/public/api/penjualan/detail")
    fun getDetailPenjualan(@Header("x-key") key:String,
                           @Query("id") id : Int?) : Call<ArrayList<ProdukDetail>>
}