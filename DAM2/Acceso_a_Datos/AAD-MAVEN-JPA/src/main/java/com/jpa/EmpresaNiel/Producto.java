package com.jpa.EmpresaNiel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(name = "productos")			//Nos permite poner otro nombre a la tabla que no sea el de la clase
public class Producto {
	
	@Id			//Indica la clave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)			//Hace que sea un campo autoincremental
	private Long id;
	
	@Column(name = "producto", length = 100, nullable = false, unique = true)
	private String nombre;			//Por defecto es un VARCHAR(255)
	
	@Column(nullable = false)
	private double precio;
	
	// JPA exige siempre un constructor sin argumentos
	public Producto() {
	}
	
	public Producto(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
		
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "(" + id + ")" + " - " + "Nombre: " + nombre + " | Precio: " + precio;
	}
}
