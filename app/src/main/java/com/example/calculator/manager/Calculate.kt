package com.example.calculator.manager

import android.content.Context

interface Calculate {
    fun setFormula(formula: String)
    fun setValue(result: String)
}