package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Provincia;
import tp.disenio.gestores.GestorDB;

public class DAOProvincia {



	public static Object[] listaProvincia(){

		ArrayList<Provincia> Provincias= new ArrayList<>();
		ResultSet rs = null;
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

			PreparedStatement st = con.prepareStatement("select id_provincia, nombre from provincia");
			rs = st.executeQuery();
			while(rs.next()) {
				
				Provincia prov = new Provincia();
				prov.setNombre(rs.getString(2));
				prov.setId_provincia(rs.getInt("id_provincia"));
				prov.setPais();
				Provincias.add(prov);
			}
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
		return Provincias.toArray();


	}
}
