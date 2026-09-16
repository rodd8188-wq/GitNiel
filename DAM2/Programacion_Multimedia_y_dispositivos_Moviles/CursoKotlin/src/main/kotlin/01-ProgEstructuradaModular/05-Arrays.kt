package `01-ProgEstructuradaModular`

fun main() {
    //Unidimensional
    val array1 = arrayOf(1,2,3,4,5,6)
    val array2 = IntArray(10)
    println(array1)
    println(array2)
    val array3 = IntArray(10){ i -> i * 2 }
    println(array3[5])
    //Multidimensional
    val array4 = Array(3){IntArray(3)}
    println(array4[1][2])
    println(array4[0])
}