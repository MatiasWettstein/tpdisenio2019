package tp.disenio.DTO;

public class ModeloDTO {

	private String nombre;
	private int idmodelo;
	private MarcaDTO marca;



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdmodelo() {
		return idmodelo;
	}
	public void setIdmodelo(int idmodelo) {
		this.idmodelo = idmodelo;
	}
	public MarcaDTO getMarca() {
		return marca;
	}
	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}


}
