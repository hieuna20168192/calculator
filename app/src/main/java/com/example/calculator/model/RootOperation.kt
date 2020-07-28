package com.example.calculator.model

import com.example.calculator.base.BaseOperation
import com.example.calculator.util.Formatter
import kotlin.math.sqrt

class RootOperation(base: Double) : BaseOperation(base) {

    override fun operation(): String? {
        val result = sqrt(baseNum)
        return Formatter.doubleToString(result)
    }

}