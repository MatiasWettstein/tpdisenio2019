package tp.disenio.clases;

public class Direccion {
	//----------Atributos
	private String calle;
	private String numero;
	private int piso;
	private String dpto;
	private Localidad localidad;

	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public String getDpto() {
		return dpto;
	}
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

}
