package Default;

public class ContactoJSON {
	
	private String nombre, telefono, dni;
	
	public ContactoJSON(String nom, String dni, String telef) {
		
		this.nombre = nom;
		this.telefono = telef;
		this.dni = dni;
		
	}
	
	public void mostrar() {
		
		System.out.println("Nombre: " + this.nombre);
		System.out.println("Telefono: " + this.telefono);
		System.out.println("DNI: " + this.dni);
		
	}
	
}
