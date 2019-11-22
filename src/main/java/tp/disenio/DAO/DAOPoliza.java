package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.clases.Poliza;
import tp.disenio.gestores.GestorDB;

public class DAOPoliza {

	public static int recuperarUltimoNroPolizaCliente(ClienteDTO c) {
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
		//tengo que buscar el último numero de poliza asociado al cliente
		try {
			String nro_cliente = c.getNroCliente();
			String Consulta = "select max(nro_poliza) from poliza where cliente = '" + nro_cliente + "'" ;
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


	public static Boolean cargarPoliza(Poliza poliza) {


		ArrayList<Integer> idhijos = DAOHijo.cargarHijos(poliza.getHijos_declarados());

		return null;
	}
	/*
	 int nro_poliza 1
	 int km_por_anio 2
	 double suma_asegurada 3
	 String inicio_vigencia 4
	 String fin__vigencia 5
	 String Forma_Pago 6
	 String estado_poliza 7
	 FK id premio 8
	 FK id vehiculo 9
	 FK id siniestros 10
	 FK id tipo_cobertura 11
	 FK id domicilio_riesgo 12
	 FK id cliente 13
	 */




}
