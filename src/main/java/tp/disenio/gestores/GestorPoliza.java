package tp.disenio.gestores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import tp.disenio.DAO.DAOCaracteristicas;
import tp.disenio.DAO.DAOCuota;
import tp.disenio.DAO.DAODescuentos;
import tp.disenio.DAO.DAODomicilioRiesgo;
import tp.disenio.DAO.DAOHijo;
import tp.disenio.DAO.DAOMedidasSeguridad;
import tp.disenio.DAO.DAOPoliza;
import tp.disenio.DAO.DAOPremio;
import tp.disenio.DAO.DAOSiniestros;
import tp.disenio.DAO.DAOTipoCobertura;
import tp.disenio.DAO.DAOVehiculo;
import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.CuotaDTO;
import tp.disenio.DTO.DescuentosDTO;
import tp.disenio.DTO.DomicilioRiesgoDTO;
import tp.disenio.DTO.HijoDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.DTO.PremioDTO;
import tp.disenio.DTO.VehiculoDTO;
import tp.disenio.clases.Alarma;
import tp.disenio.clases.Caracteristicas;
import tp.disenio.clases.Cliente;
import tp.disenio.clases.Cobertura;
import tp.disenio.clases.Cuota;
import tp.disenio.clases.Descuentos;
import tp.disenio.clases.DispRastreo;
import tp.disenio.clases.DomicilioRiesgo;
import tp.disenio.clases.Garage;
import tp.disenio.clases.Hijo;
import tp.disenio.clases.Localidad;
import tp.disenio.clases.MedidasSeguridad;
import tp.disenio.clases.Mensual;
import tp.disenio.clases.Modelo;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.PolizaModificada;
import tp.disenio.clases.Premio;
import tp.disenio.clases.Semestral;
import tp.disenio.clases.Siniestros;
import tp.disenio.clases.Tuercas;
import tp.disenio.clases.Vehiculo;

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
			return 0;
		} else if(cant > 1 && cant <= 3) {
			return 0.10;
		} else if(cant > 3) {
			return 0.25;
		}
		return 0;
	}


	public static long generarNroPoliza() {
		long nroPoliza = 0;
		GestorPoliza gp = GestorPoliza.getInstance();
		String aux_nroNuevo = "";
		String n_sucursal = "1234";
		long serial = gp.recupearUltimoNIDPoliza();
		serial = serial /100;
		serial = serial %10000000;
		serial ++;
		String n_serial = Long.toString(serial);
		String n_poliza = "01"; //porque es la primera poliza que registra

		aux_nroNuevo = n_sucursal + n_serial + n_poliza;
		nroPoliza = Long.parseLong(aux_nroNuevo);

		return nroPoliza;
	}

	public static boolean cargarPolizaMensual(ClienteDTO c,PolizaDTO p,VehiculoDTO v,ArrayList<HijoDTO>listahijos,DomicilioRiesgoDTO dom,DescuentosDTO descuentos,PremioDTO premio, ArrayList<CuotaDTO> cuotas) {

		boolean flag = false;
		GestorPoliza gp = GestorPoliza.getInstance();
		Poliza nueva_poliza = new Poliza();
		nueva_poliza = gp.generarInstanciaPoliza(c, p, v, listahijos, dom, descuentos, premio);

		Mensual aux_mens = new Mensual();

		ArrayList<Cuota> listacuotas = new ArrayList<>();

		int id_cuota = DAOCuota.recupearUltimoNID();
		id_cuota +=1;

		for(CuotaDTO cu : cuotas) {
			Cuota cuota = new Cuota();
			cuota.setId_cuota(id_cuota);
			cuota.setFecha_vencimiento(cu.getVencimiento());
			cuota.setMonto(cu.getMonto());
			cuota.setPagada(cu.isPagado());
			listacuotas.add(cuota);
			id_cuota++;
		}

		aux_mens.setCuotas(listacuotas);
		aux_mens.setNombre("MENSUAL");
		nueva_poliza.setForma_pago(aux_mens);

		if (DAOPoliza.cargarPoliza(nueva_poliza)) {
			/*cargar tablas:
			 * CUOTA - listo
			 * CATACTERISTICAS - listo
			 * DESCUENTOS - LISTO
			 * POLIZA TIENE MDS - listo
			 * AGREGAR HIJO DECLARADO - listo
			 */

			//Si se pudo cargar todo eso se cargó bien la poliza
			Boolean poliza_tiene_mds = gp.cargarPolizaTieneMDS(nueva_poliza);

			Boolean hijo_declarado = gp.cargarHijos(nueva_poliza);

			Boolean bool_cuota = gp.cargarCuota(nueva_poliza);

			Boolean bool_caracteristicas = gp.cargarCaracteristicas(nueva_poliza);

			Boolean bool_desc = gp.cargarDescuentos(nueva_poliza);

			if (poliza_tiene_mds && hijo_declarado && bool_cuota && bool_caracteristicas && bool_desc) flag = true;
		}
		return flag;
	}

	public static boolean cargarPolizaSemestral (ClienteDTO c,PolizaDTO p,VehiculoDTO v,ArrayList<HijoDTO>listahijos,DomicilioRiesgoDTO dom,DescuentosDTO descuentos,PremioDTO premio, CuotaDTO cuota ) {

		boolean flag = false;
		GestorPoliza gp = GestorPoliza.getInstance();
		Poliza nueva_poliza = new Poliza();
		nueva_poliza = gp.generarInstanciaPoliza(c, p, v, listahijos, dom, descuentos, premio);

		int id_cuota = DAOCuota.recupearUltimoNID();
		id_cuota +=1;

		Cuota aux_cuota = new Cuota();
		aux_cuota.setFecha_vencimiento(cuota.getVencimiento());
		aux_cuota.setId_cuota(id_cuota);
		aux_cuota.setMonto(cuota.getMonto());
		aux_cuota.setPagada(cuota.isPagado());

		Semestral aux_sem = new Semestral();
		aux_sem.setFecha_Vencimiento(cuota.getVencimiento());
		aux_sem.setMontoTotal(cuota.getMonto());
		aux_sem.setNombre("SEMESTRAL");
		aux_sem.setCuota1(aux_cuota);
		nueva_poliza.setForma_pago(aux_sem);


		if (DAOPoliza.cargarPoliza(nueva_poliza)) {
			/*cargar tablas:
			 * CUOTA - listo
			 * CATACTERISTICAS - listo
			 * DESCUENTOS - LISTO
			 * POLIZA TIENE MDS - listo
			 * AGREGAR HIJO DECLARADO - listo
			 */

			//Si se pudo cargar todo eso se cargó bien la poliza
			Boolean poliza_tiene_mds = gp.cargarPolizaTieneMDS(nueva_poliza);

			Boolean hijo_declarado = gp.cargarHijos(nueva_poliza);

			Boolean bool_cuota = gp.cargarCuota(nueva_poliza);

			Boolean bool_caracteristicas = gp.cargarCaracteristicas(nueva_poliza);

			Boolean bool_desc = gp.cargarDescuentos(nueva_poliza);

			if (poliza_tiene_mds && hijo_declarado && bool_cuota && bool_caracteristicas && bool_desc) flag = true;
		}
		return flag;
	}

	private Boolean cargarDescuentos(Poliza nueva_poliza) {
		boolean retorno = DAODescuentos.cargarDescuentos(nueva_poliza);
		return retorno;

	}

	private Boolean cargarCaracteristicas(Poliza nueva_poliza) {
		boolean retorno = DAOCaracteristicas.cargarCaracteristicas(nueva_poliza);
		return retorno;


	}

	private Boolean cargarCuota(Poliza nueva_poliza) {
		boolean retorno = DAOCuota.cargarCuota(nueva_poliza);
		return retorno;

	}

	private Boolean cargarHijos(Poliza nueva_poliza) {
		boolean retorno = DAOHijo.cargarHijos(nueva_poliza);
		return retorno;
	}

	public static double calcularMontoTotalAPagar (PremioDTO premiodto, ClienteDTO c ) {
		GestorCliente gc = GestorCliente.getInstance();
		int cant = gc.cantidadPoliza(c);
		GestorPoliza gp = GestorPoliza.getInstance();
		double montototalapagar = premiodto.getMontoTotal()*(1-gp.descuentos(cant));
		return montototalapagar;

	}

	public DescuentosDTO setDescuentos (ClienteDTO c) {
		GestorCliente gc = GestorCliente.getInstance();
		int cant = gc.cantidadPoliza(c);
		GestorPoliza gp = GestorPoliza.getInstance();
		DescuentosDTO descuentos = new DescuentosDTO();
		descuentos.setDescPorUnidadAdicional(gp.descuentos(cant));
		descuentos.setDescPorPagoAdelantado(0.05);
		descuentos.setDescPorPagoSemestral(0.10);

		return descuentos;
	}

	public static ArrayList<CuotaDTO> obtenerListaCuotas (PremioDTO premiodto, ClienteDTO c, PolizaDTO p){
		ArrayList<CuotaDTO> listacuotas = new ArrayList<>();

		DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal1 = Calendar.getInstance();
		try {
			cal1.setTime(dateFormat1.parse(p.getInicio_vigencia()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cal1.add(Calendar.MONTH, 1);
		cal1.add(Calendar.DAY_OF_MONTH,1);

		for(int i=0; i<6; i++) {
			CuotaDTO cuota = new CuotaDTO();
			GestorPoliza gp = GestorPoliza.getInstance();
			cuota.setMonto(gp.calcularMontoTotalAPagar(premiodto, c)/6);
			cuota.setPagado(false);
			String fechavencimiento = dateFormat1.format(cal1.getTime());
			cuota.setVencimiento(fechavencimiento);
			cal1.add(Calendar.MONTH, 1);
			listacuotas.add(cuota);
		}

		return listacuotas;
	}

	public static Poliza generarInstanciaPoliza (ClienteDTO c,PolizaDTO p,VehiculoDTO v,ArrayList<HijoDTO>listahijos,DomicilioRiesgoDTO dom,DescuentosDTO descuentos,PremioDTO premio) {
		GestorParametros gpm= GestorParametros.getInstance();
		GestorPoliza gp = GestorPoliza.getInstance();
		GestorCliente gc =GestorCliente.getInstance();

		Cliente aux_cliente = new Cliente();
		aux_cliente = gc.generarInstanciaCliente(c);

		Localidad aux_Loc = new Localidad();
		aux_Loc = gpm.setearLocalidad(dom);

		DomicilioRiesgo aux_domR = new DomicilioRiesgo();
		int idDom = gp.recupearUltimoNIDDomRiesgo();
		idDom +=1;
		aux_domR.setId_domicilioR(idDom);
		aux_domR.setLocalidad(aux_Loc);
		aux_domR.setPorcentajeDomicilio(dom.getPorcentajeDomicilio());

		Modelo aux_mod = new Modelo();
		aux_mod = gpm.setearModelo(v);


		Vehiculo aux_ve = new Vehiculo();
		int idVehiculo = gp.recupearUltimoNIDVehiculo();
		idVehiculo +=1;
		aux_ve.setId_vehiculo(idVehiculo);
		aux_ve.setPatente(v.getPatente());
		aux_ve.setMotor(v.getMotor());
		aux_ve.setChasis(v.getChasis());
		aux_ve.setModelo(aux_mod);
		aux_ve.setPorcentaje(v.getPorcentaje());
		aux_ve.setAnio(v.getAnio());

		MedidasSeguridad aux_MS = new MedidasSeguridad();

		Alarma aux_alarma = new Alarma();
		aux_alarma.setPosee(p.getAlarma());

		Garage aux_garage = new Garage();
		aux_garage.setPosee(p.getGarage());

		DispRastreo aux_dispR = new DispRastreo();
		aux_dispR.setPosee(p.getDispRastreo());

		Tuercas aux_tuercas = new Tuercas();
		aux_tuercas.setPosee(p.getTuercas());

		aux_MS.setAlarma(aux_alarma);
		aux_MS.setGarage(aux_garage);
		aux_MS.setRastreo(aux_dispR);
		aux_MS.setTuercas(aux_tuercas);


		Siniestros aux_siniestro = new Siniestros();
		aux_siniestro = gp.obtenerSiniestro(p.getSiniestro());

		ArrayList<Hijo> hijos = new ArrayList();

		if (!listahijos.isEmpty()) { //si no está vacia
			for (HijoDTO h: listahijos) {

				Hijo aux_h = new Hijo();
				aux_h.setEstadoCivil(h.getEstadoCivil());
				aux_h.setFechaNac(h.getFechaNac());
				aux_h.setSexo(h.getFechaNac());

				hijos.add(aux_h);
			}
		}

		Cobertura aux_cob = new Cobertura();
		aux_cob = gp.obtenerCobertura(p.getTipoCobertura());

		Premio aux_premio = new Premio();
		int id_premio = gp.recupearUltimoNIDPremio();
		id_premio +=1;
		aux_premio.setIdPremio(id_premio);
		aux_premio.setPrima(premio.getPrima());
		aux_premio.setDerechoEmision(premio.getDerechoEmision());
		aux_premio.setMontoTotal(premio.getMontoTotal());

		Descuentos aux_desc = new Descuentos();

		aux_desc.setDescPorPagoAdelantado(descuentos.getDescPorPagoAdelantado());
		aux_desc.setDescPorPagoSemestral(descuentos.getDescPorPagoSemestral());
		aux_desc.setDescPorUnidadAdicional(descuentos.getDescPorUnidadAdicional());


		Caracteristicas aux_car = new Caracteristicas();

		if (!hijos.isEmpty()) {
			int tam_hijos = hijos.size();
			aux_car.setPorcentajeHijo(gp.obtenerPorcentajeHijo()*tam_hijos); //lo multiplico por la cant de hijos
		} else aux_car.setPorcentajeHijo(0);
		aux_car.setPorcentajeKm(gp.obtenerPorcentajeKM());


		Poliza nueva_poliza = new Poliza();
		long nroPoliza = gp.generarNroPoliza();
		nueva_poliza.setNroPoliza(nroPoliza); //nroPoliza
		nueva_poliza.setCliente(aux_cliente); //CLIENTE
		nueva_poliza.setDomicilio_riesgo(aux_domR);
		nueva_poliza.setVehiculo(aux_ve);
		nueva_poliza.setKmPorAnio(p.getKmPorAnio());
		nueva_poliza.setSeguridad(aux_MS);
		nueva_poliza.setSumaasegurada(p.getSumaasegurada());
		nueva_poliza.setHijos_declarados(hijos);
		nueva_poliza.setTipo_cobertura(aux_cob);
		nueva_poliza.setInicio_vigencia(p.getInicio_vigencia());
		nueva_poliza.setFin_vigencia(p.getFin_vigencia());
		nueva_poliza.setEstado_poliza("GENERADA");
		nueva_poliza.setPremio(aux_premio);
		nueva_poliza.setDescuento(aux_desc);
		nueva_poliza.setPoliza_modificada(new PolizaModificada());
		nueva_poliza.setCaracteristicas(aux_car);

		return nueva_poliza;
	}

	public static Boolean cargarPolizaTieneMDS (Poliza p) {
		Boolean flag = false;

		flag = DAOMedidasSeguridad.cargarPolizaTieneMDS(p);

		return flag;
	}

	public static Poliza  buscarPoliza(String nroP) {
		Poliza retorno = new Poliza();
		retorno = DAOPoliza.buscarPoliza(nroP);
		return retorno;
	}

	public static ArrayList<Cuota> recuperarListaCuotas(long nroPoliza){
		ArrayList<Cuota> retorno = new ArrayList<>();
		retorno = DAOCuota.recuperarListaCuotas(nroPoliza);
		return retorno;
	}

	public static Cuota recupearCuota(long nroPoliza) {
		Cuota retorno = new Cuota();
		retorno = DAOCuota.recupearCuota(nroPoliza);
		return retorno;
	}

	public static Premio recuperarPremio (int idPremio) {
		Premio retorno = new Premio();
		retorno = DAOPremio.recuperarPremio(idPremio);
		return retorno;
	}

	public static Vehiculo recuperarVehiculo(int idVeh) {
		Vehiculo retorno = new Vehiculo();
		retorno = DAOVehiculo.recuperarVehiculo(idVeh);
		return retorno;
	}

	public static int obtenerIDAlarma() {
		int retorno = 0;
		retorno = DAOMedidasSeguridad.obtenerIDAlarma();
		return retorno;
	}

	public static double obtenerPorcentajeAlarma() {
		double retorno = 0;
		retorno = DAOMedidasSeguridad. obtenerPorcentajeAlarma();
		return retorno;
	}

	public static int obtenerIDTuerca() {
		int retorno = 0;
		retorno = DAOMedidasSeguridad.obtenerIDTuerca();
		return retorno;
	}

	public static double obtenerPorcentajeTuerca () {
		double retorno = 0;
		retorno = DAOMedidasSeguridad.obtenerPorcentajeTuerca();
		return retorno;
	}

	public static int obtenerIDDisp() {
		int retorno = 0;
		retorno = DAOMedidasSeguridad.obtenerIDDisp();
		return retorno;
	}

	public static double obtenerPorcentajeDisp () {
		double retorno = 0;
		retorno = DAOMedidasSeguridad.obtenerPorcentajeDisp();
		return retorno;
	}

	public static int obtenerIDGarage() {
		int retorno = 0;
		retorno = DAOMedidasSeguridad.obtenerIDGarage();
		return retorno;
	}
	public static double obtenerPorcentajeGarage() {
		double retorno = 0;
		retorno = DAOMedidasSeguridad.obtenerPorcentajeGarage();
		return retorno;
	}

	public static void guardarDomRiesgo (DomicilioRiesgo d) {
		DAODomicilioRiesgo.guardarDomRiesgo(d);
	}
	public static void  guardarPremio (Premio p) {
		DAOPremio.guardarPremio(p);
	}

	public static void  guardarVehiculo (Vehiculo v) {
		DAOVehiculo.guardarVehiculo(v);
	}
	public static void  guardarSiniestro(Siniestros s) {
		DAOSiniestros.guardarSiniestro(s);
	}

	public static void guardarTipo (Cobertura c) {
		DAOTipoCobertura.guardarTipo(c);
	}
	public static Siniestros recuperarSiniestro(int idSiniestro) {
		Siniestros retorno = new Siniestros();
		retorno = DAOSiniestros.recuperarSiniestro(idSiniestro);
		return retorno;
	}
	public static Cobertura recuperarCobertura(int idCobertura) {
		Cobertura retorno = new Cobertura();
		retorno = DAOTipoCobertura.recuperarCobertura(idCobertura);
		return retorno;
	}

	public static DomicilioRiesgo recuperarDomicilioRiesgo(int idDom) {
		DomicilioRiesgo retorno = new DomicilioRiesgo();
		retorno = DAODomicilioRiesgo.recuperarDomicilioRiesgo(idDom);
		return retorno;
	}


	public static MedidasSeguridad recuperarMedidasSeguridad(long nroPoliza) {
		MedidasSeguridad retorno = new MedidasSeguridad();
		retorno = DAOMedidasSeguridad.recuperarMedidasSeguridad(nroPoliza);
		return retorno;
	}

	public static double obtenerPorcentajeHijo() {
		double retorno = DAOCaracteristicas.obtenerPorcentajeHijo();
		return retorno;
	}
	public static double obtenerPorcentajeKM() {
		double retorno = DAOCaracteristicas.obtenerPorcentajeKM();
		return retorno;
	}

	public static int recupearUltimoNIDPremio() {
		int retorno =DAOPremio.recupearUltimoNID();
		return retorno;
	}
	public static Cobertura obtenerCobertura(String nombre) {
		Cobertura retorno = new Cobertura();
		retorno = DAOTipoCobertura.obtenerCobertura(nombre);
		return retorno;
	}
	public static Siniestros obtenerSiniestro(String nombre) {
		Siniestros retorno = new Siniestros();
		retorno = DAOSiniestros.obtenerSiniestro(nombre);
		return retorno;
	}
	public static int recupearUltimoNIDVehiculo() {
		int retorno =DAOVehiculo.recupearUltimoNID();
		return retorno;
	}
	public static int recupearUltimoNIDDomRiesgo() {
		int retorno = DAODomicilioRiesgo.recupearUltimoNID();
		return retorno;

	}

	public Descuentos recuperarDescuentos(long nroPoliza) {
		Descuentos retorno = new Descuentos();
		retorno = DAODescuentos.recuperarDescuentos(nroPoliza);
		return retorno;
	}

	public static ArrayList<Hijo> recuperarHijos(long nroPoliza) {
		ArrayList<Hijo> retorno = new ArrayList<>();
		retorno = DAOHijo.recuperarHijos(nroPoliza);
		return retorno;
	}

	public static Caracteristicas recuperarCaracteristicas (long nroPoliza) {
		Caracteristicas retorno = new Caracteristicas();
		retorno = DAOCaracteristicas.recuperarCaracteristicas(nroPoliza);
		return retorno;
	}
	public static long recupearUltimoNIDPoliza() {
		long retorno = DAOPoliza.recupearUltimoNID();
		return retorno;
	}
}
