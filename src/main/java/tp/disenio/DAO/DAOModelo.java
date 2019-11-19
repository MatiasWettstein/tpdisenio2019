package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Localidad;
import tp.disenio.clases.Marca;
import tp.disenio.clases.Modelo;
import tp.disenio.gestores.GestorDB;

public class DAOModelo {

	public static Object[] listaModelos(Marca marca){

		ArrayList<Modelo> modelos = new ArrayList<>();
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

			int idMarca = marca.getIdMarca();

			String consulta2 = "select id_modelo, porcentaje, nombre from modelo where marca = " + idMarca;
			PreparedStatement st2 = con.prepareStatement(consulta2);
			rs = st2.executeQuery();
			while(rs.next()) {
				Modelo mod = new Modelo();
				mod.setNombre(rs.getString("nombre"));
				mod.setPorcentaje(rs.getFloat(2));
				modelos.add(mod);
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
		return modelos.toArray();


	}
}
