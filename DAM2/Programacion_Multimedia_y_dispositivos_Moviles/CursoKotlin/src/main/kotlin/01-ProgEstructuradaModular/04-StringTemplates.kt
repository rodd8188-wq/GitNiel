package `01-ProgEstructuradaModular`

fun main() {
    val nombre = "Félix Sotelo"
    val edad = 39

    println("Su nombre es: " + nombre + " y tiene: " + edad)
    println("Su nombre es: $nombre y tiene: $edad")
    println("Su nombre es: ${nombre.uppercase()} y el año que viene tendra: ${edad + 1}")

    val json = """
        {
            "nombre": $nombre,
            "edad": $edad
        }
    """.trimIndent()
    val sqlInsertar = """
        INSERT INTO personas (null,$nombre,$edad)
    """.trimIndent()
    println(json)
}