package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Descuentos;
import tp.disenio.clases.Hijo;
import tp.disenio.clases.Poliza;
import tp.disenio.gestores.GestorDB;

public class DAOHijo {

	public static Boolean cargarHijos(Poliza p){
		
		
		Boolean retorno = false; 
		
		
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

		ArrayList<Hijo> hijos = p.getHijos_declarados();
		int ultimoID = DAOHijo.recupearUltimoNID();
		ultimoID += 1;
		
		for(Hijo h : hijos) {

			try {
				PreparedStatement st = con.prepareStatement("INSERT INTO hijo_declarado VALUES (?, ?, ?, ?)");
				st.setInt(1, ultimoID); //idHijo
				st.setString(2, h.getFechaNac()); //fechaNac
				st.setString(3, h.getEstadoCivil()); //EstadoCivil
				st.setString(4, h.getSexo()); //sexo 
				st.setInt(4, p.getNroPoliza());//nro_poliza
				st.setInt(5, p.getPoliza_modificada().getId_polizaMod());//poliza_mod 

				st.executeUpdate();
				st.close();

				ultimoID += 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			retorno = true;
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
			String Consulta = "select max(id_hijo) from hijo_declarado";
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
	
public static ArrayList<Hijo> recuperarHijos(int nroPoliza) {
	ArrayList<Hijo> retorno = new ArrayList<>();
	
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
		
		String Consulta = "select * from hijo_declarado where nro_poliza = " + nroPoliza;
		PreparedStatement st_pagoAdelantado = con.prepareStatement(Consulta);
		rs = st_pagoAdelantado.executeQuery();

		while(rs.next()) {
			Hijo hijo = new Hijo();
			hijo.setId_hijo(rs.getInt("id_hijo"));
			hijo.setFechaNac(rs.getString("fecha_nac"));
			hijo.setEstadoCivil(rs.getString("estado_civil"));
			hijo.setSexo(rs.getString("sexo"));
			
			retorno.add(hijo);
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
