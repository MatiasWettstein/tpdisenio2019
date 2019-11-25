package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Modelo;
import tp.disenio.gestores.GestorDB;

public class DAOSuperIntendencia {

	public static Float obtenerSumaAsegurada(Modelo modelo, String anio){

	float sumaAsegurada=0;
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

		int idModelo = modelo.getIdModelo();
		int idMarca = modelo.getMarca().getIdMarca();
		
		String consulta = "select suma_asegurada from superintendencia_seguros where modelo = " + idModelo + " and marca = " + idMarca + " and anio = " + anio;
				
		PreparedStatement st = con.prepareStatement(consulta);
		rs = st.executeQuery();

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
	
	return sumaAsegurada;
}
	
}