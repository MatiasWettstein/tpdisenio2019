package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Cliente;
import tp.disenio.clases.Direccion;
import tp.disenio.clases.Localidad;
import tp.disenio.clases.Provincia;
import tp.disenio.gestores.GestorDB;

public class DAOCliente {




	public static ArrayList<Cliente> buscarCliente(String NroCliente, String TipoDoc, String NroDoc, String Nombre, String Apellido){

		ArrayList<Cliente> Clientes= new ArrayList<>();
		ResultSet rs = null;
		ResultSet dirrs = null;
		ResultSet locrs = null;
		ResultSet provrs = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		try {
			con = gdb.crearConexion();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String Consulta = "select * from cliente";

			if(!NroCliente.isEmpty() || TipoDoc != "Seleccione_Tipo_Documento" || !NroDoc.isEmpty() || !Nombre.isEmpty() || !Apellido.isEmpty()) {

				Consulta += " where";

				if(!NroCliente.isEmpty()) {
					Consulta += " nro_cliente = " + NroCliente ;
				}
				if(!Apellido.isEmpty()) {
					Consulta += " apellido = " + Apellido ;
				}
				if(!Nombre.isEmpty()) {
					Consulta += " nombre = " + Nombre ;
				}
				if(TipoDoc != "Seleccione_Tipo_Documento") {
					Consulta += " tipo_doc = " + TipoDoc ;
				}
				if(!NroDoc.isEmpty()) {
					Consulta += " nrodoc = " + NroDoc ;
				}
			}
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();

			while(rs.next()) {

				PreparedStatement dir = con.prepareStatement("select * from direccion where id_direccion = " + rs.getString(15));
				dirrs = dir.executeQuery();
				dirrs.next();
				PreparedStatement loc = con.prepareStatement("select * from localidad where id_localidad = " + dirrs.getString(6));
				locrs = loc.executeQuery();
				locrs.next();
				PreparedStatement prov = con.prepareStatement("select * from provincia where id_provincia = " + locrs.getString(5));
				provrs = prov.executeQuery();
				provrs.next();

				Provincia provincia = new Provincia();

				provincia.setId_provincia(provrs.getInt(1));
				provincia.setNombre(provrs.getString(2));
				provincia.setPais();

				Localidad localidad = new Localidad();

				localidad.setNombre(locrs.getString(2));
				localidad.setPorcentaje(locrs.getFloat(3));
				localidad.setCodigoPostal(locrs.getString(4));
				localidad.setProvincia(provincia);

				Direccion direccion = new Direccion();
				direccion.setCalle(dirrs.getString(2));
				direccion.setNumero(dirrs.getString(3));
				direccion.setDpto(dirrs.getString(4));
				direccion.setPiso(dirrs.getString(5));
				direccion.setLocalidad(localidad);

				Cliente cliente = new Cliente();

				cliente.setNroCliente(rs.getString(1));
				cliente.setTipo(rs.getString(2));
				cliente.setCuil(rs.getString(3));
				cliente.setFechaNac(rs.getString(4));
				cliente.setDocumento(rs.getString(5));
				cliente.setNombre(rs.getString(6));
				cliente.setApellido(rs.getString(7));
				cliente.setCorreoElectronico(rs.getString(8));
				cliente.setProfesion(rs.getString(9));
				cliente.setEstado(rs.getString(10));
				cliente.setSexo(rs.getString(11));
				cliente.setCondicionIVA(rs.getString(12));
				cliente.setEstadoCivil(rs.getString(13));
				cliente.setAnioRegistro(rs.getString(14));
				cliente.setTipoDocumento(rs.getString(16));
				cliente.setDireccion(direccion);

				Clientes.add(cliente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Clientes;


	}


	public static void guardarCliente (Cliente c) {
		
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		


		try {
			con = gdb.crearConexion();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

		//nro_cliente, tipo_c, cuil, fecha_nac, nrodoc, nombre, apellido, email, profesion, estado_cliente, sexo, cond_iva, 
			//estado_civil, anio_registro, direccion, tipo_doc
			PreparedStatement st = con.prepareStatement("INSERT INTO CLIENTE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, c.getNroCliente());
			st.setString(2, c.getTipo());
			st.setString(3, c.getCuil());
			st.setString(4, c.getFechaNac()); // REVISAR EL TIPO DE DATO QUE DEVUELVE 
			st.setString(5, c.getDocumento());
			st.setString(6, c.getNombre());
			st.setString(7, c.getApellido());
			st.setString(8, c.getCorreoElectronico());
			st.setString(9, c.getProfesion());
			st.setString(10, c.getEstadoCivil());
			st.setString(11, c.getSexo());
			st.setString(12, c.getCondicionIVA());
			st.setString(13, c.getEstadoCivil());
			
			st.executeUpdate();
			st.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
