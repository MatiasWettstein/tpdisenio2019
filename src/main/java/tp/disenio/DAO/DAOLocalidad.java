package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Localidad;
import tp.disenio.gestores.GestorDB;

public class DAOLocalidad {

	 //hay que pasarle el id de la provincia por parámetro para buscarlo 
	public static Object[] listaLocalidad(String nombreProvincia){

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
			
			String consulta1= "Select id_provincia from provincia where nombre = ";
			consulta1 += nombreProvincia;
			PreparedStatement st1 = con.prepareStatement(consulta1);
			provincia = st1.executeQuery();
			String id_provincia = "";
			
			if (provincia.next()) id_provincia = provincia.getString(1);
			
			System.out.println(id_provincia);
			
			
			String consulta2 = "select id_localidad, nombre, porcentaje, codigo_postal from localidad where provincia = " ;
			consulta2 += id_provincia;
			
			PreparedStatement st2 = con.prepareStatement(consulta2);
			rs = st2.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString(2) + " " + rs.getInt("id_provincia"));
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
