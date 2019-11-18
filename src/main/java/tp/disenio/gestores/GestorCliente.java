package tp.disenio.gestores;

import java.util.ArrayList;

import tp.disenio.DAO.DAOCliente;
import tp.disenio.DTO.ClienteDTO;
import tp.disenio.clases.Cliente;

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
			dto.setDireccion(c.getDireccion().getCalle() + " " + c.getDireccion().getNumero());
			dto.setLocalidad(c.getDireccion().getLocalidad().getNombre());
			dto.setProvincia(c.getDireccion().getLocalidad().getProvincia().getNombre());

			retorno.add(dto);

		}


		return retorno;
	}







}
