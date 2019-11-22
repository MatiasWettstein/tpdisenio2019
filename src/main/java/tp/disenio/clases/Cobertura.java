package tp.disenio.clases;

public class Cobertura {
	private int id_cobertura; 
	private String nombre;
	private float porcentajeTipoCobertura; 
	private TipoCobertura acutal;
	
	
	
	public int getId_cobertura() {
		return id_cobertura;
	}
	public void setId_cobertura(int id_cobertura) {
		this.id_cobertura = id_cobertura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPorcentajeTipoCobertura() {
		return porcentajeTipoCobertura;
	}
	public void setPorcentajeTipoCobertura(float porcentajeTipoCobertura) {
		this.porcentajeTipoCobertura = porcentajeTipoCobertura;
	}
	public TipoCobertura getAcutal() {
		return acutal;
	}
	public void setAcutal(TipoCobertura acutal) {
		this.acutal = acutal;
	} 
	

}
