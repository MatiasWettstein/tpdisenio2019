package tp.disenio.gestores;

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
	
	public float obtenerTasaInteres (){
		float ret=0;
		ret = SubsistemaFinanciero.tasaInteres();
		return ret;
	}
	
	public float obtenerDescuentoPorAdelanto (){
		float ret=0;
		ret = SubsistemaFinanciero.descuentoPorAdelanto();
		return ret;
	}
	
	public void pagarCuota (int idCuota) { //COMPLETAR TODO ACA, EN EL COMENTARIO DE ABAJO ESTA ESCRITA LA CONSULTA
		
	/*PreparedStatement st=  con.PrepareStatement ("UPDATE COUTA SET pagada=true WHERE id_cuota = ?")
	 st.setInt(1, idCuota) */
		;
	}

}
