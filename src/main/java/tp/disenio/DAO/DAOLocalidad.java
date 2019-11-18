package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Localidad;
import tp.disenio.clases.Provincia;
import tp.disenio.gestores.GestorDB;

public class DAOLocalidad {

	public static Object[] listaLocalidad(Provincia prov){

		ArrayList<Localidad> Localidades= new ArrayList<>();
		ResultSet rs = null;
		ResultSet provincia = null;
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

			int idProv = prov.getId_provincia();

			String consulta2 = "select id_localidad, nombre, porcentaje, codigo_postal from localidad where provincia = " + idProv;
			PreparedStatement st2 = con.prepareStatement(consulta2);
			rs = st2.executeQuery();
			while(rs.next()) {
				Localidad loc = new Localidad();
				loc.setNombre(rs.getString(2));
				loc.setId_localidad(rs.getInt("id_localidad"));
				loc.setPorcentaje(rs.getFloat("porcentaje"));
				loc.setCodigoPostal(rs.getString(4));
				Localidades.add(loc);
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
		return Localidades.toArray();


	}
}
