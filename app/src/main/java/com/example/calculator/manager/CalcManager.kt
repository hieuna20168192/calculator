package com.example.calculator.manager

import android.content.Context
import com.example.calculator.util.Formatter.formatString
import com.example.calculator.util.NAN

class CalcManager(val calculate: Calculate, val context: Context) {

    var displayNumber: String? = null
    var displayFormula: String? = null
    var lastKey: String? = null


    init {
        displayNumber = "0"
        displayFormula = ""
        lastKey = ""
    }

    fun numPadEvent(key: String) {

        // Why throwing exception by swap the order of the expressions in if-condition?
        if (key == "." || (key.toInt() in 0..9)) {
            appendNum(key)
        }
    }

    private fun resetValues() {
        displayNumber = "0"
        displayFormula = ""
        lastKey = ""
    }

    private fun appendNum(key: String) {

        // Check valid number to visible
        if ((displayNumber == "0" && key == "0") ||
            (displayNumber!!.contains(".") && key == ".")
        ) return
        else {
            val newValue = formatString(displayNumber!! + key)
            displayNumber = newValue
            this.calculate.setValue(newValue)
        }
    }

    fun handleClear() {
        if (displayNumber.equals(NAN)) {
            handleReset()
        } else {
            val oldValue = displayNumber
            var newValue = "0"
            val len = oldValue!!.length
            var minLen = 1

            if (len > minLen) {
                newValue = oldValue.substring(0, len - 1)
            }

            newValue = newValue.replace("\\.$".toRegex(), "")
            displayNumber = newValue

            newValue = formatString(newValue)
            this.calculate.setValue(newValue)
        }
    }

    fun handleReset() {
        resetValues()
        this.calculate.setValue(displayNumber.toString())
        this.calculate.setFormula(displayFormula.toString())
    }
}