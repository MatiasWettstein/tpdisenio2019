package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Descuentos;
import tp.disenio.gestores.GestorDB;

public class DAODescuentos {
	
	public static int  guardarDescuentos (Descuentos d) {
		
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
			
			String Consulta = "select max(id_descuentos) from descuentos";
			PreparedStatement stDescuentos = con.prepareStatement(Consulta);
			rsDescuentos = stDescuentos.executeQuery();
			
			while(rsDescuentos.next()) {
				idDescuentos = rsDescuentos.getInt("max");
			}
			System.out.println(idDescuentos + "DEVOLUCION CONSULTA");
			idDescuentos++;
			System.out.println(idDescuentos + "DESPUES DE INCREMENTO");


			//id_Descuentos 1 
			//descPorUnidadAdicional, 2
			//descPorPagoSemestral 3
			//descPorPagoAdelantado, 4 
			
			PreparedStatement st = con.prepareStatement("INSERT INTO DESCUENTOS VALUES (?, ?, ?, ?)");
			st.setInt(1, idDescuentos);
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

	
	
	
}