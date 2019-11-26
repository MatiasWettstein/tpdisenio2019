package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.clases.Cliente;
import tp.disenio.clases.Direccion;
import tp.disenio.clases.Localidad;
import tp.disenio.clases.Provincia;
import tp.disenio.gestores.GestorCliente;
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

			if(!NroCliente.isEmpty() || TipoDoc != "SELECCIONE_TIPO_DOC" || !NroDoc.isEmpty() || !Nombre.isEmpty() || !Apellido.isEmpty()) {

				Consulta += " where";

				if(!NroCliente.isEmpty()) {
					String auxnc = "'"+ NroCliente + "'";
					Consulta += " nro_cliente = " + auxnc ;
				}

				if(!Apellido.isEmpty() && Consulta.equalsIgnoreCase("select * from cliente where")) {
					String auxap = "'"+ Apellido + "'";
					Consulta += " apellido = " + auxap ;
				}else if(!Apellido.isEmpty() && !Consulta.equalsIgnoreCase("select * from cliente where")){
					String auxap = "'"+ Apellido + "'";
					Consulta += " and apellido = " + auxap ;
				}
				if(!Nombre.isEmpty() && Consulta.equalsIgnoreCase("select * from cliente where")) {
					String auxnom = "'"+ Nombre + "'";
					Consulta += " nombre = " + auxnom ;
				}else if(!Nombre.isEmpty() && !Consulta.equalsIgnoreCase("select * from cliente where")) {
					String auxnom = "'"+ Nombre + "'";
					Consulta += " and nombre = " + auxnom ;
				}
				if(TipoDoc != "SELECCIONE_TIPO_DOC" && Consulta.equalsIgnoreCase("select * from cliente where")) {
					String auxtd = "'"+ TipoDoc + "'";
					Consulta += " tipo_doc = " + auxtd ;
				}else if(TipoDoc != "SELECCIONE_TIPO_DOC" && !Consulta.equalsIgnoreCase("select * from cliente where")) {
					String auxtd = "'"+ TipoDoc + "'";
					Consulta += " and tipo_doc = " + auxtd ;
				}
				if(!NroDoc.isEmpty() && Consulta.equalsIgnoreCase("select * from cliente where")) {
					String auxnd = "'"+ NroDoc + "'";
					Consulta += " nrodoc = " + auxnd ;
				}else if(!NroDoc.isEmpty() && !Consulta.equalsIgnoreCase("select * from cliente where")) {
					String auxnd = "'"+ NroDoc + "'";
					Consulta += " and nrodoc = " + auxnd ;
				}
			}

			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();

			while(rs.next()) {

				PreparedStatement dir = con.prepareStatement("select * from direccion where id_direccion = " + rs.getString(15));
				dirrs = dir.executeQuery();
				dirrs.next();
				PreparedStatement loc = con.prepareStatement("select * from localidad where id_localidad = " + dirrs.getString(5));
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
				direccion.setPiso(dirrs.getInt(5));
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
				cliente.setAnioRegistro(rs.getInt(14));
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


	public static boolean guardarCliente (Cliente c) {
		boolean retorno = false;
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

			int idDire = DAODireccion.guardarDireccion(c.getDireccion()); //me devuelve la id de la direccion asi lo asocio al cliente

			//nro_cliente 1
			//tipo_c, 2
			//cuil 3
			//fecha_nac, 4
			//nrodoc 5
			//nombre 6
			//apellido 7
			//email 8
			//profesion 9
			//estado_cliente 10
			//sexo 11
			//cond_iva 12
			//estado_civil 13
			//anio_registro 14
			//direccion 15
			//tipo_doc 16

			PreparedStatement st = con.prepareStatement("INSERT INTO CLIENTE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, c.getNroCliente());
			st.setString(2, c.getTipo());
			st.setString(3, c.getCuil());
			st.setString(4, c.getFechaNac());
			st.setString(5, c.getDocumento());
			st.setString(6, c.getNombre());
			st.setString(7, c.getApellido());
			st.setString(8, c.getCorreoElectronico());
			st.setString(9, c.getProfesion());
			st.setString(10, c.getEstadoCivil());
			st.setString(11, c.getSexo());
			st.setString(12, c.getCondicionIVA());
			st.setString(13, c.getEstadoCivil());
			st.setInt(14, c.getAnioRegistro());
			st.setInt(15, idDire);
			st.setString(16, c.getTipoDocumento());

			st.executeUpdate();
			st.close();
			retorno = true;


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

		return retorno;

	}


	public static String recuperarUltimoNroCliente () {
		String retorno = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		ResultSet rs = null;


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
			String Consulta = "select max(nro_cliente) from cliente";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getString("max");
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	public static int cantPoliza(ClienteDTO cliente) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		ResultSet rs = null;
		int retorno=0;
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

			String Consulta = "SELECT DISTINCT vehiculo FROM poliza where cliente = " + "'" + cliente.getNroCliente() + "'";

			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			while(rs.next()) {
				retorno++;
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
		return retorno;
	}

	public static String  clienteExistente(String tipoD, String doc ) {
		String nroCliente = "";
		//devuelve nroCliente si encuentra un cliente con esos datos sino null
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

			String aux = "'" + tipoD+ "'";
			String auxN = "'" + doc + "'";
			String consulta = "select nro_cliente from cliente where nrodoc = " + auxN + " and tipo_doc = " + aux;
			PreparedStatement st = con.prepareStatement(consulta);
			rs = st.executeQuery();
			while(rs.next()) {
				nroCliente = rs.getString("nroCliente");
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

		return nroCliente;
	}

	public static Cliente recuperarCliente (String nroC) {
		Cliente retorno = new Cliente();
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
			String Consulta = "select * from cliente where nro_cliente = '" + nroC +"'" ;


			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			//nro_cliente 1
			//tipo_c, 2
			//cuil 3
			//fecha_nac, 4
			//nrodoc 5
			//nombre 6
			//apellido 7
			//email 8
			//profesion 9
			//estado_cliente 10
			//sexo 11
			//cond_iva 12
			//estado_civil 13
			//anio_registro 14
			//direccion 15
			//tipo_doc 16
			
			GestorCliente gc = GestorCliente.getInstance();
			while(rs.next()) {
				retorno.setNroCliente(nroC);
				retorno.setTipo(rs.getString("tipo_c"));
				retorno.setCuil(rs.getString("cuil"));
				retorno.setFechaNac(rs.getString("fecha_nac"));
				retorno.setDocumento(rs.getString("nrodoc"));
				retorno.setNombre(rs.getString("nombre"));
				retorno.setApellido(rs.getString("apellido"));
				retorno.setCorreoElectronico(rs.getString("email"));
				retorno.setProfesion(rs.getString("profesion"));
				retorno.setEstado(rs.getString("estado_cliente"));
				retorno.setSexo(rs.getString("sexo"));
				retorno.setCondicionIVA(rs.getString("cond_iva"));
				retorno.setEstadoCivil(rs.getString("estado_civil"));
				retorno.setAnioRegistro(rs.getInt("anio_registro"));
				retorno.setTipoDocumento(rs.getString("tipo_doc"));
				Direccion aux_dire = new Direccion();
				aux_dire = gc.recuperarDireccion(rs.getInt("direccion"));
				retorno.setDireccion(aux_dire);
				
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
		
		return retorno; 
	}
	
}
