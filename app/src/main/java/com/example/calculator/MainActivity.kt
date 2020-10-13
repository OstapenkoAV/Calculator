package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var numberIds: List<Int>
    private lateinit var operationsIds: List<Int>
    private var resetId = 0

    private lateinit var calculator: CalculatorLogic
    private lateinit var inputTextField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberIds = listOf(
            R.id.button_0,
            R.id.button_1,
            R.id.button_2,
            R.id.button_3,
            R.id.button_4,
            R.id.button_5,
            R.id.button_6,
            R.id.button_7,
            R.id.button_8,
            R.id.button_9
        )

        operationsIds = listOf(
            R.id.addition_button,
            R.id.subtraction_button,
            R.id.multiplication_button,
            R.id.division_button,
            R.id.equal_button
        )
        calculator = CalculatorLogic()
        inputTextField = findViewById(R.id.input_text_field)
        setOnClickListeners()

    }

    fun setOnClickListeners() {
        val numberButtonClickListener = View.OnClickListener {
            calculator.onNumberPressed(it.id)
            inputTextField.text = calculator.showText()
        }
        for (numberBtn in numberIds) {
            findViewById<Button>(numberBtn).setOnClickListener(numberButtonClickListener)
        }

        val actionButtonClickListener = View.OnClickListener {
            calculator.onActionPressed(it.id)
            inputTextField.text = calculator.showText()
        }

        for (actionBtn in operationsIds) {
            findViewById<Button>(actionBtn).setOnClickListener(actionButtonClickListener)
        }

        findViewById<Button>(R.id.reset_button).setOnClickListener {
            calculator.reset()
            inputTextField.text = calculator.showText()
        }
    }
}