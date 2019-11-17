package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.gestores.GestorDB;

public class DAOCliente {




	public static Object[] buscarCliente(String NroCliente, String TipoDoc, String NroDoc, String Nombre, String Apellido){

		ArrayList<ClienteDTO> Clientes= new ArrayList<>();
		ResultSet rs = null;
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

			String Consulta = "select nro_cliente, apellido, nombre, tipodoc, nrodoc from cliente";

			if(NroCliente !=null || TipoDoc != null || NroDoc != null || Nombre != null || Apellido != null) {

				Consulta += " where";

				if(NroCliente != null) {
					Consulta += " nro_cliente = " + NroCliente ;
				}
				if(Apellido != null) {
					Consulta += " apellido = " + Apellido ;
				}
				if(Nombre != null) {
					Consulta += " nombre = " + Nombre ;
				}
				if(TipoDoc != null) {
					Consulta += " tipodoc = " + TipoDoc ;
				}
				if(NroDoc != null) {
					Consulta += " nrodoc = " + NroDoc ;
				}
			}

			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			while(rs.next()) {
				ClienteDTO cliente = new ClienteDTO();

				cliente.setNroCliente(rs.getString(1));
				cliente.setApellido(rs.getString(2));
				cliente.setNombre(rs.getString(3));
				cliente.setTipoDoc(rs.getString(4));
				cliente.setDocumento(rs.getString(5));

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
		return Clientes.toArray();


	}


}
