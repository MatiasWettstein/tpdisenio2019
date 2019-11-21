package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Direccion;
import tp.disenio.gestores.GestorDB;

public class DAODireccion {
	
	public static int  guardarDireccion (Direccion d) {
		
		int numeroDire =0;
		int idDire = 0; 
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		ResultSet rsDire = null;


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
			
			String Consulta = "select max(id_direccion) from direccion";
			PreparedStatement stDire = con.prepareStatement(Consulta);
			rsDire = stDire.executeQuery();
			
			while(rsDire.next()) {
				idDire = rsDire.getInt("max");
			}
			
			
			numeroDire = Integer.parseInt(d.getNumero());
			numeroDire +=1;
			

			//id_direccion 1 
			//calle, 2
			//numero 3
			//dpto, 4 
			//localidad 5 
			//piso 6
			
			
			PreparedStatement st = con.prepareStatement("INSERT INTO DIRECCION VALUES (?, ?, ?, ?, ?, ?)");
			st.setInt(1, idDire);
			st.setString(2, d.getCalle());
			st.setInt(3, numeroDire);
			if (d.getDpto() != null) st.setString(4, d.getDpto()); 
			st.setInt(5, d.getLocalidad().getId_localidad());
			if (d.getPiso() != 0) st.setInt(6, d.getPiso());
			
			

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

		return numeroDire; 

	}

	
	
	
}