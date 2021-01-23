package com.example.chasier.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Produk(var id1: Int, var nama1: String?, var harga1: Int) : Parcelable {

    @SerializedName("id")
    @Expose
    var id : Int = id1

    @SerializedName("nama")
    @Expose
    var nama : String? = nama1

    @SerializedName("harga")
    @Expose
    var harga : Int = harga1

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nama)
        parcel.writeInt(harga)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Produk> {
        override fun createFromParcel(parcel: Parcel): Produk {
            return Produk(parcel)
        }

        override fun newArray(size: Int): Array<Produk?> {
            return arrayOfNulls(size)
        }
    }

}