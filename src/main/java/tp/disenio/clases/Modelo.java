package tp.disenio.clases;

import java.util.ArrayList;

import tp.disenio.clases.Anio;
import tp.disenio.clases.Marca;

public class Modelo {
	private int idModelo;
	private String nombre;
	private float porcentaje;
	private Anio anio;
	private Marca marca;
	
	@Override
	public String toString() {
		return this.nombre;
	}

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Anio getAnio() {
		return anio;
	}

	public void setAnio(Anio anio) {
		this.anio = anio;
	}

	
	
}
