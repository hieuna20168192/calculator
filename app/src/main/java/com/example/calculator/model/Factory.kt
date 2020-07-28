package com.example.calculator.model
import com.example.calculator.base.BaseOperation
import com.example.calculator.util.PLUS

object Factory {
    fun forId(id: String, base: Double, second: Double): BaseOperation? {
        return when (id) {
            PLUS -> PlusOperation(base, second)
            else -> null
        }
    }
}