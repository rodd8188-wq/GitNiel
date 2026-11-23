package com.example.calculaniel

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class Calculadora{

    // El número inicial
    private var currentInput = "0"

    // Primer número de la operación
    // La interrogacion es porq puedes ser null
    private var storedValue: Double? = null

    // Operador
    private var pendingOperator: Char? = null

    // Texto secundario de la operacion que se esta haciendo
    private var expression = ""

    // true justo después de pulsar un operador, el siguiente dígito empieza número nuevo
    private var waitingForNumber = false

    // true justo después de pulsar "=", el siguiente dígito empieza una cuenta nueva
    private var justCalculated = false

    // true si estamos mostrando un mensaje de error
    private var hasError = false

    private val formatter = DecimalFormat("0.##########", DecimalFormatSymbols(Locale.US))

    fun getDisplayText(): String = currentInput

    fun getExpressionText(): String = expression

    fun inputDigit(digit: String) {
        prepareForNewNumber()
        // Contamos solo las cifras
        val digitCount = currentInput.replace("-", "").replace(".", "").length
        if (digitCount >= 15) return

        // Si solo hay un "0", lo sustituimos
        if (currentInput == "0")
            currentInput = digit
        else
            currentInput = currentInput + digit
    }

    fun inputDecimalPoint() {
        prepareForNewNumber()
        // Si ya hay un punto, no hacemos nada
        if (currentInput.contains(".")) return
        currentInput += "." // "0" pasa a "0.", "12" pasa a "12."
    }

    fun inputOperator(operator: Char) {
        if (hasError) return
        justCalculated = false

        // Si el usuario pulsa dos operadores seguidos, solo cambiamos el operador
        if (waitingForNumber) {
            pendingOperator = operator
            expression = "${format(storedValue ?: 0.0)} $operator"
            return
        }

        val currentValue = currentInput.toDouble()
        val previousValue = storedValue
        val previousOperator = pendingOperator

        // Si ya había una operación pendiente la resolvemos primero y seguimos con el resultado
        val accumulated = if (previousValue != null && previousOperator != null) {
            operate(previousValue, currentValue, previousOperator) ?: return // null = error
        } else {
            currentValue
        }

        storedValue = accumulated
        pendingOperator = operator
        currentInput = format(accumulated)
        expression = "${format(accumulated)} $operator"
        waitingForNumber = true
    }

    // Se llama al pulsar el igual
    fun calculate() {
        if (hasError) return
        val first = storedValue
        val operator = pendingOperator
        // Falta el segundo número
        if (first == null || operator == null || waitingForNumber) return

        val second = currentInput.toDouble()
        val result = operate(first, second, operator)

        // La expresión completa se muestra arriba
        expression = "${format(first)} $operator ${format(second)} ="

        if (result == null) return // división por cero

        currentInput = format(result)
        storedValue = null
        pendingOperator = null
        justCalculated = true
    }

    // Botón ± cambia el signo del número actual.
    fun toggleSign() {
        if (hasError || waitingForNumber) return
        if (currentInput == "0") return // el cero no tiene signo
        currentInput = if (currentInput.startsWith("-")) {
            currentInput.substring(1)   // quitar el "-"
        } else {
            "-$currentInput"            // añadir el "-"
        }
    }

    // Botón DEL borra el último carácter
    fun deleteLast() {
        if (hasError || waitingForNumber || justCalculated) return
        currentInput = currentInput.dropLast(1)
        // Si no queda nada (o solo queda un "-"), volvemos a "0"
        if (currentInput.isEmpty() || currentInput == "-") currentInput = "0"
    }

    // reinicia todo
    fun clear() {
        currentInput = "0"
        storedValue = null
        pendingOperator = null
        expression = ""
        waitingForNumber = false
        justCalculated = false
        hasError = false
    }

    private fun prepareForNewNumber() {
        if (hasError || justCalculated) clear()
        if (waitingForNumber) {
            currentInput = "0"
            waitingForNumber = false
        }
    }

    /**
     * Hace la operación matemática.
     * Devuelve null si hay un error (división por cero).
     */
    private fun operate(a: Double, b: Double, operator: Char): Double? {
        return when (operator) {
            '+' -> a + b
            '-' -> a - b
            '×' -> a * b
            '÷' -> {
                if (b == 0.0) {
                    null
                } else {
                    a / b
                }
            }
            else -> null
        }
    }

    private fun setError(message: String) {
        hasError = true
        currentInput = message
        storedValue = null
        pendingOperator = null
        waitingForNumber = false
    }

    // Convierte un Double a texto bonito
    private fun format(value: Double): String {
        if (value == 0.0) return "0" // evita que salga "-0"
        return formatter.format(value)
    }
}