package tp.disenio.gestores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import tp.disenio.DAO.*;
import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.CuotaDTO;
import tp.disenio.DTO.DescuentosDTO;
import tp.disenio.DTO.DomicilioRiesgoDTO;
import tp.disenio.DTO.HijoDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.DTO.PremioDTO;
import tp.disenio.DTO.VehiculoDTO;
import tp.disenio.clases.*;

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


	public static int generarNroPoliza() {
		int nroPoliza = 0;
		String aux_nroNuevo = "";
		String n_sucursal = "1234";
			//CAMBIAR EL NUMERO NUEVO QUE TIENE QUE SER SERIAL NO ALEATORIO
			int aleatorio = (int) Math.floor(Math.random()*(9999999-1000000+1)+1000000); //genera el numero de 7 digitos para el vehiculo
		String n_serial = Integer.toString(aleatorio);
		String n_poliza = "01"; //porque es la primera poliza que registra
		
		aux_nroNuevo = n_sucursal + n_serial + n_poliza;
			nroPoliza = Integer.parseInt(aux_nroNuevo);
		
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


		flag = DAOPoliza.cargarPoliza(nueva_poliza);
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
				Boolean poliza_tiene_mds = DAOPoliza.cargarPolizaTieneMDS(nueva_poliza);

				Boolean hijo_declarado = DAOHijo.cargarHijos(nueva_poliza);
				
				Boolean bool_cuota = DAOCuota.cargarCuota(nueva_poliza);
				
				Boolean bool_caracteristicas = DAOCaracteristicas.cargarCaracteristicas(nueva_poliza);
				
				Boolean bool_desc = DAODescuentos.cargarDescuentos(nueva_poliza);
				
				if (poliza_tiene_mds && hijo_declarado && bool_cuota && bool_caracteristicas && bool_desc) flag = true; 
			}
		return flag;
	}
	
	public static double calcularMontoTotalAPagar (PremioDTO premiodto, ClienteDTO c ) {
		GestorCliente gc = GestorCliente.getInstance();
		int cant = gc.cantidadPoliza(c);
		GestorPoliza gp = GestorPoliza.getInstance();
		double montototalapagar = premiodto.getMontoTotal()* (gp.descuentos(cant));
		return montototalapagar;
		
	}
	
	public DescuentosDTO setDescuentos (ClienteDTO c) {
		GestorCliente gc = GestorCliente.getInstance();
		int cant = gc.cantidadPoliza(c);
		GestorPoliza gp = GestorPoliza.getInstance();
		DescuentosDTO descuentos = new DescuentosDTO();
		descuentos.setDescPorUnidadAdicional(100-gp.descuentos(cant)*100);
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
		
		Cliente aux_cliente = new Cliente();
		aux_cliente = GestorCliente.generarInstanciaCliente(c);

		Localidad aux_Loc = new Localidad(); 
		aux_Loc = gpm.setearLocalidad(dom);
		
		DomicilioRiesgo aux_domR = new DomicilioRiesgo();
		int idDom = DAODomicilioRiesgo.recupearUltimoNID();
		idDom +=1;
		aux_domR.setId_domicilioR(idDom);
		aux_domR.setLocalidad(aux_Loc);
		aux_domR.setPorcentajeDomicilio(dom.getPorcentajeDomicilio());

		Modelo aux_mod = new Modelo();
		aux_mod = gpm.setearModelo(v);
		
		
		Vehiculo aux_ve = new Vehiculo();
		int idVehiculo = DAOVehiculo.recupearUltimoNID();
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
		aux_siniestro = DAOSiniestros.obtenerSiniestro(p.getSiniestro());
		
		ArrayList<Hijo> hijos = new ArrayList();
		
		if (!(listahijos.isEmpty())) { //si no está vacia 
		for (HijoDTO h: listahijos) {

			Hijo aux_h = new Hijo();
			aux_h.setEstadoCivil(h.getEstadoCivil());
			aux_h.setFechaNac(h.getFechaNac());
			aux_h.setSexo(h.getFechaNac());

			hijos.add(aux_h);
			}
		}
		
		Cobertura aux_cob = new Cobertura();
		aux_cob = DAOTipoCobertura.obtenerCobertura(p.getTipoCobertura());

		Premio aux_premio = new Premio();
		int id_premio = DAOPremio.recupearUltimoNID();
		id_premio +=1; 
		aux_premio.setIdPremio(id_premio);
		aux_premio.setPrima(premio.getPrima());
		aux_premio.setDerechoEmision(premio.getDerechoEmision());
		aux_premio.setMontoTotal(premio.getMontoTotal());
		
		Descuentos aux_desc = new Descuentos();
		int id_desc = DAODescuentos.recupearUltimoNID();
		id_desc +=1; 
		aux_desc.setDescPorPagoAdelantado(descuentos.getDescPorPagoAdelantado());
		aux_desc.setDescPorPagoSemestral(descuentos.getDescPorPagoSemestral());
		aux_desc.setDescPorUnidadAdicional(descuentos.getDescPorUnidadAdicional());
		aux_desc.setIdDescuentos(id_desc);

		Caracteristicas aux_car = new Caracteristicas();
		int id_carac = DAOCaracteristicas.recupearUltimoNID();
		id_carac +=1;
		aux_car.setId_caracteristica(id_carac);
		if (!(hijos.isEmpty())) {
			int tam_hijos = hijos.size();
			aux_car.setPorcentajeHijo((DAOCaracteristicas.obtenerPorcentajeHijo())*tam_hijos); //lo multiplico por la cant de hijos
		} else aux_car.setPorcentajeHijo(0);
		aux_car.setPorcentajeKm(DAOCaracteristicas.obtenerPorcentajeKM());
		
	
		Poliza nueva_poliza = new Poliza();
		int nroPoliza = gp.generarNroPoliza();
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
		
		flag = DAOPoliza.cargarPolizaTieneMDS(p);
		
		return flag; 
	}
	
	
}
