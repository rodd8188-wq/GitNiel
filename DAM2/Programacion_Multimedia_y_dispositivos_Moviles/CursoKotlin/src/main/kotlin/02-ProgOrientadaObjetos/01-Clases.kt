package `02-ProgOrientadaObjetos`

class Animal1(){
    private var nombre: String = ""
    private var edad: Int = 0

    init {
        println("Nuevo animal")
    }

    fun setNombre(nombre:String){
        this.nombre = nombre
    }
    fun getNombre(): String{
        return this.nombre
    }
}

class Animal2 constructor(var nombre: String = "", var edad: Int = 0){
    init {
        println("Nuevo animal")
    }
}

class Animal3(var nombre: String = "", var edad: Int = 0){
    var muerto = false

    constructor(nombre: String,edad: Int,muerto: Boolean) : this(nombre, edad){
        this.muerto = muerto
    }
}

class Animal4(var nombre: String = "", var edad: Int = 0, var muerto: Boolean = false){
    fun metodo1():String{
        var patata = 1
        return "Hola carcola"
    }
}



fun main() {
    val animal1 = Animal1()
    println("Nombre: ${animal1.getNombre()}")
    val animal2 = Animal2("Perro",2)
    println("Nombre: ${animal2.nombre}")
    animal2.nombre = "Calcetines"
    println("Nombre: ${animal2.nombre}")
    val animal31 = Animal3()
    val animal32 = Animal3("Coby",5)
    val animal33 = Animal3("Corcho",17,true)
    val animal34 = Animal3("turron")

}