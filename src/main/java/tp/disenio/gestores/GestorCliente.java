package tp.disenio.gestores;

import java.util.ArrayList;

import tp.disenio.DAO.DAOCliente;
import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.DireccionDTO;
import tp.disenio.DTO.LocalidadDTO;
import tp.disenio.DTO.ProvinciaDTO;
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







}
