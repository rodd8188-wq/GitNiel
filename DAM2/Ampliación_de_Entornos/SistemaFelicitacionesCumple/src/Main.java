import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String nombreArchivo = "amigos.txt";

        //Leer los amigos del archivo
        ArrayList<Amigo> amigos = leerAmigos(nombreArchivo);

        //Mirar quién cumple años hoy y felicitarle
        LocalDate hoy = LocalDate.now();
        int felicitaciones = 0;

        for (Amigo amigo : amigos) {
            if (amigo.cumpleHoy(hoy)) {
                System.out.println("Para: " + amigo.getEmail());
                System.out.println("Asunto: ¡Feliz cumpleaños!");
                System.out.println();
                System.out.println("¡Feliz cumpleaños, querido/a " + amigo.getNombre() + "!");
                System.out.println("---");
                felicitaciones++;
            }
        }

        System.out.println("Felicitaciones enviadas: " + felicitaciones);
    }

    // Lee el archivo y devuelve la lista de amigos.
    // apellido, nombre, fecha (aaaa/mm/dd), correo
    public static ArrayList<Amigo> leerAmigos(String nombreArchivo) {
        ArrayList<Amigo> amigos = new ArrayList<>();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try {
            Scanner lector = new Scanner(new File(nombreArchivo), "UTF-8");
            
            // la primera línea es la cabecera, la saltamos
            lector.nextLine();

            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();

                if (linea.isEmpty()) {
                	
                	// saltamos las líneas vacías
                    continue;
                }

                String[] partes = linea.split(",");
                String apellido = partes[0].trim();
                String nombre = partes[1].trim();
                LocalDate fecha = LocalDate.parse(partes[2].trim(), formato);
                String email = partes[3].trim();

                amigos.add(new Amigo(apellido, nombre, fecha, email));
            }

            lector.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + nombreArchivo);
        }

        return amigos;
    }
}
