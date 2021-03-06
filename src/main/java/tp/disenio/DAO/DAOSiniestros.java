package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Siniestros;
import tp.disenio.gestores.GestorDB;

public class DAOSiniestros {

	public static void  guardarSiniestro (Siniestros s) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		int id_siniestro = 0;

		try {

			PreparedStatement st = con.prepareStatement("INSERT INTO SINIESTROS VALUES (?, ?, ?)");
			st.setInt(1, s.getId_siniestro());//id_siniestro 1
			st.setString(2, s.getNombre());//nombre, 2
			st.setDouble(3, s.getPorcentaje());//porcentaje 3

			st.executeUpdate();
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public static int recupearUltimoNID() {

		int retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		ResultSet rs = null;
		try {
			String Consulta = "select max(id_siniestro) from siniestros";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getInt("max");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}


	public static Siniestros obtenerSiniestro(String nombre) {
		Siniestros retorno = new Siniestros();
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		ResultSet rs = null;
		try {
			String Consulta = "select * from porcentajes where nombre = '" + nombre + "'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			//TIENE QUE ESTAR ESCRITO IGUAL QUE EN LA BD
			int id_siniestro = DAOSiniestros.recupearUltimoNID();
			id_siniestro +=1;
			retorno.setId_siniestro(id_siniestro);

			while(rs.next()) {
				retorno.setPorcentaje((float) rs.getDouble("valor"));
				retorno.setId_usuario(rs.getInt("id_usuario"));
				retorno.setNombre(nombre);
			}

			st.close();


		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;

	}

	public static Siniestros recuperarSiniestro(int idSiniestro) {
		Siniestros retorno = new Siniestros();
		ResultSet rs = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		try {
			String Consulta = "select * from siniestros where id_siniestro = " + idSiniestro;
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();

			while(rs.next()) {
				retorno.setPorcentaje((float) rs.getDouble("porcentaje"));
				retorno.setNombre(rs.getString("nombre"));
				retorno.setId_siniestro(idSiniestro);
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;

	}




}
