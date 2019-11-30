package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Descuentos;
import tp.disenio.clases.Poliza;
import tp.disenio.gestores.GestorDB;

public class DAODescuentos {

	public static boolean  cargarDescuentos (Poliza p ) {

		boolean retorno = false;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		try {


			//id_Descuento 1
			//nombre 2
			//porcentaje
			//numeroPoliza
			int id_descuento = DAODescuentos.recupearUltimoNID();
			id_descuento +=1;

			PreparedStatement st_unidadAdicional = con.prepareStatement("INSERT INTO DESCUENTOS VALUES (?, ?, ?, ?)");
			st_unidadAdicional.setInt(1, id_descuento);
			st_unidadAdicional.setString(2, "UNIDAD ADICIONAL");
			st_unidadAdicional.setDouble(3, p.getDescuento().getDescPorUnidadAdicional());
			st_unidadAdicional.setLong(4, p.getNroPoliza());
			st_unidadAdicional.executeUpdate();
			st_unidadAdicional.close();

			id_descuento+=1;

			PreparedStatement st_PagoSemestral = con.prepareStatement("INSERT INTO DESCUENTOS VALUES (?, ?, ?, ?)");
			st_PagoSemestral.setInt(1, id_descuento);
			st_PagoSemestral.setString(2, "PAGO SEMESTRAL");
			st_PagoSemestral.setDouble(3, p.getDescuento().getDescPorPagoSemestral());
			st_PagoSemestral.setLong(4, p.getNroPoliza());
			st_PagoSemestral.executeUpdate();
			st_PagoSemestral.close();

			id_descuento+=1;

			PreparedStatement st_PagoAdelantado = con.prepareStatement("INSERT INTO DESCUENTOS VALUES (?, ?, ?, ?)");
			st_PagoAdelantado.setInt(1, id_descuento);
			st_PagoAdelantado.setString(2, "PAGO ADELANTADO");
			st_PagoAdelantado.setDouble(3, p.getDescuento().getDescPorPagoSemestral());
			st_PagoAdelantado.setLong(4, p.getNroPoliza());
			st_PagoAdelantado.executeUpdate();
			st_PagoAdelantado.close();

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
			String Consulta = "select max(id_descuento) from descuentos";
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

	public static Descuentos recuperarDescuentos(long nroPoliza) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		Descuentos retorno = new Descuentos();
		ResultSet rs_unidadAdicional = null;
		ResultSet rs_pagoSemestral = null;
		ResultSet rs_pagoAdelantado = null;



		try {
			//NO SETEO ID porque esta guardado diferente en la BD

			// "UNIDAD ADICIONAL"
			String Consulta_unidadAdicional = "select * from descuentos where nro_poliza = " + nroPoliza + " and nombre = 'UNIDAD ADICIONAL'";
			PreparedStatement st_unidadAdicional = con.prepareStatement(Consulta_unidadAdicional);
			rs_unidadAdicional = st_unidadAdicional.executeQuery();

			while(rs_unidadAdicional.next()) {
				retorno.setDescPorUnidadAdicional(rs_unidadAdicional.getDouble("porcentaje"));
			}
			//PAGO SEMESTRAL
			String Consulta_pagoSemestral = "select * from descuentos where nro_poliza = " + nroPoliza + " and nombre = 'PAGO SEMESTRAL'";
			PreparedStatement st_pagoSemestral = con.prepareStatement(Consulta_pagoSemestral);
			rs_pagoSemestral = st_pagoSemestral.executeQuery();

			while(rs_pagoSemestral.next()) {
				retorno.setDescPorPagoSemestral(rs_pagoSemestral.getDouble("porcentaje"));
			}
			//PAGO ADELANTADO
			String Consulta_pagoAdelantado = "select * from descuentos where nro_poliza = " + nroPoliza + " and nombre = 'PAGO ADELANTADO'";
			PreparedStatement st_pagoAdelantado = con.prepareStatement(Consulta_pagoAdelantado);
			rs_pagoAdelantado = st_pagoAdelantado.executeQuery();

			while(rs_pagoAdelantado.next()) {
				retorno.setDescPorPagoAdelantado(rs_pagoAdelantado.getDouble("porcentaje"));
			}


		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;

	}


}