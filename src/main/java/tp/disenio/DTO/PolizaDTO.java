package tp.disenio.DTO;

import java.util.ArrayList;
import java.util.Date;

import tp.disenio.clases.*;

public class PolizaDTO {
	
	private int nroPoliza; 
	private String localidad;
	private String provincia; 
	private String marca;
	private String modelo;
	private String motor;
	private String chasis; 
	private String anio_vehiculo;
	private String patente;
	private float kmPorAnio;
	private boolean garage;
	private boolean alarma;
	private boolean dispR;
	private boolean tuercas;
	private String siniestros;
	private ArrayList<HijoDTO> listaHijos;
	private String tipo_cobertura;
	private String inicio_vigencia;
	private String fin_vigencia; 
	private String estado_poliza;

	
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public String getChasis() {
		return chasis;
	}
	public void setChasis(String chasis) {
		this.chasis = chasis;
	}
	public String getAnio_vehiculo() {
		return anio_vehiculo;
	}
	public void setAnio_vehiculo(String string) {
		this.anio_vehiculo = string;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public float getKmPorAnio() {
		return kmPorAnio;
	}
	public void setKmPorAnio(float kmPorAnio) {
		this.kmPorAnio = kmPorAnio;
	}
	public boolean isGarage() {
		return garage;
	}
	public void setGarage(boolean garage) {
		this.garage = garage;
	}
	public boolean isAlarma() {
		return alarma;
	}
	public void setAlarma(boolean alarma) {
		this.alarma = alarma;
	}
	public boolean isDispR() {
		return dispR;
	}
	public void setDispR(boolean dispR) {
		this.dispR = dispR;
	}
	public boolean isTuercas() {
		return tuercas;
	}
	public void setTuercas(boolean tuercas) {
		this.tuercas = tuercas;
	}
	public String getSiniestros() {
		return siniestros;
	}
	public void setSiniestros(String siniestros) {
		this.siniestros = siniestros;
	}
	public ArrayList<HijoDTO> getListaHijos() {
		return listaHijos;
	}
	public void setListaHijos(ArrayList<HijoDTO> listaHijos) {
		this.listaHijos = listaHijos;
	}
	 	
	
	
	
}
