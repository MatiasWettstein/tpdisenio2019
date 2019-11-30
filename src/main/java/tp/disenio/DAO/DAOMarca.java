package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Marca;
import tp.disenio.gestores.GestorDB;

public class DAOMarca {


	public static Object[] listaMarcas(){

		ArrayList<Marca> Marcas= new ArrayList<>();
		ResultSet rs = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		try {

			PreparedStatement st = con.prepareStatement("select id_marca, nombre_marca from marca");
			rs = st.executeQuery();
			while(rs.next()) {

				Marca marca_aux = new Marca();
				marca_aux.setNombre(rs.getString(2));
				marca_aux.setIdMarca(rs.getInt("id_marca"));
				Marcas.add(marca_aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Marcas.toArray();


	}

	public static Marca obtenerMarca(int idMarca) {
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		Marca retorno = new Marca();
		ResultSet rs = null;

		try {
			String Consulta = "select * from marca where id_marca = " + idMarca;
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno.setIdMarca(idMarca);
				retorno.setNombre(rs.getString("nombre_marca"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}
}
