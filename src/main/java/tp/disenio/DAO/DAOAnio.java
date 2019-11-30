package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Anio;
import tp.disenio.clases.Modelo;
import tp.disenio.gestores.GestorDB;

public class DAOAnio {

	public static Object[] listaAnios(Modelo modelo){

		ArrayList<Integer> anios= new ArrayList<>();
		ResultSet rs = null;
		ResultSet anio = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		int inicio = 0 ;
		int fin = 0;

		
		try {

			int idModelo = modelo.getIdModelo();
			String consulta1 = "select * from modelo where id_modelo = " + idModelo;

			PreparedStatement st1 = con.prepareStatement(consulta1);
			anio = st1.executeQuery();
			int idAnio=0;

			while (anio.next()) {
				idAnio = anio.getInt("anio");
			}


			String consulta2 = "select inicio_fabricacion, fin_fabricacion from anio where id_anio = " + idAnio;
			PreparedStatement st2 = con.prepareStatement(consulta2);
			rs = st2.executeQuery();

			while(rs.next()) {
				inicio = rs.getInt("inicio_fabricacion");
				fin = rs.getInt("fin_fabricacion");
			}

			anios.add(inicio);

			if (fin - inicio == 1) {
				anios.add(fin);
			}
			else {
				while (inicio < fin) {
					inicio++;
					anios.add(inicio);
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return anios.toArray();
	}


	public static Anio obtenerAnioModelo(Modelo modelo) {

		Anio retorno = new Anio();
		ResultSet rs = null;
		ResultSet anio = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

	
		try {

			int idModelo = modelo.getIdModelo();
			int idAnio=0;

			String consulta1 = "select anio from modelo where id_modelo = " + idModelo;
			PreparedStatement st1 = con.prepareStatement(consulta1);
			anio = st1.executeQuery();

			while (anio.next()) {
				idAnio = anio.getInt("anio");
			}


			String consulta2 = "select * from anio where id_anio = " + idAnio;
			PreparedStatement st2 = con.prepareStatement(consulta2);
			rs = st2.executeQuery();

			while(rs.next()) {
				retorno.setId_anio(rs.getInt("id_anio"));
				retorno.setInicioFabricacion(rs.getInt("inicio_fabricacion"));
				retorno.setFinFabricacion(rs.getInt("fin_fabricacion"));;
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}

}
