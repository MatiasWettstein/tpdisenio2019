package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Poliza;
import tp.disenio.gestores.GestorDB;

public class DAODescuentos {
	
	public static boolean  cargarDescuentos (Poliza p ) {
		
		boolean retorno = false;
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
			
			
			//id_Descuento 1 
			//nombre 2
			//porcentaje
			//numeroPoliza
			int id_descuento = p.getDescuento().getIdDescuentos();
			
			PreparedStatement st_unidadAdicional = con.prepareStatement("INSERT INTO DESCUENTOS VALUES (?, ?, ?, ?)");
			st_unidadAdicional.setInt(1, id_descuento);
			st_unidadAdicional.setString(2, "UNIDAD ADICIONAL");
			st_unidadAdicional.setDouble(3, p.getDescuento().getDescPorUnidadAdicional());
			st_unidadAdicional.setInt(4, p.getNroPoliza());
			st_unidadAdicional.executeUpdate();
			st_unidadAdicional.close();
			
			id_descuento+=1;

			PreparedStatement st_PagoSemestral = con.prepareStatement("INSERT INTO DESCUENTOS VALUES (?, ?, ?, ?)");
			st_PagoSemestral.setInt(1, id_descuento);
			st_PagoSemestral.setString(2, "PAGO SEMESTRAL");
			st_PagoSemestral.setDouble(3, p.getDescuento().getDescPorPagoSemestral());
			st_PagoSemestral.setInt(4, p.getNroPoliza());
			st_PagoSemestral.executeUpdate();
			st_PagoSemestral.close();
			
			id_descuento+=1;

			PreparedStatement st_PagoAdelantado = con.prepareStatement("INSERT INTO DESCUENTOS VALUES (?, ?, ?, ?)");
			st_PagoAdelantado.setInt(1, id_descuento);
			st_PagoAdelantado.setString(2, "PAGO ADELANTADO");
			st_PagoAdelantado.setDouble(3, p.getDescuento().getDescPorPagoSemestral());
			st_PagoAdelantado.setInt(4, p.getNroPoliza());
			st_PagoAdelantado.executeUpdate();
			st_PagoAdelantado.close();
			
			retorno = true;
			

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

		return retorno; 

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
			String Consulta = "select max(id_descuentos) from descuentos";
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