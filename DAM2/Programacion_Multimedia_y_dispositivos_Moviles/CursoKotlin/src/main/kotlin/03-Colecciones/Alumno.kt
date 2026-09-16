package `03-Colecciones`

import org.jetbrains.kotlinx.dataframe.annotations.DataSchema


@DataSchema
data class Alumno(
    val nombre: String,
    val nota: Double,
    val curso: String
)