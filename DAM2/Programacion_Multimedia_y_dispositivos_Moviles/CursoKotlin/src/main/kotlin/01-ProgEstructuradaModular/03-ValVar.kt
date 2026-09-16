package `01-ProgEstructuradaModular`

const val PRIMERO = 1
const val IVA_SUPER_REDUCIDO = 5

fun main(){
    var edad = 28
    println(edad)
    edad = 29
    println(edad)

    val nombre = "Félix"
    println(nombre)
    // No se puede reasignar un val.
    // nombre = "Pepe"

    println(PRIMERO)
    println(IVA_SUPER_REDUCIDO)
}