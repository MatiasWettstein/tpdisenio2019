package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.DTO.DescuentosDTO;
import tp.disenio.gestores.GestorDB;

public class DAODescuentos {
	
	public static int  guardarDescuentos (DescuentosDTO d) {
		
		int idDescuentos = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		ResultSet rsDescuentos = null;


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
			
			
			//id_Descuentos 1 
			//descPorUnidadAdicional, 2
			//descPorPagoSemestral 3
			//descPorPagoAdelantado, 4 
			
			PreparedStatement st = con.prepareStatement("INSERT INTO DESCUENTOS VALUES (?, ?, ?, ?)");
			st.setInt(1, d.getIdDescuentos());
			st.setDouble(2, d.getDescPorUnidadAdicional());
			st.setDouble(3, d.getDescPorPagoSemestral());
			st.setDouble(4, d.getDescPorPagoAdelantado());
			
			

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

		return idDescuentos; 

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