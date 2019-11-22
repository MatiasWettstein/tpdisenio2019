package tp.disenio.DTO;

public class PremioDTO {

	private int idPremio;
	private float prima;
	private float derechoEmision;
	private float montoTotal;


	public float getPrima() {
		return prima;
	}
	public void setPrima(float prima) {
		this.prima = prima;
	}
	public float getDerechoEmision() {
		return derechoEmision;
	}
	public void setDerechoEmision(float derechoEmision) {
		this.derechoEmision = derechoEmision;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public int getIdPremio() {
		return idPremio;
	}
	public void setIdPremio(int idPremio) {
		this.idPremio = idPremio;
	}


}
