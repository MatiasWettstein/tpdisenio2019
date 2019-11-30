package tp.disenio.gestores;

import java.sql.Connection;
import java.util.ArrayList;

import tp.disenio.DAO.DAOCliente;
import tp.disenio.DAO.DAODireccion;
import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.DireccionDTO;
import tp.disenio.DTO.LocalidadDTO;
import tp.disenio.DTO.ProvinciaDTO;
import tp.disenio.clases.Cliente;
import tp.disenio.clases.Direccion;
import tp.disenio.clases.Localidad;
import tp.disenio.clases.Provincia;

public class GestorCliente {

	private static GestorCliente GCliente ; // Patron Singleton -- Unica instancia tipo gestor creada.

	private GestorCliente(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
	}

	public static GestorCliente getInstance() { // Patron Singleton -- Devuelve la instancia, si no existe la crea
		if ( GCliente == null) {
			GCliente = new GestorCliente();
		}
		return GCliente;
	}

	public static ArrayList<ClienteDTO> buscarCliente(String NroCliente, String TipoDoc, String NroDoc, String Nombre, String Apellido){

		ArrayList<Cliente> clientes = DAOCliente.buscarCliente(NroCliente, TipoDoc, NroDoc, Nombre, Apellido);
		ArrayList<ClienteDTO> retorno = new ArrayList<>();
		for(Cliente c:clientes) {

			ProvinciaDTO prov = new ProvinciaDTO();
			prov.setId_provincia(c.getDireccion().getLocalidad().getProvincia().getId_provincia());
			prov.setNombre(c.getDireccion().getLocalidad().getProvincia().getNombre());
			prov.setPais(c.getDireccion().getLocalidad().getProvincia().getPais());

			LocalidadDTO loc = new LocalidadDTO();
			loc.setCodigoPostal(c.getDireccion().getLocalidad().getCodigoPostal());
			loc.setId_localidad(c.getDireccion().getLocalidad().getId_localidad());
			loc.setNombre(c.getDireccion().getLocalidad().getNombre());
			loc.setPorcentaje(c.getDireccion().getLocalidad().getPorcentaje());
			loc.setProvincia(prov);


			DireccionDTO dir = new DireccionDTO();
			dir.setCalle(c.getDireccion().getCalle());
			dir.setDpto(c.getDireccion().getDpto());
			dir.setLocalidad(loc);
			dir.setNumero(c.getDireccion().getNumero());

			String piso = Integer.toString(c.getDireccion().getPiso());
			dir.setPiso(piso);


			ClienteDTO dto = new ClienteDTO();

			dto.setNroCliente(c.getNroCliente());
			dto.setApellido(c.getApellido());
			dto.setFechaNac(c.getFechaNac());
			dto.setNombre(c.getNombre());
			dto.setDocumento(c.getDocumento());
			dto.setSexo(c.getSexo());
			dto.setCorreoElectronico(c.getCorreoElectronico());
			dto.setProfesion(c.getProfesion());
			dto.setAnioRegistro(c.getAnioRegistro());
			dto.setTipoDoc(c.getTipoDocumento());
			dto.setCuil(c.getCuil());
			dto.setCondicionIVA(c.getCondicionIVA());
			dto.setEstadoCivil(c.getEstadoCivil());
			dto.setEstadoCliente(c.getEstado());
			dto.setTipoC(c.getTipo());
			dto.setDireccion(dir);

			retorno.add(dto);

		}


		return retorno;
	}

	public static String  generarNumeroCliente() {
		String retorno = null;
		String ultimoGuardado = DAOCliente.recuperarUltimoNroCliente();
		String primeraParte = ultimoGuardado.substring(0,3);
		String numero_aux = ultimoGuardado.substring(3);
		int numero = Integer.parseInt(numero_aux);
		numero+=1; //incremento el numero
		String numeroNuevo = Integer.toString(numero);

		if (numeroNuevo.length()<8) {
			String aux = "";
			for (int i = numeroNuevo.length(); i<8; i++) {
				aux += "0";
			}
			aux+=numeroNuevo;
			numeroNuevo = aux;
		}

		retorno = primeraParte + numeroNuevo;

		return retorno;
	}

	// metodo guardar cliente le paso un DTO crea una instancia de cliente y llama al dao para que guarde en la BD
	public static Boolean guardarCliente(ClienteDTO c) {

		boolean flag = false;

		Cliente clienteFinal = new Cliente();
		clienteFinal = GestorCliente.generarInstanciaCliente(c);

		flag = DAOCliente.guardarCliente(clienteFinal);

		return flag;
		//ACORDARSE DE MOSTRAR UN OPTION PANE CON EL MSJ "CLIENTE GENERADO CON EXITO" --> el DAO Tiene uqe devolver un bool para saber si se pudo guardar bien
	}


	public static Cliente generarInstanciaCliente(ClienteDTO c) {

		Provincia provCliente = new Provincia();
		provCliente.setNombre(c.getDireccion().getLocalidad().getProvincia().getNombre());
		provCliente.setId_provincia(c.getDireccion().getLocalidad().getProvincia().getId_provincia());
		provCliente.setPais();

		Localidad locCliente = new Localidad();
		locCliente.setId_localidad(c.getDireccion().getLocalidad().getId_localidad());
		locCliente.setCodigoPostal(c.getDireccion().getLocalidad().getCodigoPostal());
		locCliente.setNombre(c.getDireccion().getLocalidad().getNombre());
		locCliente.setPorcentaje(c.getDireccion().getLocalidad().getPorcentaje());
		locCliente.setProvincia(provCliente);

		Direccion direCliente = new Direccion();
		direCliente.setCalle(c.getDireccion().getCalle());
		direCliente.setNumero(c.getDireccion().getNumero());
		if (c.getDireccion().getPiso() != null) {
			int piso = Integer.parseInt(c.getDireccion().getPiso());
			direCliente.setPiso(piso);
		}
		else direCliente.setPiso(0);
		direCliente.setDpto(c.getDireccion().getDpto());


		direCliente.setLocalidad(locCliente);

		Cliente clienteFinal = new Cliente();
		clienteFinal.setNroCliente(c.getNroCliente());
		clienteFinal.setApellido(c.getApellido());
		clienteFinal.setFechaNac(c.getFechaNac());
		clienteFinal.setNombre(c.getNombre());
		clienteFinal.setTipoDocumento(c.getTipoDoc());
		clienteFinal.setDocumento(c.getDocumento());
		clienteFinal.setSexo(c.getSexo());
		clienteFinal.setDireccion(direCliente);
		clienteFinal.setCorreoElectronico(c.getCorreoElectronico());
		clienteFinal.setProfesion(c.getProfesion());
		clienteFinal.setAnioRegistro(c.getAnioRegistro());
		clienteFinal.setEstado(c.getEstadoCliente());
		clienteFinal.setCuil(c.getCuil());
		clienteFinal.setCondicionIVA(c.getCondicionIVA());
		clienteFinal.setEstadoCivil(c.getEstadoCivil());
		clienteFinal.setTipo(c.getTipoC());


		return clienteFinal;

	}

	public static int cantidadPoliza(ClienteDTO c) {

		return DAOCliente.cantPoliza(c);

	}

	public static boolean clienteExistente(String tipoD, String doc) {
		boolean retorno = false;
		//devuelve true si encuentra un cliente con esos datos

		if (!DAOCliente.clienteExistente(tipoD, doc).isEmpty()) {
			retorno = true;
		}
		return retorno;
	}


	public static Cliente recuperarCliente (String nroC) {
		Cliente retorno = new Cliente();
		retorno = DAOCliente.recuperarCliente(nroC);
		return retorno;
	}

	public static Direccion recuperarDireccion (int idDire) {
		Direccion retorno = new Direccion();
		retorno = DAODireccion.recuperarDireccion(idDire);
		return retorno;
	}
}
