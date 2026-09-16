package `01-ProgEstructuradaModular`

fun main() {
    procedimiento()

    println(miFuncionConParametros("Félix"))
    println(miFuncionConParametros("María",10.0))

    val entero = miFuncionDevuelve()
}

fun procedimiento() {
    println("Hola soy un procedimiento.")
}

fun miFuncion(){
    //lo que quieras
}

fun miFuncionDevuelve() : Int{
    return 1
}

fun miFuncionConParametros(nombre: String, nota: Double = 0.0): String{
    return "Su nombre es $nombre y  ha obtenido un $nota."
}

fun miFuncionCorta() = "Hola caracola"

