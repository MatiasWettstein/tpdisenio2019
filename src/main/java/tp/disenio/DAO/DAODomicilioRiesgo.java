package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.DomicilioRiesgo;
import tp.disenio.clases.Localidad;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorParametros;

public class DAODomicilioRiesgo {

	public static void guardarDomRiesgo (DomicilioRiesgo d, Connection con) { //DEVUELVE EL ID DEL DOM CARGADO

		try {

			PreparedStatement st = con.prepareStatement("INSERT INTO DOMICILIO_RIESGO VALUES (?, ?, ?)");
			st.setInt(1, d.getId_domicilioR()); //id_domicilio
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

		return retorno;
	}

	public static DomicilioRiesgo recuperarDomicilioRiesgo(int idDom, Connection con) {
		DomicilioRiesgo retorno = new DomicilioRiesgo();
		ResultSet rs = null;

		try {
			String Consulta = "select * from domicilio_riesgo where id_domicilio = " + idDom;
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();

			while(rs.next()) {
				retorno.setId_domicilioR(idDom);
				retorno.setPorcentajeDomicilio((float) rs.getDouble("porcentaje"));
				Localidad aux_loc = new Localidad();
				GestorParametros gpm = GestorParametros.getInstance();
				aux_loc = gpm.obtenerLocalidad(rs.getInt("localidad"), con);
				retorno.setLocalidad(aux_loc);
			}



		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;

	}


}
