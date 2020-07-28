package com.example.calculator.base

abstract class BaseOperation(open val baseNum: Double, open val secondNum: Double? = 0.0) {

    abstract fun operation(): String

}