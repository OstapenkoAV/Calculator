package com.example.calculator

class CalculatorLogic {
    private var firstArg = 0
    private var secondArg = 0

    private var inputStr = StringBuilder()

    private var actionSelected = 0

    private var state: State

    init {
        state = State.firstArgInput
    }

    fun onNumberPressed(buttonId: Int) {

        if (state == State.resultShow) {
            state = State.firstArgInput
            inputStr.setLength(0)
        }

        if (inputStr.length < 9) {
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
            state = State.resultShow
            inputStr.setLength(0)

            when (actionSelected) {
                R.id.addition_button -> inputStr.append(firstArg + secondArg)
                R.id.subtraction_button -> inputStr.append(firstArg - secondArg)
                R.id.multiplication_button -> inputStr.append(firstArg * secondArg)
                R.id.division_button -> inputStr.append(firstArg / secondArg)
            }

        } else {
            if (inputStr.isNotEmpty() && state == State.firstArgInput) {
                firstArg = inputStr.toString().toInt()
                state = State.secondArgInput
                inputStr.setLength(0)
                actionSelected = actionId
            }
        }
    }

    fun getText(): String {
        return inputStr.toString()
    }
}