package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Caracteristicas;
import tp.disenio.clases.Cuota;
import tp.disenio.clases.Mensual;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.Premio;
import tp.disenio.clases.Semestral;
import tp.disenio.gestores.GestorDB;

public class DAOCaracteristicas {

	public static Boolean cargarCaracteristicas(Poliza p) {
		Boolean retorno = false;
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
			/*
			 int id_caracteristicas 1
			 String nombre 2
			 double porcentaje 3
			 int nro_poliza4
			 */
			int id_carac = DAOCaracteristicas.recupearUltimoNID();
			id_carac +=1;
			
			if (p.getHijos_declarados().isEmpty()) { //si no tiene hijos cargo solo los KM 
				PreparedStatement st_km = con.prepareStatement("INSERT INTO caracteristicas VALUES (?, ?, ?, ?)");
				st_km.setInt(1, id_carac); //id_caracteristicas
				st_km.setString(2, "PORCENTAJE KM"); //nombre
				st_km.setFloat(3, (float) p.getCaracteristicas().getPorcentajeKm());//porcentaje
				st_km.setInt(5, p.getNroPoliza());//nro poliza
				st_km.executeUpdate();
				st_km.close();
			}
			else { //sino cargo los dos 	
				PreparedStatement st_hijo = con.prepareStatement("INSERT INTO caracteristicas VALUES (?, ?, ?, ?)");
				st_hijo.setInt(1, id_carac); //id_caracteristicas
				st_hijo.setString(2, "PORCENTAJE HIJO"); //nombre
				st_hijo.setFloat(3, (float) p.getCaracteristicas().getPorcentajeHijo());//porcentaje
				st_hijo.setInt(5, p.getNroPoliza());//nro poliza
				st_hijo.executeUpdate();
				st_hijo.close();
				//ACA TENGO QUE AUMENTAR EL ID porque no esta de la misma forma que en la clase 
				PreparedStatement st_km = con.prepareStatement("INSERT INTO caracteristicas VALUES (?, ?, ?, ?)");
				id_carac +=1;
				st_km.setInt(1, id_carac); //id_caracteristicas
				st_km.setString(2, "PORCENTAJE KM"); //nombre
				st_km.setFloat(3, (float) p.getCaracteristicas().getPorcentajeKm());//porcentaje
				st_km.setInt(5, p.getNroPoliza());//nro poliza
				st_km.executeUpdate();
				st_km.close();
				
			}
			
			retorno = true;
			
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

		return retorno;
		
	}
	
public static int recupearUltimoNID() {
		
		int retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		ResultSet rs = null;


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
			String Consulta = "select max(id_caracteristicas) from caracteristicas";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getInt("max");
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

public static double obtenerPorcentajeHijo() {
	double retorno = 0;
	GestorDB gdb = GestorDB.getInstance();
	Connection con = null;
	ResultSet rs = null;


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
		String Consulta = "select valor from porcentajes where nombre = 'PORCENTAJE HIJO'";
		PreparedStatement st = con.prepareStatement(Consulta);
		rs = st.executeQuery();


		while(rs.next()) {
			retorno = rs.getInt("valor");
		}

	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return retorno;
}

public static double obtenerPorcentajeKM() {
	double retorno = 0;
	GestorDB gdb = GestorDB.getInstance();
	Connection con = null;
	ResultSet rs = null;


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
		String Consulta = "select valor from porcentajes where nombre = 'PORCENTAJE KM'";
		PreparedStatement st = con.prepareStatement(Consulta);
		rs = st.executeQuery();


		while(rs.next()) {
			retorno = rs.getInt("valor");
		}

	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return retorno;
}

public static Caracteristicas recuperarCaracteristicas (int nroPoliza) {
	Caracteristicas retorno = new Caracteristicas();
	ResultSet rs_km = null;
	ResultSet rs_hijo = null;
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

		String Consulta_km = "select * from caracteristicas where nro_poliza = " + nroPoliza + " and nombre = 'PORCENTAJE KM'";
		PreparedStatement st_km = con.prepareStatement(Consulta_km);
		rs_km = st_km.executeQuery();
		while(rs_km.next()) {
			retorno.setPorcentajeKm(rs_km.getDouble("porcentaje"));
		}
		
		String Consulta_hijo = "select * from caracteristicas where nro_poliza = " + nroPoliza + " and nombre = 'PORCENTAJE HIJO'";
		PreparedStatement st_hijo = con.prepareStatement(Consulta_hijo);
		rs_hijo = st_hijo.executeQuery();
		while(rs_hijo.next()) {
			retorno.setPorcentajeKm(rs_hijo.getDouble("porcentaje"));
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
	return retorno;
}
}
