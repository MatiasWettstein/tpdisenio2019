package tp.disenio.gestores;

import java.util.ArrayList;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.clases.Cliente;
import tp.disenio.clases.Hijo;
import tp.disenio.pantallas.*;

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

	public static void PantallaDarAltaPoliza() {
		PantallaDarAltaPoliza.start();

	}

	public static void agregarHijos() {
		PantallaDarAltaPoliza.agregarhijo();
	}

	public static void Pantalla2Alta(Cliente c, PolizaDTO p) {
		PantallaDarAltaPoliza2.start(c, p);

	}

	public static void pantalla3AltaMensual() {
		PantallaDarAltaPoliza3Mensual.start();
	}

	public static void pantalla3AltaSemestral() {
		PantallaDarAltaPoliza3Semestral.start();
	}


	public static void PantallaDarAltaCliente() {
		PantallaDarAltaCliente.start();
	}

	public static void PantallaDarAltaCliente2() {
		PantallaDarAltaCliente2.start();
	}


	public static void buscarcliente() {
		PantallaDarAltaPoliza.buscarcliente();		
	}


	



	
}
