package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Marca;
import tp.disenio.clases.Modelo;
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
			System.out.println("ID MODELO" + idModelo + "\n");
			System.out.println("ID ANIO" + idAnio);
			
			
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
		
		if (sumaAsegurada == 0) System.out.println("NO FUNCIONA");
		
		return sumaAsegurada; 


	}
	
	
}
