package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.disenio.clases.Direccion;
import tp.disenio.clases.Localidad;
import tp.disenio.gestores.GestorParametros;

public class DAODireccion {

	public static int  guardarDireccion (Direccion d, Connection con) {

		int numeroDire =0;
		int idDire = 0;
		ResultSet rsDire = null;


		try {

			String Consulta = "select max(id_direccion) from direccion";
			PreparedStatement stDire = con.prepareStatement(Consulta);
			rsDire = stDire.executeQuery();

			while(rsDire.next()) {
				idDire = rsDire.getInt("max");
			}
			idDire++;

			numeroDire = Integer.parseInt(d.getNumero());

			//id_direccion 1
			//calle, 2
			//numero 3
			//dpto, 4
			//localidad 5
			//piso 6

			PreparedStatement st = con.prepareStatement("INSERT INTO DIRECCION VALUES (?, ?, ?, ?, ?, ?)");
			st.setInt(1, idDire);
			st.setString(2, d.getCalle());
			st.setInt(3, numeroDire);
			if (d.getDpto() != null) st.setString(4, d.getDpto());
			else st.setNull(4, java.sql.Types.VARCHAR);
			st.setInt(5, d.getLocalidad().getId_localidad());
			if (d.getPiso() != 0) st.setInt(6, d.getPiso());
			else st.setNull(6, java.sql.Types.NUMERIC);



			st.executeUpdate();
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return idDire;

	}

	public static Direccion recuperarDireccion (int idDire, Connection con) {
		Direccion retorno = new Direccion();
		ResultSet rs = null;

		try {
			String Consulta = "select * from direccion where id_direccion = " + idDire;


			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
			/*
			 * id_direccion 1
			 * calle 2 string
			 * numero 3 int
			 * dpto 4 string
			 * localidad 5 	fk
			 * piso 6 int
			 */

			GestorParametros gpm = GestorParametros.getInstance();
			while(rs.next()) {
				retorno.setCalle(rs.getString("calle"));
				retorno.setNumero(Integer.toString(rs.getInt("numero")));
				retorno.setDpto(rs.getString("dpto"));
				Localidad aux_loc = new Localidad();
				aux_loc = gpm.obtenerLocalidad(rs.getInt("localidad"));
				retorno.setLocalidad(aux_loc);
				retorno.setPiso(rs.getInt("piso"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}


}
