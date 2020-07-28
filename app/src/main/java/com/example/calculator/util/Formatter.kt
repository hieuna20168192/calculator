package com.example.calculator.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object Formatter {
    fun doubleToString(d: Double): String {
        val symbols = DecimalFormatSymbols(Locale.US)
        symbols.decimalSeparator = '.'
        symbols.groupingSeparator = ','

        val formatter = DecimalFormat()
        formatter.maximumFractionDigits = 12
        formatter.decimalFormatSymbols = symbols
        formatter.isGroupingUsed = true
        return formatter.format(d)
    }

    fun stringToDouble(str: String) = str.replace(",", "").toDouble()

    fun formatString(str: String): String {

        if (str.contains(".")) {
            return str
        }

        val doubleValue = Formatter.stringToDouble(str)
        return doubleValue.format()
    }
}

fun Double.format(): String = Formatter.doubleToString(this)