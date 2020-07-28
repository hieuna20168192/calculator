package com.example.calculator.model
import com.example.calculator.base.BaseOperation
import com.example.calculator.util.DIVIDE
import com.example.calculator.util.MINUS
import com.example.calculator.util.MULTIPLY
import com.example.calculator.util.PLUS

object Factory {
    fun forId(id: String, base: Double, second: Double): BaseOperation? {
        return when (id) {
            PLUS -> PlusOperation(base, second)
            MINUS -> MinusOperation(base, second)
            MULTIPLY -> MultipleOperation(base, second)
            DIVIDE -> DivideOperation(base, second)
            else -> null
        }
    }
}