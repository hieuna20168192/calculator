package com.example.calculator.model

import com.example.calculator.base.BaseOperation
import com.example.calculator.util.Formatter
import kotlin.math.pow

class PowerOperation(base: Double, second: Double) : BaseOperation(base, second) {

    override fun operation(): String? {
        var result = baseNum.pow(secondNum!!)
        if (java.lang.Double.isInfinite(result) ||
                java.lang.Double.isNaN(result))
            result = 0.0
        return Formatter.doubleToString(result)
    }

