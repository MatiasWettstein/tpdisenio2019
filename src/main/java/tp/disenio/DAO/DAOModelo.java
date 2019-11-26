package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Anio;
import tp.disenio.clases.Marca;
import tp.disenio.clases.Modelo;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorParametros;

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
				mod.setIdModelo(rs.getInt("id_modelo"));
				mod.setNombre(rs.getString("nombre"));
				mod.setPorcentaje(rs.getFloat(2));
				mod.setMarca(marca);
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

	public static Modelo obtenerModelo(int idModelo, Connection con) {
		Modelo retorno = new Modelo();
		ResultSet rs = null;

		try {

			GestorParametros gpm = GestorParametros.getInstance();
			String Consulta = "select * from modelo where id_modelo = " + idModelo;
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			/*
			 * id_modelo 1
			 * porcentaje 2 double
			 * nombre 3 string
			 * marca 4 int fk
			 * anio 5 int fk
			 */


			while(rs.next()) {
				retorno.setIdModelo(idModelo);
				retorno.setPorcentaje((float) rs.getDouble("porcentaje"));
				retorno.setNombre(rs.getString("nombre"));
				Marca aux_marca = new Marca();
				aux_marca = gpm.recuperarMarca(rs.getInt("marca"), con);
				retorno.setMarca(aux_marca);
				Anio aux_anio = new Anio();
				aux_anio = gpm.recuperarAnio(retorno);
				retorno.setAnio(aux_anio);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;

	}
}
