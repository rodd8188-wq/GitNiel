package `01-ProgEstructuradaModular`

fun main() {
    val num = 2
    if (num % 2 == 0){
        println("Es par")
    }else{
        println("Es impar")
    }

    val salida = if (num % 2 == 0) "Es par" else "Es impar"
    println(salida)

    when (num){
        1 -> println("num = 1")
        2 -> println("num = 2")
        else -> {
            println("num no es ni 1 ni 2")
        }
    }

    val salidaDos = when (num){
        1 -> "num = 1"
        2 -> "num = 2"
        else -> {
            "num no es ni 1 ni 2"
        }
    }
    println(salidaDos)

    var dato = 0
    while (dato < 10){
        println(dato)
        dato += 1// dato++
    }

    dato = 10
    do {
        println(dato)
        dato -= 1// dato--
    } while (dato > 0)

    for (i in 1..10 step 2){
        println(i)
    }

    val enteros = IntArray(10)
    for (i in enteros.reversed())
        println(i)

    for (indice in enteros.indices)
        println(indice)

    for (i in enteros.withIndex())// ((index,value) in enteros.withIndex())
        println("${i.index} ${i.value}")

    repeat(2){
        println("patata")
    }
}