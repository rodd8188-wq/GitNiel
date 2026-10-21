package com.example.proyectolightsoutdaniel

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // Los 9 botones del tablero :
    //   0 1 2
    //   3 4 5
    //   6 7 8
    private lateinit var botones: List<Button>

    private var pulsaciones = 0
    private lateinit var sonido: MediaPlayer
    private lateinit var cronometro: Chronometer

    // false = color primario, true = color secundario
    private val estado = BooleanArray(9)

    private var colorPrimario = 0
    private var colorSecundario = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargamos los colores de colors.xml
        colorPrimario = ContextCompat.getColor(this, R.color.primario)
        colorSecundario = ContextCompat.getColor(this, R.color.secundario)

        // Buscamos los 9 botones del layout
        botones = listOf(
            findViewById(R.id.b0), findViewById(R.id.b1), findViewById(R.id.b2),
            findViewById(R.id.b3), findViewById(R.id.b4), findViewById(R.id.b5),
            findViewById(R.id.b6), findViewById(R.id.b7), findViewById(R.id.b8)
        )

        // Iniciar el cronometro, lo ponemos a 0 y lo empezamos
        cronometro = findViewById(R.id.cronometro)
        cronometro.base = android.os.SystemClock.elapsedRealtime()
        cronometro.start()

        // A cada botonn le decimos lo que hacer cuando se pulsa
        for (i in 0..8) {
            botones[i].setOnClickListener {
                pulsar(i)
                ejecutarSonidoBtn()
                sumarPulsacion()
            }
        }


        // Boton Reiniciar
        findViewById<Button>(R.id.btnReiniciar).setOnClickListener {
            reiniciar()
            reiniciarPulsacion()
            reiniciarCronometro()
            cronometro.start()
        }

        reiniciar() // dejamos el tablero en su estado inicial
    }
    private fun ejecutarSonidoBtn(){
        sonido = MediaPlayer.create(this, R.raw.btnsound)
        sonido.start()
        sonido.setOnCompletionListener {
            it.release()
        }
    }
    private fun ejecutarSonidoVictoria(){
        sonido = MediaPlayer.create(this, R.raw.victorysound2)
        sonido.start()
        sonido.setOnCompletionListener {
            it.release()
        }
    }
    private fun reiniciarCronometro(){
        cronometro.base = android.os.SystemClock.elapsedRealtime()
    }
    private fun reiniciarPulsacion(){
        pulsaciones = 0
        mostrarPulsacion()
    }
    private fun sumarPulsacion(){
        pulsaciones++
        mostrarPulsacion()
    }

    private fun mostrarPulsacion(){
        val texto = findViewById<TextView>(R.id.tvPulsaciones)
        texto.text = "Pulsaciones: " + pulsaciones
    }

    // Al pulsar cualquiera de las casillas
    private fun pulsar(i: Int) {
        val fila = i / 3      // 0, 1, 2
        val columna = i % 3   // 0, 1, 2

        cambiar(i)                              // la casilla pulsada
        if (fila > 0) cambiar(i - 3)            // la de arriba
        if (fila < 2) cambiar(i + 3)            // la de abajo
        if (columna > 0) cambiar(i - 1)         // la de la izquierda
        if (columna < 2) cambiar(i + 1)         // la de la derecha

        pintar()

        if (hayVictoria()) {
            Toast.makeText(this, "Vamoooos, en solo " + (pulsaciones+1) + " pulsaciones!!!!", Toast.LENGTH_LONG).show()
        }
    }

    // Cambia el estado de una casilla, de primario a secundario y al reves
    private fun cambiar(i: Int) {
        estado[i] = !estado[i]
    }

    // Pinta cada boton segun su estado
    private fun pintar() {
        for (i in 0..8) {
            if (estado[i]) {
                botones[i].setBackgroundColor(colorSecundario)
            } else {
                botones[i].setBackgroundColor(colorPrimario)
            }
        }
    }

    // Victoria
    // Si las casillas estan todas al otro color
    private fun hayVictoria(): Boolean {
        for (i in 0..8) {
            if (!estado[i]) {
                return false
            }
        }
        ejecutarSonidoVictoria()
        cronometro.stop()
        return true
    }

    // Todas las casillas vuelven al color primario
    private fun reiniciar() {
        for (i in 0..8) {
            estado[i] = false
        }
        pintar()
    }
}