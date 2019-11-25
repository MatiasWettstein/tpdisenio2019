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
			
			
			
			DAODomicilioRiesgo.guardarDomRiesgo(poliza.getDomicilio_riesgo());
			DAOPremio.guardarPremio(poliza.getPremio());
			DAOVehiculo.guardarVehiculo(poliza.getVehiculo());
			DAOSiniestros.guardarSiniestro(poliza.getSiniestro());
			DAOTipoCobertura.guardarTipo(poliza.getTipo_cobertura());
			
			PreparedStatement st = con.prepareStatement("INSERT INTO POLIZA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
			st.setInt(1, poliza.getNroPoliza()); //nro_poliza
			st.setInt(2, poliza.getKmPorAnio()); //km_por_anio
			st.setFloat(3, poliza.getSumaasegurada()); //suma_asegurada
			st.setString(4, poliza.getInicio_vigencia());//inicio_vigencia
			st.setString(5, poliza.getFin_vigencia());//fin__vigencia
			st.setString(6, poliza.getForma_pago().getNombre());//Forma_Pago
			st.setString(7, poliza.getEstado_poliza());//estado_poliza
			st.setInt(8, poliza.getPremio().getIdPremio());//FK id premio 
			st.setInt(9, poliza.getVehiculo().getId_vehiculo());//FK id vehiculo
			st.setInt(10, poliza.getSiniestro().getId_siniestro());// FK id siniestros
			st.setInt(11, poliza.getTipo_cobertura().getId_cobertura());// FK id tipo_cobertura
			st.setInt(12, poliza.getDomicilio_riesgo().getId_domicilioR());//FK id domicilio_riesgo
			st.setString(13, poliza.getCliente().getNroCliente());//FK id cliente
			st.executeUpdate();
			st.close();
				
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

	public static Boolean cargarPolizaTieneMDS(Poliza p) {
		Boolean retorno = false;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		
		Boolean aux_alarma = p.getSeguridad().getAlarma().isPosee(); 
		Boolean aux_dispR = p.getSeguridad().getRastreo().isPosee(); 
		Boolean aux_garage = p.getSeguridad().getGarage().isPosee(); 
		Boolean aux_tuercas = p.getSeguridad().getTuercas().isPosee(); 

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
			 int nro_poliza 1
			 int id_mds
			 double porcentaje actual;*/
			
			if (aux_alarma) { // SI TIENE ALARMA
				PreparedStatement st_alarma = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_alarma.setInt(1, p.getNroPoliza()); //nro_poliza
				st_alarma.setInt(2, DAOMedidasSeguridad.obtenerIDAlarma());
				st_alarma.setFloat(3, (float) DAOMedidasSeguridad.obtenerPorcentajeAlarma());
				st_alarma.executeUpdate();
				st_alarma.close();
			}
			if (aux_garage) { //si tiene garage
				PreparedStatement st_garage = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_garage.setInt(1, p.getNroPoliza()); //nro_poliza
				st_garage.setInt(2, DAOMedidasSeguridad.obtenerIDGarage());
				st_garage.setFloat(3, (float) DAOMedidasSeguridad.obtenerPorcentajeGarage());
				st_garage.executeUpdate();
				st_garage.close();
			}
			if (aux_dispR) { //Si tiene disp R 
				PreparedStatement st_dispR = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_dispR.setInt(1, p.getNroPoliza()); //nro_poliza
				st_dispR.setInt(2, DAOMedidasSeguridad.obtenerIDDisp());
				st_dispR.setFloat(3, (float) DAOMedidasSeguridad.obtenerPorcentajeDisp());
				st_dispR.executeUpdate();
				st_dispR.close();
			}
			if (aux_tuercas) { //si tiene tueras
				PreparedStatement st_tuercas = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_tuercas.setInt(1, p.getNroPoliza()); //nro_poliza
				st_tuercas.setInt(2, DAOMedidasSeguridad.obtenerIDTuerca());
				st_tuercas.setFloat(3, (float) DAOMedidasSeguridad.obtenerPorcentajeTuerca());
				st_tuercas.executeUpdate();
				st_tuercas.close();
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

	




}
