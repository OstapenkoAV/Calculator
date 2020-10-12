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
        Log.i(TAG, lastResult.toString())
        if (state == State.showResult) {
            state = State.firstArgInput
            inputStr.setLength(0)
        }
        if (state == State.operationSelected) {
            state = State.secondArgInput
            inputStr.setLength(0)
        }
        if (inputStr.length < 9) {
            if (inputStr.isNotEmpty() && inputStr[0] == '0') {
                inputStr.setLength(0)
            }
            when (buttonId) {
                R.id.button_0 -> if (inputStr.isNotEmpty()) {
                    inputStr.append("0")
                }
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
            when (actionSelected) {
                R.id.addition_button -> {
                    inputStr.append(firstArg + secondArg)
                    lastResult = inputStr.toString().toInt()
                    Log.i(TAG, lastResult.toString())
                }
                R.id.subtraction_button -> {
                    inputStr.append(firstArg - secondArg)
                    lastResult = inputStr.toString().toInt()
                    Log.i(TAG, lastResult.toString())
                }
                R.id.multiplication_button -> {
                    inputStr.append(firstArg * secondArg)
                    lastResult = inputStr.toString().toInt()
                    Log.i(TAG, lastResult.toString())
                }
                R.id.division_button -> {
                    inputStr.append(firstArg / secondArg)
                    lastResult = inputStr.toString().toInt()
                    Log.i(TAG, lastResult.toString())
                }
            }
        } else {
            if (inputStr.isNotEmpty() && state == State.firstArgInput) {
                state = State.operationSelected
                firstArg = inputStr.toString().toInt()
                inputStr.setLength(0)
                actionSelected = actionId
            }
        }
    }

    fun getText(): String {
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
        inputStr.setLength(0)
        inputStr.append("0")
    }

    private fun changeState() {
        if (state == State.firstArgInput && lastResult == 0){}
        else if (state == State.firstArgInput ){}
        else if (state == State.operationSelected){}
        else if (state == State.secondArgInput){}
        else if (state == State.showResult){}
    }
}
