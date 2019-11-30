package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Modelo;
import tp.disenio.clases.Vehiculo;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorParametros;

public class DAOVehiculo {

	public static void  guardarVehiculo (Vehiculo v) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		try {

			PreparedStatement st = con.prepareStatement("INSERT INTO VEHICULO VALUES (?, ?, ?, ?, ?, ?, ?)");
			st.setInt(1, v.getId_vehiculo()); //id_vehiculo 1
			st.setString(2, v.getPatente()); //patente 2 varchar
			st.setString(3, v.getMotor()); // motor 3
			st.setString(4, v.getChasis()); //chasis 4
			st.setInt(5, v.getAnio()); //anio 5 numeric
			st.setInt(6, v.getModelo().getIdModelo()); //FK modelo
			st.setFloat(7, v.getPorcentaje()); //porcentaje_actual

			st.executeUpdate();
			//st.close();

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
			String Consulta = "select max(id_vehiculo) from vehiculo";
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


	public static Vehiculo recuperarVehiculo(int idVeh) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		Vehiculo retorno = new Vehiculo();
		ResultSet rs = null;

		try {
			String Consulta = "select * from vehiculo where id_vehiculo = " + idVeh;


			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			/*
			 * id_vehiculo 1
			 * patente 2 string
			 * motor 3 string
			 * chasis 4 string
			 * anio 5 int
			 * modelo 6 int fk
			 * porcentaje_actual 7 double
			 */


			while(rs.next()) {
				retorno.setId_vehiculo(idVeh);
				retorno.setPatente(rs.getString("patente"));
				retorno.setMotor(rs.getString("motor"));
				retorno.setChasis(rs.getString("chasis"));
				retorno.setAnio(rs.getInt("anio"));
				GestorParametros gpm = GestorParametros.getInstance();
				Modelo aux_mod = new Modelo();
				aux_mod = gpm.obtenerModelo(rs.getInt("modelo"));
				retorno.setModelo(aux_mod);
				retorno.setPorcentaje((float) rs.getDouble("porcentaje_actual"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}



}
