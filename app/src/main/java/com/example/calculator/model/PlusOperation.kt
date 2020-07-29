package com.example.calculator.model

import com.example.calculator.base.BaseOperation
import com.example.calculator.util.Formatter

class PlusOperation(base: Double, second: Double) : BaseOperation(base, second) {

    override fun operation(): String? {
        val result = baseNum + secondNum!!
        return Formatter.doubleToString(result)
    }

}

