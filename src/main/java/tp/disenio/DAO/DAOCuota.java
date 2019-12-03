package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.DTO.CuotaDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.clases.Cuota;
import tp.disenio.clases.Mensual;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.Semestral;
import tp.disenio.gestores.GestorDB;

public class DAOCuota {

	public static Boolean cargarCuota(Poliza p) {
		Boolean retorno = false;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		try {
			/*
			 int id_cuota 1
			 String vencimiento
			 boolean pagada
			 double monto
			 int nro_poliza
			 int id_cobro
			 */


			if (p.getForma_pago().getNombre() == "MENSUAL") {

				ArrayList<Cuota> lista_cuotas = new ArrayList<>();
				lista_cuotas = ((Mensual)p.getForma_pago()).getCuotas(); //Creo un arraylist con las cuotas

				for (Cuota c: lista_cuotas) {
					PreparedStatement st = con.prepareStatement("INSERT INTO CUOTA VALUES (?, ?, ?, ?, ?, ?)");

					st.setInt(1, c.getId_cuota()); //id_cuota
					st.setString(2, c.getFecha_vencimiento()); //fech_vencimiento
					st.setBoolean(3, c.isPagada()); //pagada
					st.setDouble(4, c.getMonto());//monto
					st.setLong(5, p.getNroPoliza());//nro poliza
					st.setNull(6, java.sql.Types.INTEGER); //id_cobro
					st.executeUpdate();
					st.close();
				}

			}
			else if (p.getForma_pago().getNombre() == "SEMESTRAL") {

				Semestral aux_sem = new Semestral();
				aux_sem = (Semestral) p.getForma_pago();

				PreparedStatement st = con.prepareStatement("INSERT INTO CUOTA VALUES (?, ?, ?, ?, ?, ?)");
				st.setInt(1, aux_sem.getCuota1().getId_cuota()); //id_cuota
				st.setString(2, aux_sem.getCuota1().getFecha_vencimiento()); //fech_vencimiento
				st.setBoolean(3, aux_sem.getCuota1().isPagada()); //pagada
				st.setDouble(4, aux_sem.getCuota1().getMonto());//monto
				st.setLong(5, p.getNroPoliza());//nro poliza
				st.setNull(6, java.sql.Types.INTEGER); //id_cobro
				st.executeUpdate();
				st.close();
			}



			retorno = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;

	}

	public static int recupearUltimoNID() {

		int retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		ResultSet rs = null;

		try {
			String Consulta = "select max(id_cuota) from cuota";
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

	public static ArrayList<Cuota> recuperarListaCuotas(long nroPoliza) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		ArrayList<Cuota> retorno = new ArrayList<>();
		ResultSet rs = null;
		try {
			String Consulta = "select * from cuota where nro_poliza = " + nroPoliza;


			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			/*
		 int id_cuota 1
		 String vencimiento
		 boolean pagada
		 double monto
		 int nro_poliza
		 int id_cobro
			 */


			while(rs.next()) {
				Cuota cuota = new Cuota();
				cuota.setId_cuota(rs.getInt("id_cuota")); //id_cuota
				cuota.setFecha_vencimiento(rs.getString("vencimiento")); // vencimiento STRING
				cuota.setPagada(rs.getBoolean("pagada")); //pagada boolean
				cuota.setMonto(rs.getDouble("monto")); //monto --- double
				retorno.add(cuota);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	public static Cuota recupearCuota(long nroPoliza) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		Cuota retorno = new Cuota();
		ResultSet rs = null;

		try {
			String Consulta = "select * from cuota where nro_poliza = " + nroPoliza;


			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			/*
		 int id_cuota 1
		 String vencimiento
		 boolean pagada
		 double monto
		 int nro_poliza
		 int id_cobro
			 */

			while(rs.next()) {
				retorno.setId_cuota(rs.getInt("id_cuota")); //id_cuota
				retorno.setFecha_vencimiento(rs.getString("vencimiento")); // vencimiento STRING
				retorno.setPagada(rs.getBoolean("pagada")); //pagada boolean
				retorno.setMonto(rs.getDouble("monto")); //monto --- double
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	
	public static boolean pagarCuota(CuotaDTO c, int nroRecibo) { //ACTUALIZA EL ESTADO DE LA CUOTA 
		boolean retorno = false; 
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		

		try {
			/*
			 int id_cuota 1
			 String vencimiento
			 boolean pagada
			 double monto
			 int nro_poliza
			 int id_cobro
			 */

				PreparedStatement st = con.prepareStatement("UPDATE CUOTA SET pagada = ?, id_cobro = ? WHERE id_cuota = ?");
				st.setBoolean(1, true); //pagada
				st.setInt(2, nroRecibo); //id_cobro
				st.setInt(3, c.getId_cuota()); //id_cuota
				st.executeUpdate();
				st.close();
	



			retorno = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	
	

	public static boolean pagarCuotas(ArrayList<CuotaDTO> cuotas, int nroRecibo) {
		boolean retorno = false; 
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		

		try {
			/*
			 int id_cuota 1
			 String vencimiento
			 boolean pagada
			 double monto
			 int nro_poliza
			 int id_cobro
			 */
			for (CuotaDTO c: cuotas) {

				PreparedStatement st = con.prepareStatement("UPDATE CUOTA SET pagada = ?, id_cobro = ? WHERE id_cuota = ?");
				st.setBoolean(1, true); //pagada
				st.setInt(2, nroRecibo); //id_cobro
				st.setInt(3, c.getId_cuota()); //id_cuota
				st.executeUpdate();
				st.close();
	
			}


			retorno = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	
}
