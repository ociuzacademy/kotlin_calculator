package com.example.testcalc



import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentInput: String = ""
    private var lastResult: Double = 0.0
    private var lastOperator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)


        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnDot, R.id.btnAdd, R.id.btnSubtract,
            R.id.btnMultiply, R.id.btnDivide, R.id.btnEquals, R.id.btnClear
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { handleInput((it as Button).text.toString()) }
        }
    }

    private fun handleInput(input: String) {
        when (input) {
            "C" -> clear()
            "=" -> calculate()
            "+", "-", "*", "/" -> setOperator(input)
            else -> appendInput(input)
        }
    }

    private fun clear() {
        currentInput = ""
        lastResult = 0.0
        lastOperator = null
        tvResult.text = "0"
    }

    private fun calculate() {
        if (currentInput.isNotEmpty()) {
            val inputNumber = currentInput.toDouble()
            lastResult = when (lastOperator) {
                "+" -> lastResult + inputNumber
                "-" -> lastResult - inputNumber
                "*" -> lastResult * inputNumber
                "/" -> lastResult / inputNumber
                else -> inputNumber
            }
            tvResult.text = lastResult.toString()
            currentInput = ""
        }
    }

    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            calculate() // Use the current input to update the last result
        }
        lastOperator = operator
    }

    private fun appendInput(input: String) {
        if (input == "." && currentInput.contains(".")) return
        currentInput += input
        tvResult.text = currentInput
    }
}
