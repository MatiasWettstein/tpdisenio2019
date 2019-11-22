package tp.disenio.DTO;

public class DescuentosDTO {

	private int idDescuentos;
	private double descPorUnidadAdicional;
	private double descPorPagoSemestral;
	private double descPorPagoAdelantado;

	public double getDescPorUnidadAdicional() {
		return descPorUnidadAdicional;
	}
	public void setDescPorUnidadAdicional(double d) {
		this.descPorUnidadAdicional = d;
	}
	public double getDescPorPagoSemestral() {
		return descPorPagoSemestral;
	}
	public void setDescPorPagoSemestral(double descPorPagoSemestral) {
		this.descPorPagoSemestral = descPorPagoSemestral;
	}
	public double getDescPorPagoAdelantado() {
		return descPorPagoAdelantado;
	}
	public void setDescPorPagoAdelantado(double d) {
		this.descPorPagoAdelantado = d;
	}
	public int getIdDescuentos() {
		return idDescuentos;
	}
	public void setIdDescuentos(int idDescuentos) {
		this.idDescuentos = idDescuentos;
	}


}