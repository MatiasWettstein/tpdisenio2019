package tp.disenio.gestores;

import java.util.ArrayList;

import tp.disenio.DAO.DAOCuota;
import tp.disenio.DAO.DAOReciboPago;
import tp.disenio.DTO.CuotaDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.clases.Cuota;
import tp.disenio.clases.SubsistemaFinanciero;

public class GestorCobro {
	
	private static GestorCobro GCobro ; // Patron Singleton -- Unica instancia tipo gestor creada.

	private GestorCobro(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
	}

	public static GestorCobro getInstance() { // Patron Singleton -- Devuelve la instancia, si no existe la crea
		if ( GCobro == null) {
			GCobro = new GestorCobro();
		}
		return GCobro;
	}
	
	public double obtenerTasaInteres (){
		double ret=0;
		ret = SubsistemaFinanciero.tasaInteres();
		return ret;
	}
	
	public double obtenerDescuentoPorAdelanto (){
		double ret=0;
		ret = SubsistemaFinanciero.descuentoPorAdelanto();
		return ret;
	}
	
	public void pagarCuota (int idCuota) { //COMPLETAR TODO ACA, EN EL COMENTARIO DE ABAJO ESTA ESCRITA LA CONSULTA
		
	/*PreparedStatement st=  con.PrepareStatement ("UPDATE COUTA SET pagada=true WHERE id_cuota = ?")
	 st.setInt(1, idCuota) */
		;
	}
	
	public float aplicarDescuento(float monto) {
		float retorno =0; 
		float descuento = (float) this.obtenerDescuentoPorAdelanto();
		retorno = monto - (monto*descuento);
		return retorno; 
		
	}
	
	public float aplicarInteres(float monto) {
		float retorno =0; 
		float interes = (float) this.obtenerTasaInteres();
		retorno = monto + (monto*interes);
		return retorno; 
		
	}
	
	public boolean registrarPagoCuotaSemestral(CuotaDTO c, String fecha_pago, double monto_total) { //UNICA CUOTA 
		int nroRecibo = DAOReciboPago.cargarReciboPago(fecha_pago, monto_total);
		boolean retorno = DAOCuota.pagarCuota(c, nroRecibo);
		return retorno;
	}
	
	
	public int generarNumeroRecibo() {
		int retorno =0;
		int ultimoID = DAOReciboPago.recupearUltimoNID();
		retorno = ultimoID +1;
		return retorno; 
	}
	
	public int obtenerUltimoNIDReciboPago() {
		int retorno = DAOReciboPago.recupearUltimoNID();
		return retorno; 
	}

	public boolean registrarPagoCuotaMensual(ArrayList<CuotaDTO> cuotas, String fechaPago, Double montoTotal) {
		int nroRecibo = DAOReciboPago.cargarReciboPago(fechaPago, montoTotal);
		boolean retorno = DAOCuota.pagarCuotas(cuotas, nroRecibo);
		return retorno;
	}
	
	public double calcularMontoTotal(ArrayList<Double> montos) {
		double retorno = 0;
		for (Double d: montos) {
			retorno +=d;
		}
		return retorno; 
	}

}
