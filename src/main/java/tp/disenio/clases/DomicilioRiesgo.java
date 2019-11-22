package tp.disenio.clases;

public class DomicilioRiesgo {

	private String id_domicilioR;
	private float porcentajeDomicilio;
	private Localidad localidad;

	public String getId_domicilioR() {
		return id_domicilioR;
	}
	public void setId_domicilioR(String id_domicilioR) {
		this.id_domicilioR = id_domicilioR;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public float getPorcentajeDomicilio() {
		return porcentajeDomicilio;
	}
	public void setPorcentajeDomicilio(float porcentajeDomicilio) {
		this.porcentajeDomicilio = porcentajeDomicilio;
	}



}
