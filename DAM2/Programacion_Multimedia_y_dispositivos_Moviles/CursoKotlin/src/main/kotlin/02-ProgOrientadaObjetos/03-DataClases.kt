package `02-ProgOrientadaObjetos`

import java.time.Instant

data class Ordenador(
    val numeroSerie: String = Instant.now().toString(),
    var marca: String,
    var modelo: String,
    var ram: Int,
    var disco: Int,
    var ssd:Boolean
)

fun main() {
    val ordenador1 = Ordenador(
        numeroSerie = "02",
        marca = "HP",
        modelo = "Pavillon",
        ram = 12,
        disco = 1000,
        ssd = true
    )
    val ordenador2 = Ordenador(
        marca = "Acer",
        modelo = "Acer guay",
        ram = 8,
        disco = 500,
        ssd = false
    )
    val ordenador3 = Ordenador(
        numeroSerie = "02",
        marca = "HP",
        modelo = "Pavillon",
        ram = 12,
        disco = 1000,
        ssd = true
    )

    println(ordenador1)
    println(ordenador2)

    println(ordenador1.hashCode())

    println(ordenador2 == ordenador1)
    println(ordenador1 == ordenador3)

    val ordenador4 = ordenador2.copy(marca = "lenovo", ssd = true)
    println(ordenador4)

    println(ordenador4.component3())
}