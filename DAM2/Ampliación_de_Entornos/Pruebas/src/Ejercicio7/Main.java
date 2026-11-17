package Ejercicio7;

public class Main {

    public static void main(String[] args) {

    }

    public void guardarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException();
        }
        validarEstudiante(estudiante);
        repositorio.save(estudiante);
        logger.info("Estudiante guardado");
    }

    public void guardarProfesor(Profesor profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException();
        }
        validarProfesor(profesor);
        repositorio.save(profesor);
        logger.info("Profesor guardado");
    }
}
