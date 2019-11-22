package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Premio;
import tp.disenio.clases.Vehiculo;
import tp.disenio.gestores.GestorDB;

public class DAOVehiculo {
	
public static int  guardarVehiculo (Vehiculo v) {
		
		int id_ve = 0;
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
			id_ve = DAOVehiculo.recupearUltimoNID(v);
			id_ve +=1;
			PreparedStatement st = con.prepareStatement("INSERT INTO PREMIO VALUES (?, ?, ?, ?, ?, ?, ?)");
			st.setInt(1, id_ve); //id_vehiculo 1 
			st.setString(2, v.getPatente()); //patente 2 varchar
			st.setString(3, v.getMotor()); // motor 3 
			st.setString(4, v.getChasis()); //chasis 4
			st.setInt(5, v.getAnio()); //anio 5 numeric 
			st.setInt(6, v.getModelo().getIdModelo()); //FK modelo 
			st.setFloat(7, v.getPorcentaje()); //porcentaje_actual 
		
			st.executeUpdate();
			st.close();

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

		return id_ve; 

	}

	
private static int recupearUltimoNID(Vehiculo v) {
		
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
			String Consulta = "select max(id_vehiculo) from vehiculo";
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
	
	

}
