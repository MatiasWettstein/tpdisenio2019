package tp.disenio.gestores;

import java.util.ArrayList;

import tp.disenio.DAO.DAOCliente;
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
			dir.setPiso(c.getDireccion().getPiso());


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
	public static void guardarCliente(ClienteDTO c) {

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
		direCliente.setPiso(c.getDireccion().getPiso());
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




		//ACORDARSE DE MOSTRAR UN OPTION PANE CON EL MSJ "CLIENTE GENERADO CON EXITO" --> el DAO Tiene uqe devolver un bool para saber si se pudo guardar bien
	}


	public static int cantidadPoliza() {








		return 0;
	}



}
