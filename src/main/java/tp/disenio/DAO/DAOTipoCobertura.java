package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Cobertura;
import tp.disenio.clases.Siniestros;
import tp.disenio.gestores.GestorDB;

public class DAOTipoCobertura {

public static void guardarTipo (Cobertura c) {
		
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
			
			PreparedStatement st = con.prepareStatement("INSERT INTO PREMIO VALUES (?, ?, ?)");
			st.setInt(1, c.getId_cobertura());//id_cob 1 
			st.setString(2, c.getNombre());//nombre, 2
			st.setFloat(3, c.getPorcentajeTipoCobertura());//porcentaje 3
			
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

	
public static int recupearUltimoNID() {
		
		int retorno = 0;
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
			String Consulta = "select max(id_tipo) from tipo_cobertura";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getInt("max");
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

public static Cobertura obtenerCobertura(String nombre) {
	Cobertura retorno = new Cobertura();
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
		String Consulta = "select * from porcentajes where nombre = '" + nombre + "'";
		PreparedStatement st = con.prepareStatement(Consulta);
		rs = st.executeQuery();
//TIENE QUE ESTAR ESCRITO IGUAL QUE EN LA BD 

		while(rs.next()) {
			retorno.setPorcentajeTipoCobertura(rs.getFloat("valor")); 
			retorno.setId_usuario(rs.getInt("id_usuario"));
			retorno.setNombre(nombre);
		}
		
		int id_cobertura = DAOTipoCobertura.recupearUltimoNID();
		id_cobertura +=1;
		retorno.setId_cobertura(id_cobertura);
		

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
	
	
}
