package com.example.calculator.model
import com.example.calculator.base.BaseOperation
import com.example.calculator.util.*

object Factory {
    fun forId(id: String, base: Double, second: Double): BaseOperation? {
        return when (id) {
            PLUS -> PlusOperation(base, second)
            MINUS -> MinusOperation(base, second)
            MULTIPLY -> MultipleOperation(base, second)
            DIVIDE -> DivideOperation(base, second)
            PERCENT -> PercentOperation(base, second)
            POWER -> PowerOperation(base, second)
            ROOT -> RootOperation(base)
            else -> null
        }
    }
}
