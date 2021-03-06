package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Premio;
import tp.disenio.gestores.GestorDB;

public class DAOPremio {

	public static void  guardarPremio (Premio p) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO PREMIO VALUES (?, ?, ?, ?)");
			st.setInt(1, p.getIdPremio());//id_premio 1
			st.setDouble(2, p.getPrima());//prima, 2
			st.setDouble(3, p.getDerechoEmision());//derecho 3
			st.setDouble(4, p.getMontoTotal());//monto, 4

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
			String Consulta = "select max(id_premio) from premio";
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

	public static Premio recuperarPremio (int idPremio) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		Premio retorno = new Premio();
		ResultSet rs = null;

		try {
			String Consulta = "select * from premio where id_premio = " + idPremio;


			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			/*
			 * id_premio 1
			 * prima 2 double
			 * derecho_emision 3 double
			 * monto_total 4 double
			 *
			 */


			while(rs.next()) {
				retorno.setIdPremio(idPremio);
				retorno.setPrima((float) rs.getDouble("prima"));
				retorno.setDerechoEmision((float) rs.getDouble("derecho_emision"));
				retorno.setMontoTotal((float) rs.getDouble("monto_total") );

			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}


}