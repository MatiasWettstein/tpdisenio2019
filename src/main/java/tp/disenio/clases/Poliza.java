package tp.disenio.clases;

import java.util.ArrayList;
import java.util.Date;

public class Poliza {

	private int nroPoliza;
	private Cliente cliente;
	private DomicilioRiesgo domicilio_riesgo;
	private Vehiculo vehiculo;
	private int kmPorAnio;
	private MedidasSeguridad seguridad;
	private Siniestros siniestro;
	private float sumaasegurada;
	private ArrayList<Hijo> hijos_declarados;
	private Cobertura tipo_cobertura;
	private Date inicio_vigencia;
	private Date fin_vigencia;
	private String estado_poliza;
	private Premio premio;
	private Descuentos descuento;
	private FormaDePago forma_pago;
	private PolizaModificada poliza_modificada;





	public int getNroPoliza() {
		return nroPoliza;
	}
	public void setNroPoliza(int nroPoliza) {
		this.nroPoliza = nroPoliza;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public DomicilioRiesgo getDomicilio_riesgo() {
		return domicilio_riesgo;
	}
	public void setDomicilio_riesgo(DomicilioRiesgo domicilio_riesgo) {
		this.domicilio_riesgo = domicilio_riesgo;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public int getKmPorAnio() {
		return kmPorAnio;
	}
	public void setKmPorAnio(int kmPorAnio) {
		this.kmPorAnio = kmPorAnio;
	}
	public MedidasSeguridad getSeguridad() {
		return seguridad;
	}
	public void setSeguridad(MedidasSeguridad seguridad) {
		this.seguridad = seguridad;
	}
	public Siniestros getSiniestro() {
		return siniestro;
	}
	public void setSiniestro(Siniestros siniestro) {
		this.siniestro = siniestro;
	}
	public float getSumaasegurada() {
		return sumaasegurada;
	}
	public void setSumaasegurada(float sumaasegurada) {
		this.sumaasegurada = sumaasegurada;
	}
	public ArrayList<Hijo> getHijos_declarados() {
		return hijos_declarados;
	}
	public void setHijos_declarados(ArrayList<Hijo> hijos_declarados) {
		this.hijos_declarados = hijos_declarados;
	}
	public Cobertura getTipo_cobertura() {
		return tipo_cobertura;
	}
	public void setTipo_cobertura(Cobertura tipo_cobertura) {
		this.tipo_cobertura = tipo_cobertura;
	}
	public Date getInicio_vigencia() {
		return inicio_vigencia;
	}
	public void setInicio_vigencia(Date inicio_vigencia) {
		this.inicio_vigencia = inicio_vigencia;
	}
	public Date getFin_vigencia() {
		return fin_vigencia;
	}
	public void setFin_vigencia(Date fin_vigencia) {
		this.fin_vigencia = fin_vigencia;
	}
	public String getEstado_poliza() {
		return estado_poliza;
	}
	public void setEstado_poliza(String estado_poliza) {
		this.estado_poliza = estado_poliza;
	}
	public Premio getPremio() {
		return premio;
	}
	public void setPremio(Premio premio) {
		this.premio = premio;
	}
	public Descuentos getDescuento() {
		return descuento;
	}
	public void setDescuento(Descuentos descuento) {
		this.descuento = descuento;
	}
	public FormaDePago getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(FormaDePago forma_pago) {
		this.forma_pago = forma_pago;
	}
	public PolizaModificada getPoliza_modificada() {
		return poliza_modificada;
	}
	public void setPoliza_modificada(PolizaModificada poliza_modificada) {
		this.poliza_modificada = poliza_modificada;
	}


}
