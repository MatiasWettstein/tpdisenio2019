package tp.disenio.DTO;

public class PolizaDTO {

	private int nroPoliza; //
	private String inicio_vigencia;//
	private String fin_vigencia; //
	private String estado_poliza;//
	private String forma_pago;//
	private float kmPorAnio;//

	public int getNroPoliza() {
		return nroPoliza;
	}
	public void setNroPoliza(int nroPoliza) {
		this.nroPoliza = nroPoliza;
	}
	public String getInicio_vigencia() {
		return inicio_vigencia;
	}
	public void setInicio_vigencia(String inicio_vigencia) {
		this.inicio_vigencia = inicio_vigencia;
	}
	public String getFin_vigencia() {
		return fin_vigencia;
	}
	public void setFin_vigencia(String fin_vigencia) {
		this.fin_vigencia = fin_vigencia;
	}
	public String getEstado_poliza() {
		return estado_poliza;
	}
	public void setEstado_poliza(String estado_poliza) {
		this.estado_poliza = estado_poliza;
	}
	public String getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	public float getKmPorAnio() {
		return kmPorAnio;
	}
	public void setKmPorAnio(float kmPorAnio) {
		this.kmPorAnio = kmPorAnio;
	}


}
