package tp.disenio.gestores;

import java.util.ArrayList;

import tp.disenio.DAO.DAOPoliza;
import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.DomicilioRiesgoDTO;
import tp.disenio.DTO.HijoDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.DTO.PremioDTO;
import tp.disenio.DTO.VehiculoDTO;
import tp.disenio.clases.Alarma;
import tp.disenio.clases.Cliente;
import tp.disenio.clases.Descuentos;
import tp.disenio.clases.DispRastreo;
import tp.disenio.clases.DomicilioRiesgo;
import tp.disenio.clases.Garage;
import tp.disenio.clases.Localidad;
import tp.disenio.clases.Marca;
import tp.disenio.clases.MedidasSeguridad;
import tp.disenio.clases.Modelo;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.Provincia;
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
			return 1;
		} else if(cant > 1 && cant <= 3) {
			return 0.90;
		} else if(cant > 3) {
			return 0.75;
		}
		return 1;
	}


	public static int generarNroPoliza(ClienteDTO c) {
		int nroPoliza = 0;
		String aux_nroNuevo = "";
		int ultimoGuardado = DAOPoliza.recuperarUltimoNroPolizaCliente(c);

		if (ultimoGuardado == 0) { // EL CLIENTE NO TIENE POLIZAS REGISTRADAS
			aux_nroNuevo = "1234";
			int aleatorio = (int) Math.floor(Math.random()*(9999999-1000000+1)+1000000); //genera el numero de 7 digitos para el vehiculo
			aux_nroNuevo += Integer.toString(aleatorio);
			aux_nroNuevo += "01"; //porque es la primera poliza que registra
			nroPoliza = Integer.parseInt(aux_nroNuevo);
		}
		else {
			String aux_nroPoliza = "";
			String aux_primerosN = "";
			String aux_ultimosN = "";
			int aux_ultimos = 0;

			aux_nroPoliza = Integer.toString(ultimoGuardado);

			aux_primerosN = aux_nroPoliza.substring(0, 11);
			aux_ultimosN = aux_nroPoliza.substring(11, 13);

			aux_ultimos = Integer.parseInt(aux_ultimosN); //acá tengo en int el ultimo numero
			aux_ultimos +=1;

			aux_ultimosN = Integer.toString(aux_ultimos);

			aux_nroPoliza = aux_primerosN + aux_ultimosN ;

			nroPoliza = Integer.parseInt(aux_nroPoliza);

		}
		return nroPoliza;
	}

	public void cargarPoliza(ClienteDTO c,PolizaDTO p,VehiculoDTO v,ArrayList<HijoDTO>listahijos,DomicilioRiesgoDTO dom,Descuentos descuentos,PremioDTO premio) {

		boolean flag = false;

		Cliente aux_cliente = new Cliente();
		aux_cliente = GestorCliente.generarInstanciaCliente(c);

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

		DomicilioRiesgo aux_domR = new DomicilioRiesgo();
		aux_domR.setId_domicilioR(dom.getId_domicilioR());
		aux_domR.setLocalidad(aux_Loc);
		aux_domR.setPorcentajeDomicilio(dom.getPorcentajeDomicilio());

		Marca aux_marca = new Marca();
		aux_marca.setIdMarca(v.getModelo().getMarca().getIdmarca());
		aux_marca.setNombre(v.getModelo().getMarca().getNombre());

		Modelo aux_mod = new Modelo();
		aux_mod.setIdModelo(v.getModelo().getIdmodelo());
		aux_mod.setMarca(aux_marca);
		//FALTA SETEAR ANIO

		Vehiculo aux_ve = new Vehiculo();
		aux_ve.setId_vehiculo(v.getId_vehiculo());
		aux_ve.setPatente(v.getPatente());
		aux_ve.setMotor(v.getMotor());
		aux_ve.setChasis(v.getChasis());
		aux_ve.setModelo(aux_mod);
		aux_ve.setPorcentaje(v.getPorcentaje());

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


		Poliza nueva_poliza = new Poliza();

		nueva_poliza.setNroPoliza(p.getNroPoliza()); //nroPoliza
		nueva_poliza.setCliente(aux_cliente); //CLIENTE
		nueva_poliza.setDomicilio_riesgo(aux_domR);
		nueva_poliza.setVehiculo(aux_ve);
		nueva_poliza.setKmPorAnio(p.getKmPorAnio());
		nueva_poliza.setSeguridad(aux_MS);

		flag = DAOPoliza.cargarPoliza(nueva_poliza);
	}




}
