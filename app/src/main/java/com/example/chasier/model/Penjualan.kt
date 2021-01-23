package com.example.chasier.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Penjualan (id1 : Int, tanggal1 : String, harga1 : Int, created_at1 : String, created_by1 :Int,
                 updated_at1 :String, updated_by1 : Int, deleted_at1 : String, deleted_by1 : Int) {
    @SerializedName("id")
    @Expose
    var id : Int = id1

    @SerializedName("tanggal")
    @Expose
    var tanggal : String = tanggal1

    @SerializedName("harga")
    @Expose
    var harga : Int = harga1

    @SerializedName("created_at")
    @Expose
    var created_at : String = created_at1

    @SerializedName("created_by")
    @Expose
    var created_by : Int = created_by1

    @SerializedName("updated_at")
    @Expose
    var updated_at : String = updated_at1

    @SerializedName("updated_by")
    @Expose
    var updated_by : Int = updated_by1

    @SerializedName("deleted_at")
    @Expose
    var deleted_at : String = deleted_at1

    @SerializedName("deleted_by")
    @Expose
    var deleted_by : Int = deleted_by1
}