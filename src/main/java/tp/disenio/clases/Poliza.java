package tp.disenio.clases;

import java.util.ArrayList;
import java.util.Date;

public class Poliza {

	private int nroPoliza; 
	private Cliente cliente;
	private DomicilioRiesgo domicilio_riesgo;
	private Vehiculo vehiculo; 
	private float kmPorAnio;
	private MedidasSeguridad seguridad;
	private Siniestros siniestro;
	private ArrayList<Hijo> hijos_declarados;
	private Cobertura tipo_cobertura;
	private Date inicio_vigencia;
	private Date fin_vigencia; 
	private String estado_poliza;
	private Premio premio;
	private Descuentos descuento;
	private FormaDePago forma_pago;
	private PolizaModificada poliza_modificada;
	
	
	
}
