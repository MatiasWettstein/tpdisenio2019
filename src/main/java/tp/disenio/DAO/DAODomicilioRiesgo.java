package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Cliente;
import tp.disenio.clases.DomicilioRiesgo;
import tp.disenio.gestores.GestorDB;

public class DAODomicilioRiesgo {
	
	public static int guardarDomRiesgo (DomicilioRiesgo d) { //DEVUELVE EL ID DEL DOM CARGADO
		int id_dom = 0;
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

			id_dom = DAODomicilioRiesgo.recupearUltimoNID(d); 
			
			id_dom +=1;

			PreparedStatement st = con.prepareStatement("INSERT INTO DOMICILIO_RIESGO VALUES (?, ?, ?)");
			st.setInt(1, id_dom); //id_domicilio
			st.setFloat(2, d.getPorcentajeDomicilio() ); //porcentaje
			st.setInt(3, d.getLocalidad().getId_localidad());//FK LOCALIDAD  

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

		return id_dom;

	}

	private static int recupearUltimoNID(DomicilioRiesgo d) {
		
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
			String Consulta = "select max(id_domicilio) from domicilio_riesgo";
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

}
