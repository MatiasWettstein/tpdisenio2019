package tp.disenio.clases;

public class Marca {
	private int idMarca;
	private String nombre;
	
	
	@Override
	public String toString() {
		return this.nombre;
	}
	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
