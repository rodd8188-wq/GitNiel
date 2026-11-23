package com.example.calculaniel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // El motor con toda la lógica. "val" porque nunca cambiamos el objeto, solo su estado.
    private val engine = Calculadora()

    // Las inicializamos más tarde en el oncreate
    private lateinit var tvResult: TextView
    private lateinit var tvExpression: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        tvExpression = findViewById(R.id.tvExpression)

        // Grupos de botones
        setupDigitButtons()
        setupOperatorButtons()
        setupSpecialButtons()

        updateDisplay() // El estado inicial que es 0
    }

    // Botones del 0 al 9
    private fun setupDigitButtons() {
        // Lista con los ids de todos los botones numéricos
        val digitButtonIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        // Recorremos la lista y a cada botón le ponemos un oyente
        digitButtonIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener { view ->
                // "view" es el botón pulsado; su texto ("7", "3"...) es el dígito
                val digit = (view as Button).text.toString()
                engine.inputDigit(digit)
                updateDisplay()
            }
        }

        // El punto decimal
        findViewById<Button>(R.id.btnDot).setOnClickListener {
            engine.inputDecimalPoint()
            updateDisplay()
        }
    }

    // Botones + - × ÷ *
    private fun setupOperatorButtons() {
        // mapOf relaciona el id de cada botón con su símbolo
        val operators = mapOf(
            R.id.btnAdd to '+',
            R.id.btnSubtract to '-',
            R.id.btnMultiply to '×',
            R.id.btnDivide to '÷'
        )

        // Para cada pareja (id, símbolo) configuramos el clic
        operators.forEach { (buttonId, symbol) ->
            findViewById<Button>(buttonId).setOnClickListener {
                engine.inputOperator(symbol)
                updateDisplay()
            }
        }
    }

    // Botones C DEL ± e =
    private fun setupSpecialButtons() {
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            engine.clear()
            updateDisplay()
        }
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            engine.deleteLast()
            updateDisplay()
        }
        findViewById<Button>(R.id.btnSign).setOnClickListener {
            engine.toggleSign()
            updateDisplay()
        }
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            engine.calculate()
            updateDisplay()
        }
    }

    // Copia el estado de esto a las dos pantallas
    //tras cada click
    private fun updateDisplay() {
        tvResult.text = engine.getDisplayText()
        tvExpression.text = engine.getExpressionText()
    }
}