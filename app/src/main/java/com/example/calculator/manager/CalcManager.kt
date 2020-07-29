package com.example.calculator.manager

import android.content.Context
import android.widget.Toast
import com.example.calculator.model.Factory
import com.example.calculator.util.*
import com.example.calculator.util.Formatter.formatString

class CalcManager(private val calculate: Calculate, private val context: Context) {

    var displayNumber: String? = null
    var displayFormula: String? = null
    var lastKey: String? = null
    var lastOperation: String? = null
    var firstOperation: Boolean = false
    var baseNumber: Double = 0.0
    var secondNumber: Double = 0.0

    init {
        displayNumber = "0"
        displayFormula = ""
        lastOperation = ""
        lastKey = ""
    }

    fun numPadEvent(key: String) {

        if (key == "." || (key.toInt() in 0..9)) {
            appendNum(key)
        }
    }

    private fun resetValues() {
        displayNumber = "0"
        displayFormula = ""
        lastOperation = ""
        lastKey = ""
        firstOperation = true
        baseNumber = 0.0
        secondNumber = 0.0
    }

    private fun appendNum(key: String) {

        if (!firstOperation) {
            displayNumber = ""
            this.calculate.setValue(displayNumber!!)
        }

        // The first time typing digits, not yet press any operation
        // Check valid number to visible
        if ((displayNumber == "0" && key == "0") ||
            (displayNumber!!.contains(".") && key == ".")
        ) return
        else {
            lastKey = key
            val newValue = formatString(displayNumber!! + key)
            displayNumber = newValue
            this.calculate.setValue(newValue)
            firstOperation = true
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

    private fun setFormula(value: String) {
        this.calculate.setFormula(value)
        displayFormula = value
    }

    private fun updateFormula() {
        val first = baseNumber.format()
        val second = secondNumber.format()
        val sign = getSign(lastOperation)
        println("sign = $sign")
        when {
            sign == "√" -> setFormula(sign + first)
            sign == "!" -> setFormula(first + sign)
            sign.isNotEmpty() -> {
                if (secondNumber == 0.0 && sign == "/") {
                    Toast.makeText(context, "Division by Zero is undefined", Toast.LENGTH_LONG)
                        .show()
                }
                setFormula(first + sign + second)
            }
        }
    }

    private fun getSign(lastOperation: String?): String {
        return when (lastOperation) {
            PLUS -> "+"
            MINUS -> "-"
            MULTIPLY -> "*"
            DIVIDE -> "/"
            PERCENT -> "%"
            POWER -> "^"
            ROOT -> "√"
            FACTORIAL -> "!"
            else -> ""
        }
    }

    fun handOperation(operation: String) {
        lastKey = ""
        handResult()
        lastOperation = operation
    }

    fun handEqual(operation: String) {
        if (lastKey!!.isNotEmpty()) {
            handOperation(operation)
            firstOperation = true
        }
    }

    fun handRoot(operation: String) {
        lastOperation = operation
        baseNumber = Formatter.stringToDouble(displayNumber!!)
        handResult()
        lastOperation = ""
    }

    private fun handResult() {

        secondNumber = Formatter.stringToDouble(displayNumber!!)
        updateFormula()
        val result = Factory.forId(lastOperation!!, baseNumber, secondNumber)

        if (result != null) {
            displayNumber = result.operation()
            if (result.operation() != null) {
                baseNumber = Formatter.stringToDouble(result.operation()!!)
                calculate.setValue(result.operation()!!)
            }
        } else {
            baseNumber = Formatter.stringToDouble(displayNumber!!)
        }

        firstOperation = false
    }
}

