package `02-ProgOrientadaObjetos`

class Coches(var marca: String = "",val año: Int = 2022){
    var color: String = ""
        get() = field.uppercase()
        set(value) {
            field = value.lowercase()
        }

    val isViejo: Boolean
        get() = this.año < 2000

    override fun toString(): String {
        return "${this.color}"
    }
}

fun main(){
    val coche = Coches()

    coche.color = "rEd"
    println(coche.toString())
    println(coche.color)

    println(coche.isViejo)
}