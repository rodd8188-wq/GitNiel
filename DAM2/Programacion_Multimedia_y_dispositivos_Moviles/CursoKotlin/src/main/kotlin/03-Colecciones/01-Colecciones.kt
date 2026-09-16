package `03-Colecciones`

import `02-ProgOrientadaObjetos`.Coches
import java.util.UUID

fun main() {
    val lista = listOf<Int>(1,2,3,4,5,6)

    println(lista)
    println(lista.size)
    //listof read-only
    println(lista.get(4))
    println(lista[0])

    val listaMutable = mutableListOf(1,2,3,4,5,6,7)

    println(listaMutable.get(4))
    println(listaMutable[0])
    println(listaMutable)
    println(listaMutable.size)
    listaMutable.add(7)
    //listaMutable.add(0,0)
    listaMutable.set(1,0)
    //listaMutable.remove(7)
    listaMutable.removeAt(2)
    listaMutable.removeAll { i: Int -> i == 7}
    listaMutable.clear()
    println(listaMutable)

    val set = setOf(1,2,3,4)

    val setMutable = mutableSetOf(1,2,4,5,6)

    setMutable.add(3)
    setMutable.add(6)
    setMutable.add(4)
    println(setMutable)

    val array = ArrayList<String>()

    array.add("hola")
    array.get(0)
    array.add("adios")
    println(array)
    array.removeAt(1)

    val mapMutable = mutableMapOf(1L to "posicion",2L to "patattillas")
    val mapa = mutableMapOf<Long,String>()
    println(mapMutable)
    mapMutable[3L] = "boñiga"
    mapMutable[1L] = "letrero"
    println(mapMutable)
}