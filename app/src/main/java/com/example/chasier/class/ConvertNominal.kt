package com.example.chasier.`class`

import java.text.NumberFormat
import java.util.*

class ConvertNominal() {
    fun formatRupiah(number: Int?): String? {
        val localeID = Locale("in", "ID")
        val formatRupiah =
            NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number)
    }
}