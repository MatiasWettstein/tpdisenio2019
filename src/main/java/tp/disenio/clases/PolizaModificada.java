package tp.disenio.clases;

import java.util.ArrayList;

public class PolizaModificada {
	
	private int id_polizaMod;
	private Anio anio_mod; //REVISAR
	private String patente;
	private String motor;
	private String chasis;
	private float kmPorAnio;
	private String nroSiniestros; // REVISAR
	private MedidasSeguridad medidas_seguridad;// REVISAR
	private Cobertura cobertura;// REVISAR
	private ArrayList<Hijo> hijos_declarados;
	public int getId_polizaMod() {
		return id_polizaMod;
	}
	public void setId_polizaMod(int id_polizaMod) {
		this.id_polizaMod = id_polizaMod;
	}
	public Anio getAnio_mod() {
		return anio_mod;
	}
	public void setAnio_mod(Anio anio_mod) {
		this.anio_mod = anio_mod;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
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
	public float getKmPorAnio() {
		return kmPorAnio;
	}
	public void setKmPorAnio(float kmPorAnio) {
		this.kmPorAnio = kmPorAnio;
	}
	public String getNroSiniestros() {
		return nroSiniestros;
	}
	public void setNroSiniestros(String nroSiniestros) {
		this.nroSiniestros = nroSiniestros;
	}
	public MedidasSeguridad getMedidas_seguridad() {
		return medidas_seguridad;
	}
	public void setMedidas_seguridad(MedidasSeguridad medidas_seguridad) {
		this.medidas_seguridad = medidas_seguridad;
	}
	public Cobertura getCobertura() {
		return cobertura;
	}
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}
	public ArrayList<Hijo> getHijos_declarados() {
		return hijos_declarados;
	}
	public void setHijos_declarados(ArrayList<Hijo> hijos_declarados) {
		this.hijos_declarados = hijos_declarados;
	}
	

}
