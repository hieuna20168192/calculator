package com.example.calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.calculator.manager.CalcManager
import com.example.calculator.manager.Calculate
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
    }

    private fun getButtonIds() = arrayOf<Button>(btn_decimal, btn_0, btn_1, btn_2, btn_3, btn_4,
                                         btn_5, btn_6, btn_7, btn_8, btn_9)

    override fun setFormula(formula: String) {
        println("Formula is $formula")
    }

    override fun setValue(result: String) {
        txt_result.text = result
    }

}