package com.goya.institutos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//@Table(name = "productos")			//Nos permite poner otro nombre a la tabla que no sea el de la clase
public class Alumno {
	
	@Id
	@Column(length = 9)
	private String DNI;
	
	@Column(length = 50, nullable = false)
	private String nombre;
	
	@Column(length = 150, nullable = false)
	private String apellidos;
	
	@Column(nullable = false)
	private int edad;
	
	@Column(nullable = false)
	private double matricula;
	
	// JPA exige siempre un constructor sin argumentos
	public Alumno() {
	}
	
	public Alumno(String DNI, String nombre, String apellidos, int edad, double matricula) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.matricula = matricula;
	}
	
	public String getDNI() {
		return DNI;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public double getMatricula() {
		return matricula;
	}
	
	public void setDNI(String DNI) {
		this.DNI = DNI;
	}
	
	public void setnNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void setMatricula(double matricula) {
		this.matricula = matricula;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", DNI, nombre, apellidos, edad, matricula);
	}
}
