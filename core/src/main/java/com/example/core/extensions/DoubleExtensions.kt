package com.example.core.extensions

import java.text.DecimalFormat

fun Double.toCurrencyString(): String {
    val numberFormat = DecimalFormat("######.00") // just to keep the trailing zeros
    return numberFormat.format(this).plus(" $")
}
