package tp.disenio.gestores;

import java.util.ArrayList;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.CuotaDTO;
import tp.disenio.DTO.DomicilioRiesgoDTO;
import tp.disenio.DTO.HijoDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.DTO.VehiculoDTO;
import tp.disenio.clases.Poliza;
import tp.disenio.pantallas.PantallaBuscarPoliza;
import tp.disenio.pantallas.PantallaDarAltaCliente;
import tp.disenio.pantallas.PantallaDarAltaCliente2;
import tp.disenio.pantallas.PantallaDarAltaPoliza;
import tp.disenio.pantallas.PantallaDarAltaPoliza2;
import tp.disenio.pantallas.PantallaDarAltaPoliza3Mensual;
import tp.disenio.pantallas.PantallaDarAltaPoliza3Semestral;
import tp.disenio.pantallas.PantallaInicio;
import tp.disenio.pantallas.PantallaLogin;
import tp.disenio.pantallas.PantallaPrincipal;
import tp.disenio.pantallas.PantallaRegistrarPago;
import tp.disenio.pantallas.PantallaRegistrarPago2;

public class GestorPantallas {

	public static void Inicio() {
		PantallaInicio.start();
	}


	public static void PantallaLogin() {
		PantallaLogin.start();
	}

	public static void PantallaPrincipal(){
		PantallaPrincipal.start();
	}

	public static void PantallaDarAltaPoliza(ClienteDTO c, PolizaDTO p, VehiculoDTO v,ArrayList<HijoDTO> listahijos, DomicilioRiesgoDTO dom) {
		PantallaDarAltaPoliza.start(c,  p,  v, listahijos, dom);

	}

	public static void agregarHijos() {
		PantallaDarAltaPoliza.agregarhijo();
	}

	public static void Pantalla2Alta(ClienteDTO c,VehiculoDTO v,ArrayList<HijoDTO> listahijos,  PolizaDTO p, DomicilioRiesgoDTO dom) {
		PantallaDarAltaPoliza2.start(c, p, v, listahijos, dom);

	}

	public static void pantalla3AltaMensual(ClienteDTO c, PolizaDTO p, VehiculoDTO v,ArrayList<HijoDTO> listahijos, DomicilioRiesgoDTO dom) {
		PantallaDarAltaPoliza3Mensual.start( c, p, v, listahijos, dom);
	}

	public static void pantalla3AltaSemestral(ClienteDTO c, PolizaDTO p, VehiculoDTO v,ArrayList<HijoDTO> listahijos, DomicilioRiesgoDTO dom) {
		PantallaDarAltaPoliza3Semestral.start( c,  p, v, listahijos, dom);
	}


	public static void PantallaDarAltaCliente(boolean vienedepoliza) {
		PantallaDarAltaCliente.start(vienedepoliza);
	}

	public static void PantallaDarAltaCliente2(ClienteDTO cliente, boolean vienedepoliza) {
		PantallaDarAltaCliente2.start(cliente, vienedepoliza);
	}


	public static void buscarcliente() {
		PantallaDarAltaPoliza.buscarcliente();
	}

	public static void registrarPago(Poliza p, ArrayList<CuotaDTO> cuotas, CuotaDTO c, double montoTotal) {
		PantallaRegistrarPago.start(p, cuotas, c, montoTotal);
	}

	public static void buscarPoliza() {
		PantallaBuscarPoliza.start();

	}

	public static void registrarPago2(Poliza p, ArrayList<CuotaDTO> cuotas, CuotaDTO c, double montoTotal) {
		PantallaRegistrarPago2.start(p, cuotas, c, montoTotal);
	}









}
