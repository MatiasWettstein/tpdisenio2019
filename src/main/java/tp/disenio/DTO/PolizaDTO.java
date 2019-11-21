package tp.disenio.DTO;

public class PolizaDTO {

	private int nroPoliza; //
	private String inicio_vigencia;//
	private String fin_vigencia; //
	private String estado_poliza;//
	private String forma_pago;//
	private int kmPorAnio;//
	private Boolean tuercas;
	private Boolean Garage;
	private Boolean Alarma;
	private Boolean DispRastreo;


	public Boolean getTuercas() {
		return tuercas;
	}
	public void setTuercas(Boolean tuercas) {
		this.tuercas = tuercas;
	}
	public Boolean getGarage() {
		return Garage;
	}
	public void setGarage(Boolean garage) {
		Garage = garage;
	}
	public Boolean getAlarma() {
		return Alarma;
	}
	public void setAlarma(Boolean alarma) {
		Alarma = alarma;
	}
	public Boolean getDispRastreo() {
		return DispRastreo;
	}
	public void setDispRastreo(Boolean dispRastreo) {
		DispRastreo = dispRastreo;
	}
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
	public int getKmPorAnio() {
		return kmPorAnio;
	}
	public void setKmPorAnio(int kmPorAnio) {
		this.kmPorAnio = kmPorAnio;
	}


}
