package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Hijo;
import tp.disenio.gestores.GestorDB;

public class DAOHijo {

	public static ArrayList<Integer> cargarHijos(ArrayList<Hijo> hijos){
		ArrayList<Integer> retorno = new ArrayList<>();
		int maxid = 0;
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
				maxid = rs.getInt("max");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Hijo h : hijos) {

			try {
				PreparedStatement st = con.prepareStatement("INSERT INTO hijo_declarado VALUES (?, ?, ?, ?)");
				st.setInt(1, maxid+1);
				st.setString(2, h.getFechaNac());
				st.setString(3, h.getEstadoCivil());
				st.setString(4, h.getSexo());

				st.executeUpdate();
				st.close();

				retorno.add(maxid+1);
				maxid=maxid+1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



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
