package com.example.calculator.model

import com.example.calculator.base.BaseOperation
import com.example.calculator.util.Formatter

class DivideOperation(base: Double, second: Double) : BaseOperation(base, second) {

    override fun operation(): String? {
        val result = baseNum / secondNum!!
        return if (secondNum != 0.0)Formatter.doubleToString(result) else null
    }

}