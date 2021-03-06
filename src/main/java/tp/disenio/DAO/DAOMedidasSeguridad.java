package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Alarma;
import tp.disenio.clases.DispRastreo;
import tp.disenio.clases.Garage;
import tp.disenio.clases.MedidasSeguridad;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.Tuercas;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPoliza;

public class DAOMedidasSeguridad {


	public static int obtenerIDAlarma() {
		int retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		ResultSet rs = null;
		try {
			String Consulta = "select id_mds from medidas_seguridad where nombre = 'ALARMA'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getInt("id_mds");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;

	}

	public static double obtenerPorcentajeAlarma() {
		double retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		ResultSet rs = null;

		try {
			String Consulta = "select porcentaje from medidas_seguridad where nombre = 'ALARMA'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getDouble("porcentaje");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	public static int obtenerIDTuerca() {
		int retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		ResultSet rs = null;


		try {
			String Consulta = "select id_mds from medidas_seguridad where nombre = 'TUERCAS'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getInt("id_mds");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	public static double obtenerPorcentajeTuerca () {
		double retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		ResultSet rs = null;



		try {
			String Consulta = "select porcentaje from medidas_seguridad where nombre = 'TUERCA'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getDouble("porcentaje");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}

	public static int obtenerIDDisp() {
		int retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		ResultSet rs = null;

		try {
			String Consulta = "select id_mds from medidas_seguridad where nombre = 'DISPOSITIVO RASTREO'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getInt("id_mds");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	public static double obtenerPorcentajeDisp () {
		double retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		ResultSet rs = null;

		try {
			String Consulta = "select porcentaje from medidas_seguridad where nombre = 'DISPOSITIVO RASTREO'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getDouble("porcentaje");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	public static int obtenerIDGarage() {
		int retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		ResultSet rs = null;



		try {
			String Consulta = "select id_mds from medidas_seguridad where nombre = 'GARAGE'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getInt("id_mds");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}

	public static double obtenerPorcentajeGarage() {
		double retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		ResultSet rs = null;


		try {
			String Consulta = "select porcentaje from medidas_seguridad where nombre = 'GARAGE'";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getDouble("porcentaje");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}

	public static Boolean cargarPolizaTieneMDS(Poliza p) {
		Boolean retorno = false;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;


		Boolean aux_alarma = p.getSeguridad().getAlarma().isPosee();
		Boolean aux_dispR = p.getSeguridad().getRastreo().isPosee();
		Boolean aux_garage = p.getSeguridad().getGarage().isPosee();
		Boolean aux_tuercas = p.getSeguridad().getTuercas().isPosee();


		try {
			/*
			 int nro_poliza 1
			 int id_mds
			 double porcentaje actual;*/
			GestorPoliza gp = GestorPoliza.getInstance();
			if (aux_alarma) { // SI TIENE ALARMA
				PreparedStatement st_alarma = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_alarma.setLong(1, p.getNroPoliza()); //nro_poliza
				st_alarma.setInt(2, gp.obtenerIDAlarma());
				st_alarma.setDouble(3, gp.obtenerPorcentajeAlarma());
				st_alarma.executeUpdate();
				st_alarma.close();
			}
			if (aux_garage) { //si tiene garage
				PreparedStatement st_garage = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_garage.setLong(1, p.getNroPoliza()); //nro_poliza
				st_garage.setInt(2, gp.obtenerIDGarage());
				st_garage.setDouble(3, gp.obtenerPorcentajeGarage());
				st_garage.executeUpdate();
				st_garage.close();
			}
			if (aux_dispR) { //Si tiene disp R
				PreparedStatement st_dispR = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_dispR.setLong(1, p.getNroPoliza()); //nro_poliza
				st_dispR.setInt(2, gp.obtenerIDDisp());
				st_dispR.setDouble(3, gp.obtenerPorcentajeDisp());
				st_dispR.executeUpdate();
				st_dispR.close();
			}
			if (aux_tuercas) { //si tiene tueras
				PreparedStatement st_tuercas = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_tuercas.setLong(1, p.getNroPoliza()); //nro_poliza
				st_tuercas.setInt(2, gp.obtenerIDTuerca());
				st_tuercas.setDouble(3, gp.obtenerPorcentajeTuerca());
				st_tuercas.executeUpdate();
				st_tuercas.close();
			}

			retorno = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;

	}

	public static MedidasSeguridad recuperarMedidasSeguridad(long nroPoliza) {
		MedidasSeguridad retorno = new MedidasSeguridad();
		Alarma aux_alarma = new Alarma();
		DispRastreo aux_dis = new DispRastreo();
		Garage aux_garage = new Garage();
		Tuercas aux_tuercas = new Tuercas();
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		GestorPoliza gp = GestorPoliza.getInstance();
		ResultSet rs = null;


		try {
			String Consulta = "select * from poliza_tiene_mds where nro_poliza = " + nroPoliza;
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();

			while(rs.next()) {
				if (rs.getInt("id_mds") == gp.obtenerIDAlarma()) { //SI UNA DE LAS MEDIDAS ES ALARMA
					aux_alarma.setPosee(true);
					aux_alarma.setId_alarma(rs.getInt("id_mds"));
					aux_alarma.setPorcentaje((float) rs.getDouble("porcentaje_actual"));
				}
				if (rs.getInt("id_mds") == gp.obtenerIDDisp()) { //SI UNA DE LAS MEDIDAS ES DISP
					aux_dis.setPosee(true);
					aux_dis.setId_dispR(rs.getInt("id_mds"));
					aux_dis.setPorcentaje((float) rs.getDouble("porcentaje_actual"));
				}
				if (rs.getInt("id_mds") == gp.obtenerIDGarage()) { //SI UNA DE LAS MEDIDAS ES GARAGE
					aux_garage.setPosee(true);
					aux_garage.setId_garage(rs.getInt("id_mds"));
					aux_garage.setPorcentaje((float) rs.getDouble("porcentaje_actual"));
				}
				if (rs.getInt("id_mds") == gp.obtenerIDTuerca()) { //SI UNA DE LAS MEDIDAS ES TUERCAS
					aux_tuercas.setPosee(true);
					aux_tuercas.setId_tuercas(rs.getInt("id_mds"));
					aux_tuercas.setPorcentaje((float) rs.getDouble("porcentaje_actual"));
				}

				retorno.setAlarma(aux_alarma);
				retorno.setGarage(aux_garage);
				retorno.setRastreo(aux_dis);
				retorno.setTuercas(aux_tuercas);

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
