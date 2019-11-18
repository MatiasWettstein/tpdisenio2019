package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Cliente;
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
			System.out.println(Consulta);
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			PreparedStatement dir = con.prepareStatement("select * from direccion where id_direccion = " + rs.getString(15));
			dirrs = dir.executeQuery();
			PreparedStatement loc = con.prepareStatement("select * from localidad where id_localidad = " + dirrs.getString(6));
			locrs = loc.executeQuery();
			PreparedStatement prov = con.prepareStatement("select * from provincia where id_provincia = " + locrs.getString(5));
			provrs = prov.executeQuery();

			while(rs.next()) {
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


}
