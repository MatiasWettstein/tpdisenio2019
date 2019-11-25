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

	

	public static Boolean cargarPoliza(Poliza poliza) {

		boolean retorno = false;
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
			
			ArrayList<Integer> idhijos = DAOHijo.cargarHijos(poliza.getHijos_declarados());
			int id_domRiesgo = DAODomicilioRiesgo.guardarDomRiesgo(poliza.getDomicilio_riesgo());
			int id_premio = DAOPremio.guardarPremio(poliza.getPremio());
			int id_vehiculo = DAOVehiculo.guardarVehiculo(poliza.getVehiculo());
			int id_siniestro = DAOSiniestros.guardarSiniestro(poliza.getSiniestro());
			int id_tipoC = DAOTipoCobertura.guardarTipo(poliza.getTipo_cobertura());
			
			PreparedStatement st = con.prepareStatement("INSERT INTO POLIZA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
			st.setInt(1, poliza.getNroPoliza()); //nro_poliza
			st.setInt(2, poliza.getKmPorAnio()); //km_por_anio
			st.setFloat(3, poliza.getSumaasegurada()); //suma_asegurada
			st.setString(4, poliza.getInicio_vigencia());//inicio_vigencia
			st.setString(5, poliza.getFin_vigencia());//fin__vigencia
			st.setString(6, poliza.getForma_pago().getNombre());//Forma_Pago
			st.setString(7, poliza.getEstado_poliza());//estado_poliza
			st.setInt(8, id_premio);//FK id premio 
			st.setInt(9, id_vehiculo);//FK id vehiculo
			st.setInt(10, id_siniestro);// FK id siniestros
			st.setInt(11, id_tipoC);// FK id tipo_cobertura
			st.setInt(12, id_domRiesgo);//FK id domicilio_riesgo
			st.setString(13, poliza.getCliente().getNroCliente());//FK id cliente
			st.executeUpdate();
			st.close();
			
			/*
			 * cargar tablas: 
			 * CUOTA 
			 * POLIZA_POSEE_HIJO_DECLARADO
			 * POLIZA_TIENE_MDS
			 * 
			 */
			
			
			retorno = true;
			
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
			 FK id cliente 13 */
		
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
