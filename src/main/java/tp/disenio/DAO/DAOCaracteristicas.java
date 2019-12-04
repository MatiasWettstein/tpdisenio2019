package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Caracteristicas;
import tp.disenio.clases.Poliza;
import tp.disenio.gestores.GestorDB;

public class DAOCaracteristicas {

	public static Boolean cargarCaracteristicas(Poliza p) {
		Boolean retorno = false;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		try {
			/*
			 int id_caracteristicas 1
			 String nombre 2
			 double porcentaje 3
			 int nro_poliza4
			 */
			int id_carac = DAOCaracteristicas.recupearUltimoNID();
			id_carac +=1;

			if (p.getHijos_declarados().isEmpty()) { //si no tiene hijos cargo solo los KM
				PreparedStatement st_km = con.prepareStatement("INSERT INTO caracteristicas VALUES (?, ?, ?, ?)");
				st_km.setInt(1, id_carac); //id_caracteristicas
				st_km.setString(2, "PORCENTAJE KM"); //nombre
				st_km.setDouble(3, (float) p.getCaracteristicas().getPorcentajeKm());//porcentaje
				st_km.setLong(4, p.getNroPoliza());//nro poliza
				st_km.executeUpdate();
				st_km.close();
			}
			else { //sino cargo los dos
				PreparedStatement st_hijo = con.prepareStatement("INSERT INTO caracteristicas VALUES (?, ?, ?, ?)");
				st_hijo.setInt(1, id_carac); //id_caracteristicas
				st_hijo.setString(2, "PORCENTAJE HIJO"); //nombre
				st_hijo.setDouble(3, (float) p.getCaracteristicas().getPorcentajeHijo());//porcentaje
				st_hijo.setLong(4, p.getNroPoliza());//nro poliza
				st_hijo.executeUpdate();
				st_hijo.close();
				//ACA TENGO QUE AUMENTAR EL ID porque no esta de la misma forma que en la clase
				PreparedStatement st_km = con.prepareStatement("INSERT INTO caracteristicas VALUES (?, ?, ?, ?)");
				id_carac +=1;
				st_km.setInt(1, id_carac); //id_caracteristicas
				st_km.setString(2, "PORCENTAJE KM"); //nombre
				st_km.setDouble(3, (float) p.getCaracteristicas().getPorcentajeKm());//porcentaje
				st_km.setLong(4, p.getNroPoliza());//nro poliza
				st_km.executeUpdate();
				st_km.close();

			}

			retorno = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;

	}

	public static int recupearUltimoNID() {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		int retorno = 0;
		ResultSet rs = null;

		try {
			String Consulta = "select max(id_caracteristicas) from caracteristicas";
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

	public static double obtenerPorcentajeHijo() {
		double retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		ResultSet rs = null;

		try {
			String Consulta = "select valor from porcentajes where nombre = 'PORCENTAJE HIJO'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getDouble("valor");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}

	public static double obtenerPorcentajeKM() {
		double retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		ResultSet rs = null;

		try {
			String Consulta = "select valor from porcentajes where nombre = 'PORCENTAJE KM'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getDouble("valor");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	public static Caracteristicas recuperarCaracteristicas (long nroPoliza) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		Caracteristicas retorno = new Caracteristicas();
		ResultSet rs_km = null;
		ResultSet rs_hijo = null;

		try {

			String Consulta_km = "select * from caracteristicas where nro_poliza = " + nroPoliza + " and nombre = 'PORCENTAJE KM'";
			PreparedStatement st_km = con.prepareStatement(Consulta_km);
			rs_km = st_km.executeQuery();
			while(rs_km.next()) {
				retorno.setPorcentajeKm(rs_km.getDouble("porcentaje"));
			}

			String Consulta_hijo = "select * from caracteristicas where nro_poliza = " + nroPoliza + " and nombre = 'PORCENTAJE HIJO'";
			PreparedStatement st_hijo = con.prepareStatement(Consulta_hijo);
			rs_hijo = st_hijo.executeQuery();
			while(rs_hijo.next()) {
				retorno.setPorcentajeKm(rs_hijo.getDouble("porcentaje"));
			}

			st_km.close();
			st_hijo.close();



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}
}
