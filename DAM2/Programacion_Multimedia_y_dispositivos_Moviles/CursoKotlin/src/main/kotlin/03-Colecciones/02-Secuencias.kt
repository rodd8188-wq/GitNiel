package `03-Colecciones`

val alumnos = listOf(
    Alumno("Javier",5.0,"DAM"),
    Alumno("Ana",8.0,"DAW"),
    Alumno("Félix",10.0,"ASIR"),
    Alumno("Alejandra",4.0,"DAM"),
    Alumno("Marina",3.0,"ASIR"),
    Alumno("Manolo",10.0,"DAM")
)

fun main() {
    val alumnoFiltro1 = alumnos.asSequence()
        .groupBy { it.curso }
        .map { it.value.maxByOrNull { it.nota } }
        .toList()
    println(alumnoFiltro1)

    val alumnoFiltro2 = alumnos.asSequence()
        .groupBy { it.nota }

    println(alumnoFiltro2)
}