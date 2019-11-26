package tp.disenio.gestores;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import tp.disenio.DAO.DAOAnio;
import tp.disenio.DAO.DAOLocalidad;
import tp.disenio.DAO.DAOMarca;
import tp.disenio.DAO.DAOModelo;
import tp.disenio.DAO.DAOProvincia;
import tp.disenio.DAO.DAOSuperIntendencia;
import tp.disenio.DTO.DomicilioRiesgoDTO;
import tp.disenio.DTO.VehiculoDTO;
import tp.disenio.clases.Anio;
import tp.disenio.clases.Localidad;
import tp.disenio.clases.Marca;
import tp.disenio.clases.Modelo;
import tp.disenio.clases.Provincia;

public class GestorParametros {

	private static GestorParametros GParametros ; // Patron Singleton -- Unica instancia tipo gestor creada.

	private GestorParametros(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
	}

	public static GestorParametros getInstance() { // Patron Singleton -- Devuelve la instancia, si no existe la crea
		if ( GParametros == null) {
			GParametros = new GestorParametros();
		}
		return GParametros;
	}


	public static Object[] obtenerProvincias() {
		Object[] ret  = DAOProvincia.listaProvincia();
		return ret;

	}

	public static Object[] obtenerLocalidad(Provincia prov) {
		Object[] ret  = DAOLocalidad.listaLocalidad(prov);
		return ret;
	}

	public static Object[] obtenerMarcas() {
		Object[] ret  = DAOMarca.listaMarcas();
		return ret;
	}

	public static Object[] obtenerModelos(Marca marca) {
		Object[] ret  = DAOModelo.listaModelos(marca);
		return ret;
	}

	public static Object[] obtenerAnios(Modelo modelo) {
		Object[] ret  = DAOAnio.listaAnios(modelo);
		return ret;
	}

	public static Float obtenerSumaAsegurada(Modelo modelo, String anio) {
		Float ret  = DAOSuperIntendencia.obtenerSumaAsegurada(modelo, anio);
		return ret;
	}

	public static int castSumaAsegurada (String suma) {
		int sumaAsegurada = 0;
		int tamsuma = suma.length();
		String sumasinpuntos = "";
		for(int i=0; i<tamsuma; i++) {
			if(suma.charAt(i) != '.') {
				sumasinpuntos += suma.charAt(i);
			}
		}
		sumaAsegurada = Integer.parseInt(sumasinpuntos);
		return sumaAsegurada;
	}


	public static String obtenerFechaFin(String inicio) {
		String fin="";

		DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal1 = Calendar.getInstance();
		try {
			cal1.setTime(dateFormat1.parse(inicio));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cal1.add(Calendar.MONTH, 6);
		fin = dateFormat1.format(cal1.getTime());
		return fin;

	}

	public static Localidad setearLocalidad(DomicilioRiesgoDTO dom) {
		Provincia aux_Prov = new Provincia();
		aux_Prov.setNombre(dom.getLocalidad().getProvincia().getNombre());
		aux_Prov.setId_provincia(dom.getLocalidad().getProvincia().getId_provincia());
		aux_Prov.setPais();

		Localidad aux_Loc = new Localidad();
		aux_Loc.setId_localidad(dom.getLocalidad().getId_localidad());
		aux_Loc.setCodigoPostal(dom.getLocalidad().getCodigoPostal());
		aux_Loc.setNombre(dom.getLocalidad().getNombre());
		aux_Loc.setPorcentaje(dom.getLocalidad().getPorcentaje());
		aux_Loc.setProvincia(aux_Prov);

		return aux_Loc;
	}

	public static Modelo setearModelo(VehiculoDTO v) {

		Marca aux_marca = new Marca();
		aux_marca.setIdMarca(v.getModelo().getMarca().getIdmarca());
		aux_marca.setNombre(v.getModelo().getMarca().getNombre());

		Modelo aux_mod = new Modelo();
		aux_mod.setIdModelo(v.getModelo().getIdmodelo());
		aux_mod.setPorcentaje(v.getModelo().getPorcentaje());
		aux_mod.setMarca(aux_marca);
		aux_mod.setNombre(v.getModelo().getNombre());

		Anio aux_anio = new Anio();
		aux_anio = DAOAnio.obtenerAnioModelo(aux_mod);

		aux_mod.setAnio(aux_anio);

		return aux_mod;
	}

	public static Localidad obtenerLocalidad(int idLoc, Connection con) {
		Localidad retorno = new Localidad();
		retorno = DAOLocalidad.obtenerLocalidad(idLoc, con);
		return retorno;
	}

	public static Provincia obtenerProvincia(int idProv, Connection con) {
		Provincia retorno = new Provincia();
		retorno = DAOProvincia.obtenerProvincia(idProv, con);
		return retorno;
	}


	public static Modelo obtenerModelo (int idModelo, Connection con) {
		Modelo retorno = new Modelo();
		retorno = DAOModelo.obtenerModelo(idModelo, con);
		return retorno;
	}

	public Marca recuperarMarca(int idMarca, Connection con) {
		Marca retorno = new Marca();
		retorno = DAOMarca.obtenerMarca(idMarca, con);
		return retorno;
	}

	public Anio recuperarAnio(Modelo m) {
		Anio retorno = new Anio();
		retorno = DAOAnio.obtenerAnioModelo(m);
		return retorno;
	}




}
