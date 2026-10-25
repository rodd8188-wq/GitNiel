import java.time.LocalDate;

// Esta clase guarda los datos de un amigo.
public class Amigo {

    private String apellido;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String email;

    public Amigo(String apellido, String nombre, LocalDate fechaNacimiento, String email) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    //Devuelve true si hoy es el cumpleaños de este tio
    public boolean cumpleHoy(LocalDate hoy) {
        int diaNacimiento = fechaNacimiento.getDayOfMonth();
        int mesNacimiento = fechaNacimiento.getMonthValue();

        //En los años que no son bisiestos lo felicitamos el 28. (Si nació el 29 de febrero)
        if (mesNacimiento == 2 && diaNacimiento == 29 && !hoy.isLeapYear()) {
            return hoy.getMonthValue() == 2 && hoy.getDayOfMonth() == 28;
        }

        return diaNacimiento == hoy.getDayOfMonth() && mesNacimiento == hoy.getMonthValue();
    }
}
