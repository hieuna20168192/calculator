package com.example.calculator.base

// Use class for a polymorphism that we have various implements
// based on each specific operation input

abstract class BaseOperation(open val baseNum: Double, open val secondNum: Double? = 0.0) {

    abstract fun operation(): String?

}
