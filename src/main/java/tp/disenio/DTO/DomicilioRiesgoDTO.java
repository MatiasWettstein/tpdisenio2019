package tp.disenio.DTO;

public class DomicilioRiesgoDTO {

	private String id_domicilioR;
	private LocalidadDTO localidad;
	private float porcentajeDomicilio;


	public String getId_domicilioR() {
		return id_domicilioR;
	}
	public void setId_domicilioR(String id_domicilioR) {
		this.id_domicilioR = id_domicilioR;
	}
	public LocalidadDTO getLocalidad() {
		return localidad;
	}
	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}
	public float getPorcentajeDomicilio() {
		return porcentajeDomicilio;
	}
	public void setPorcentajeDomicilio(float porcentajeDomicilio) {
		this.porcentajeDomicilio = porcentajeDomicilio;
	}

}
