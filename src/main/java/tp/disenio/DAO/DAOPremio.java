package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Premio;
import tp.disenio.gestores.GestorDB;

public class DAOPremio {
	
	public static int  guardarPrima (Premio p) {
		
		int idPremio = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		ResultSet rsPremio = null;


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
			
			String Consulta = "select max(id_direccion) from premio";
			PreparedStatement stPremio = con.prepareStatement(Consulta);
			rsPremio = stPremio.executeQuery();
			
			while(rsPremio.next()) {
				idPremio = rsPremio.getInt("max");
			}
			System.out.println(idPremio + "DEVOLUCION CONSULTA");
			idPremio++;
			System.out.println(idPremio + "DESPUES DE INCREMENTO");


			//id_premio 1 
			//prima, 2
			//derecho 3
			//monto, 4 

			
			PreparedStatement st = con.prepareStatement("INSERT INTO PREMIO VALUES (?, ?, ?, ?, ?, ?)");
			st.setInt(1, idPremio);
			st.setFloat(2, p.getPrima());
			st.setFloat(3, p.getDerechoEmision());
			st.setFloat(4, p.getMontoTotal());
			
			

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

		return idPremio; 

	}

	
	
	
}