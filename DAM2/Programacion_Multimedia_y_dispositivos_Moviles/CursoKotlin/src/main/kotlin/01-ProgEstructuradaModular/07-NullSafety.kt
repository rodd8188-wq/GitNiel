package `01-ProgEstructuradaModular`

fun main() {
    var nombre: String? = null
    println(nombre)
    nombre = "Félix"
    println(nombre)

    var otraCadena: String? = null
    println(otraCadena?.length)
    otraCadena = "Patata"
    println(otraCadena?.length)

    var edadDos: Int? =null
    var edad = edadDos ?: 0
    println(edad)
    var numero = edadDos ?: "No especificado."
    println(numero)
}