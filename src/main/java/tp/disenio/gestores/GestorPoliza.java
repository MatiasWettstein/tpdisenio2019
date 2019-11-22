package tp.disenio.gestores;

import java.util.ArrayList;

import tp.disenio.DAO.DAOHijo;
import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.DomicilioRiesgoDTO;
import tp.disenio.DTO.HijoDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.DTO.PremioDTO;
import tp.disenio.DTO.VehiculoDTO;
import tp.disenio.clases.Descuentos;

public class GestorPoliza {
	//---------- Patron Singleton
	private static GestorPoliza GPoliza ; // Patron Singleton -- Unica instancia tipo gestor creada.

	private GestorPoliza(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
	}

	public static GestorPoliza getInstance() { // Patron Singleton -- Devuelve la instancia, si no existe la crea
		if ( GPoliza == null) {
			GPoliza = new GestorPoliza();
		}
		return GPoliza;
	}


	public float calcularPrima(float precio) {
		return precio/100 * 25;

	}
	public float calcularDerecho(float precio) {
		return precio/1000;
	}
	public float calcularPremio(float prima, float emision) {
		return prima+emision;
	}

	public double descuentos(int cant) {

		if(cant <=1) {
			return 1;
		} else if(cant > 1 && cant <= 3) {
			return 0.90;
		} else if(cant > 3) {
			return 0.75;
		}
		return 1;
	}

	public void cargarPoliza(ClienteDTO c,PolizaDTO p,VehiculoDTO v,ArrayList<HijoDTO>listahijos,DomicilioRiesgoDTO dom,Descuentos descuentos,PremioDTO premio) {
		// TODO Auto-generated method stub
		/*
 int nro_poliza 1
 int km_por_anio 2
 double suma_asegurada 3
 String inicio_vigencia 4
 String fin__vigencia 5
 String Forma_Pago 6
 String estado_poliza 7
 FK id premio 8
 FK id vehiculo 9
 FK id siniestros 10
 FK id tipo_cobertura 11
 FK id domicilio_riesgo 12
 FK id cliente 13
		 */

		ArrayList<Integer> idhijos = new ArrayList<>();

		idhijos = DAOHijo.cargarhijos();








	}



}
