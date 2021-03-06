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
		Connection con = gdb.conec;

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
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Provincias.toArray();


	}

	public static Provincia obtenerProvincia(int idProv) {
		Provincia retorno = new Provincia();
		ResultSet rs = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		try {
			String Consulta = "select * from provincia where id_provincia = " + idProv;


			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			/*
			 * id_localidad 1
			 * nombre 2 string
			 * pais string
			 */


			while(rs.next()) {
				retorno.setId_provincia(idProv);
				retorno.setNombre(rs.getString("nombre"));
				retorno.setPais();
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

}
