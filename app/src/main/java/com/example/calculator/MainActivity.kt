package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.calculator.manager.CalcManager
import com.example.calculator.manager.Calculate
import com.example.calculator.util.*
import kotlinx.android.synthetic.main.activity_calculator.*

class MainActivity : AppCompatActivity(), Calculate {

    lateinit var calc: CalcManager
    private var lastKey = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_clear.setOnClickListener {
            calc.handleClear()
        }

        btn_clear.setOnLongClickListener {
            calc.handleReset()
            true
        }

        calc = CalcManager(this, this)
        getButtonIds().forEach {
            it.setOnClickListener { view ->
                lastKey = (view as Button).text.toString()
                calc.numPadEvent(lastKey)
            }
        }

        btn_plus.setOnClickListener {
            calc.handOperation(PLUS)
        }

        btn_minus.setOnClickListener {
            calc.handOperation(MINUS)
        }

        btn_multiply.setOnClickListener {
            calc.handOperation(MULTIPLY)
        }

        btn_divide.setOnClickListener {
            calc.handOperation(DIVIDE)
        }

        btn_equals.setOnClickListener {
            calc.handEqual(EQUALS)
        }

        btn_power.setOnClickListener {
            calc.handOperation(POWER)
        }

        btn_percent.setOnClickListener {
            calc.handOperation(PERCENT)
        }

        btn_root.setOnClickListener {
            calc.handRoot(ROOT)
        }
    }

    private fun getButtonIds() = arrayOf<Button>(btn_decimal, btn_0, btn_1, btn_2, btn_3, btn_4,
                                         btn_5, btn_6, btn_7, btn_8, btn_9)

    override fun setFormula(formula: String) {
        println("operation is $formula")
        txt_formula.text = formula
    }

    override fun setValue(result: String) {
        txt_result.text = result
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Value", calc.displayNumber)
        outState.putString("Formula", calc.displayFormula)
        outState.putString("UI Value", txt_result.text.toString())
        outState.putString("UI Formula", txt_formula.text.toString())
        outState.putString("Last Key", calc.lastKey)
        outState.putString("Last Operation", calc.lastOperation)
        outState.putDouble("Base Number", calc.baseNumber)
        outState.putDouble("Second Number", calc.secondNumber)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        calc.displayNumber = savedInstanceState.getString("Value")
        calc.displayFormula = savedInstanceState.getString("Formula")
        calc.lastOperation = savedInstanceState.getString("Last Operation")
        calc.lastKey = savedInstanceState.getString("Last Key")
        calc.baseNumber = savedInstanceState.getDouble("Base Number")
        calc.secondNumber = savedInstanceState.getDouble("Second Number")
        txt_formula.text = savedInstanceState.getString("UI Formula")
        txt_result.text = savedInstanceState.getString("UI Value")
    }

}
