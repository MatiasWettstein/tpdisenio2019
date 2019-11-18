package tp.disenio.clases;

import tp.disenio.clases.Provincia;

public class Localidad {
	//----------Atributos
	private int id_localidad;
	private String codigoPostal;
	private String nombre;
	private float porcentaje;
	private String idUsuario;
	private Provincia provincia;
	
	// ------------- 
	@Override
	public String toString() {
		return this.nombre;
	}
	
	public int getId_localidad() {
		return id_localidad;
	}
	public void setId_localidad(int id_localidad) {
		this.id_localidad = id_localidad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
	
	
}
