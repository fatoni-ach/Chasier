package com.example.chasier.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProdukDetail (var id1: Int, var nama1: String?, var harga1: Int, var qty1 : Int) {
    @SerializedName("id")
    @Expose
    var id : Int = id1

    @SerializedName("nama")
    @Expose
    var nama : String? = nama1

    @SerializedName("harga")
    @Expose
    var harga : Int = harga1

    @SerializedName("qty")
    @Expose
    var qty : Int = qty1
}