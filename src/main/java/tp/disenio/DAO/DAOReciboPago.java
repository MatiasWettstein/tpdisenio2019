package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.ReciboPago;
import tp.disenio.gestores.GestorCobro;
import tp.disenio.gestores.GestorDB;

public class DAOReciboPago {

	public static int cargarReciboPago(ReciboPago recibo) {
		int retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		GestorCobro gc = GestorCobro.getInstance();
		Connection con = gdb.conec;

		try {
			/*
			 int id_cobro 1
			 String fecha_cobro
			 double monto_total
			 int nro_recibo
			 */
			retorno = gc.generarNumeroRecibo();

			int id_cobro = gc.obtenerUltimoNIDReciboPago();
			id_cobro++;

			PreparedStatement st = con.prepareStatement("INSERT INTO recibo_pago VALUES (?, ?, ?, ?)");
			st.setInt(1, id_cobro); //id_cobro
			st.setString(2, recibo.getFechacobro()); //fecha
			st.setDouble(3, recibo.getMontototal());//monto
			st.setLong(4, retorno);//nro_recibo
			st.executeUpdate();
			st.close();



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
			String Consulta = "select max(id_cobro) from recibo_pago";
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


	public static String obtenerfechapago(int recibo) {

		String retorno = "";
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		ResultSet rs = null;

		try {
			String Consulta = "select fecha_cobro from recibo_pago where id_cobro = "+recibo;
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();

			while(rs.next()) {
				retorno = rs.getString("fecha_cobro");
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
