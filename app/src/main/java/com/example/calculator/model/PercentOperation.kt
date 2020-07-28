package com.example.calculator.model

import com.example.calculator.base.BaseOperation
import com.example.calculator.util.Formatter

class PercentOperation(base: Double, second: Double) : BaseOperation(base, second) {

    override fun operation(): String? {
        val result = baseNum /100 * secondNum!!
        return Formatter.doubleToString(result)
    }

}