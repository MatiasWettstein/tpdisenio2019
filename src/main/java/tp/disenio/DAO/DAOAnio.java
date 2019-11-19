package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Marca;
import tp.disenio.clases.Modelo;
import tp.disenio.clases.Provincia;
import tp.disenio.gestores.GestorDB;

public class DAOAnio {

	public static Object obtenerSumaAsegurada(Modelo modelo){

		float sumaAsegurada=0;
		ResultSet rs = null;
		ResultSet anio = null;
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
			
			int idModelo = modelo.getIdModelo();
			String consulta1 = "select * from modelo where id_modelo = " + idModelo;
			
			PreparedStatement st1 = con.prepareStatement(consulta1);
			anio = st1.executeQuery();
			int idAnio=0;
			
			while (anio.next()) {
				idAnio = anio.getInt("anio");
			}
			
			String consulta2 = "select suma_asegurada from anio where id_anio = " + idAnio;
			PreparedStatement st2 = con.prepareStatement(consulta2);
			rs = st2.executeQuery();
			
			while(rs.next()) {
				sumaAsegurada = rs.getFloat("suma_asegurada");
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
		
		sumaAsegurada = sumaAsegurada*1000;
		
		return sumaAsegurada; 


	}
	
	
	public static Object[] listaAnios(Modelo modelo){
		
		ArrayList<Integer> anios= new ArrayList<>();
		ResultSet rs = null;
		ResultSet anio = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		int inicio = 0 ;
		int fin = 0; 

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


		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anios.toArray();
	}
	
	
}
