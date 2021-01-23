package com.example.chasier.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Code (code1 : Int, msg1:String) {
    @SerializedName("code")
    @Expose
    var code : Int = code1

    @SerializedName("msg")
    @Expose
    var msg : String = msg1
}