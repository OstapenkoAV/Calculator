package com.example.calculator

import android.util.Log


class CalculatorLogic {
    private var firstArg = 0
    private var secondArg = 0
    private var lastResult = 0
    private val TAG = javaClass.simpleName


    private var inputStr = StringBuilder()

    private var actionSelected = 0

    private var state: State

    init {
        state = State.firstArgInput
    }

    fun onNumberPressed(buttonId: Int) {
        if (state == State.showResult) {
            state = State.firstArgInput
            inputStr.setLength(0)
        }
        if (state == State.operationSelected) {
            state = State.secondArgInput
            inputStr.setLength(0)
        }
        if (inputStr.length < 15) {
            if (inputStr.isNotEmpty() && inputStr[0] == '0') {
                inputStr.setLength(0)
            }
            when (buttonId) {
                R.id.button_0 -> inputStr.append("0")
                R.id.button_1 -> inputStr.append("1")
                R.id.button_2 -> inputStr.append("2")
                R.id.button_3 -> inputStr.append("3")
                R.id.button_4 -> inputStr.append("4")
                R.id.button_5 -> inputStr.append("5")
                R.id.button_6 -> inputStr.append("6")
                R.id.button_7 -> inputStr.append("7")
                R.id.button_8 -> inputStr.append("8")
                R.id.button_9 -> inputStr.append("9")
            }
        }
    }

    fun onActionPressed(actionId: Int) {
        if (actionId == R.id.equal_button && state == State.secondArgInput && inputStr.isNotEmpty()) {
            secondArg = inputStr.toString().toInt()
            state = State.showResult
            inputStr.setLength(0)
            lastResult = 0
            calculate()
        } else if (actionId != R.id.equal_button) {
            if (inputStr.isNotEmpty() && state == State.firstArgInput) {
                firstArg = inputStr.toString().toInt()
            } else if (lastResult != 0 && state == State.showResult) {
                firstArg = lastResult
            }
            state = State.operationSelected
            inputStr.setLength(0)
            actionSelected = actionId
        }
    }

    private fun calculate() {
        when (actionSelected) {
            R.id.addition_button -> {
                inputStr.append(firstArg + secondArg)
                lastResult = inputStr.toString().toInt()
            }
            R.id.subtraction_button -> {
                inputStr.append(firstArg - secondArg)
                lastResult = inputStr.toString().toInt()
            }
            R.id.multiplication_button -> {
                inputStr.append(firstArg * secondArg)
                lastResult = inputStr.toString().toInt()
            }
            R.id.division_button -> {
                inputStr.append(firstArg / secondArg)
                lastResult = inputStr.toString().toInt()
            }
        }
    }

    fun showText(): String {
        val string = StringBuilder()
        return when (state) {
            State.operationSelected -> string.append(getOperationSymbol()).toString()
            else -> inputStr.toString()
        }
    }

    private fun getOperationSymbol(): Char {
        return when (actionSelected) {
            R.id.addition_button -> '+'
            R.id.subtraction_button -> '-'
            R.id.multiplication_button -> '*'
            R.id.division_button -> '/'
            else -> ' '
        }
    }

    fun reset() {
        state = State.firstArgInput
        lastResult = 0
        inputStr.setLength(0)
        inputStr.append("0")
    }
}
