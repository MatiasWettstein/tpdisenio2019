package tp.disenio.clases;

public class Provincia {
	//----------Atributos
	private String nombre;
	private int id_provincia;
	private String pais;

	@Override
	public String toString() {
		return this.nombre;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId_provincia() {
		return id_provincia;
	}
	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais() {
		this.pais = "Argentina";
	}
}
