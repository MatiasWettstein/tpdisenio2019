package tp.disenio.DTO;

public class VehiculoDTO {

	private ModeloDTO modelo;
	private int anio;
	private float sumaasegurada;
	private String motor;
	private String chasis;
	private String patente;
	private String id_vehiculo;
	private float porcentaje;



	public ModeloDTO getModelo() {
		return modelo;
	}
	public void setModelo(ModeloDTO modelo) {
		this.modelo = modelo;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public float getSumaasegurada() {
		return sumaasegurada;
	}
	public void setSumaasegurada(float sumaasegurada) {
		this.sumaasegurada = sumaasegurada;
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
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getId_vehiculo() {
		return id_vehiculo;
	}
	public void setId_vehiculo(String id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}
	public float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}


}
