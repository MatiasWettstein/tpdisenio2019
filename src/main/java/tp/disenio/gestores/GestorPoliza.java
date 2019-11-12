package tp.disenio.gestores;

import java.util.ArrayList;

import tp.disenio.clases.Localidad;
import tp.disenio.clases.Modelo;
import tp.disenio.clases.TipoCobertura;
import tp.disenio.enumerators.TipoCoberturaEnum;
import tp.disenio.gestores.GestorPoliza;

public class GestorPoliza {
	//---------- Patron Singleton
		private static GestorPoliza GPoliza ; // Patron Singleton -- Unica instancia tipo gestor creada.

		private GestorPoliza(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
		}

		public static GestorPoliza getInstance() { // Patron Singleton -- Devuelve la instancia, si no existe la crea
			if ( GPoliza == null) {
				GPoliza = new GestorPoliza();

				/*ESTAS INICIALIZACIONES SON PARA TENER LOS PORCENTAJES HASTA QUE SE TENGA LA BD
				modelos = new ArrayList<Modelo>();
				localidades = new ArrayList<Localidad>();
				tiposCobertura = new ArrayList <TipoCobertura>();

				TipoCobertura tipo= new TipoCobertura();
				tipo.setNombre(TipoCoberturaEnum.Responsabilidad_Civil.toString());
				tipo.setPorcentaje(50);
				tiposCobertura.add(tipo);
				TipoCobertura tipo1= new TipoCobertura();
				tipo1.setNombre(TipoCoberturaEnum.RC_y_Robo_Total.toString());
				tipo1.setPorcentaje(55);
				tiposCobertura.add(tipo1);
				TipoCobertura tipo2= new TipoCobertura();
				tipo2.setNombre(TipoCoberturaEnum.Terceros_Completo.toString());
				tipo2.setPorcentaje(60);
				tiposCobertura.add(tipo2);
				TipoCobertura tipo3= new TipoCobertura();
				tipo3.setNombre(TipoCoberturaEnum.Todo_Riesgo_Franquicia.toString());
				tipo3.setPorcentaje(65);
				tiposCobertura.add(tipo3);
				TipoCobertura tipo4= new TipoCobertura();
				tipo4.setNombre(TipoCoberturaEnum.Todo_Total.toString());
				tipo4.setPorcentaje(70);
				tiposCobertura.add(tipo4);
				//FALTAN TODAS LAS DE LOCALIDAD Y MODELO
				//FIN INICIALIZACIONES
				 * 
				 */
			}
			return GPoliza;
		}
}
