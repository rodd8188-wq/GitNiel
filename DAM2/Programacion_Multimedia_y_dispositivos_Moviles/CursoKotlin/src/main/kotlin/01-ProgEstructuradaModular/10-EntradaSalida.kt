package `01-ProgEstructuradaModular`

fun main(args: Array<String>) {
    var nombre = ""
    var edad = -1

    do {
        println("¿Como te llamas?")
        nombre = readln()
    }while (nombre.isEmpty())

    do {
        println("¿Cuantos años tienes?")
        edad = readln().toIntOrNull() ?: -1
    }while (edad < 0)

    println("Nombre: ${nombre.uppercase()} y edad: $edad")
}