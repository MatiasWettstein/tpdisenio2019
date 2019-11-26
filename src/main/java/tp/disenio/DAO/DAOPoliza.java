package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.clases.Cliente;
import tp.disenio.clases.Cobertura;
import tp.disenio.clases.Cuota;
import tp.disenio.clases.Direccion;
import tp.disenio.clases.DomicilioRiesgo;
import tp.disenio.clases.Localidad;
import tp.disenio.clases.Mensual;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.Premio;
import tp.disenio.clases.Provincia;
import tp.disenio.clases.Semestral;
import tp.disenio.clases.Siniestros;
import tp.disenio.clases.Vehiculo;
import tp.disenio.gestores.GestorCliente;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPoliza;

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
			
			
			GestorPoliza gp = GestorPoliza.getInstance();
			gp.guardarDomRiesgo(poliza.getDomicilio_riesgo());
			gp.guardarPremio(poliza.getPremio());
			gp.guardarVehiculo(poliza.getVehiculo());
			gp.guardarSiniestro(poliza.getSiniestro());
			gp.guardarTipo(poliza.getTipo_cobertura());
			
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
			GestorPoliza gp = GestorPoliza.getInstance();
			if (aux_alarma) { // SI TIENE ALARMA
				PreparedStatement st_alarma = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_alarma.setInt(1, p.getNroPoliza()); //nro_poliza
				st_alarma.setInt(2, gp.obtenerIDAlarma());
				st_alarma.setFloat(3, (float) gp.obtenerPorcentajeAlarma());
				st_alarma.executeUpdate();
				st_alarma.close();
			}
			if (aux_garage) { //si tiene garage
				PreparedStatement st_garage = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_garage.setInt(1, p.getNroPoliza()); //nro_poliza
				st_garage.setInt(2, gp.obtenerIDGarage());
				st_garage.setFloat(3, (float) gp.obtenerPorcentajeGarage());
				st_garage.executeUpdate();
				st_garage.close();
			}
			if (aux_dispR) { //Si tiene disp R 
				PreparedStatement st_dispR = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_dispR.setInt(1, p.getNroPoliza()); //nro_poliza
				st_dispR.setInt(2, gp.obtenerIDDisp());
				st_dispR.setFloat(3, (float) gp.obtenerPorcentajeDisp());
				st_dispR.executeUpdate();
				st_dispR.close();
			}
			if (aux_tuercas) { //si tiene tueras
				PreparedStatement st_tuercas = con.prepareStatement("INSERT INTO POLIZA_TIENE_MDS VALUES (?, ?, ?)");
				st_tuercas.setInt(1, p.getNroPoliza()); //nro_poliza
				st_tuercas.setInt(2, gp.obtenerIDTuerca());
				st_tuercas.setFloat(3, (float) gp.obtenerPorcentajeTuerca());
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

	public static Poliza buscarPoliza(String nroP) {
		Poliza retorno = new Poliza();
		ResultSet rs = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = null;
		int nroPoliza = Integer.parseInt(nroP);
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
			String Consulta = "select * from poliza where nro_poliza = " + nroPoliza;


			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();
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
			
			
			
			while(rs.next()) {
				GestorPoliza gp = GestorPoliza.getInstance();
				GestorCliente gc = GestorCliente.getInstance();
				
				retorno.setNroPoliza(rs.getInt("nro_poliza"));
				retorno.setKmPorAnio(rs.getInt("km_por_anio"));
				retorno.setSumaasegurada( (float) rs.getDouble("suma_asegurada"));
				retorno.setInicio_vigencia(rs.getString("inicio_vigencia"));
				retorno.setFin_vigencia(rs.getString("fin_vigencia"));
				if (rs.getString("forma_pago") == "MENSUAL") {
					Mensual aux_mens = new Mensual();
					aux_mens.setNombre(rs.getString("forma_pago"));
					aux_mens.setCuotas(gp.recuperarListaCuotas(rs.getInt("nro_poliza")));
					//no hago el set de monto total
				}
				else {
					Semestral aux_sem = new Semestral();
					Cuota aux_cuota = new Cuota();
					aux_cuota=gp.recupearCuota(rs.getInt("nro_poliza"));
					aux_sem.setCuota1(aux_cuota);
					aux_sem.setNombre(rs.getString("forma_pago"));
					aux_sem.setFecha_Vencimiento(aux_cuota.getFecha_vencimiento());
					aux_sem.setMontoTotal(aux_cuota.getMonto());
				}
				
				retorno.setEstado_poliza(rs.getString("estado_poliza"));
				//PREMIO
				Premio aux_premio = new Premio();
				aux_premio = gp.recuperarPremio(rs.getInt("premio"));
				retorno.setPremio(aux_premio);
				
				//VEHICULO
				Vehiculo aux_veh = new Vehiculo();
				aux_veh = gp.recuperarVehiculo(rs.getInt("vehiculo"));
				retorno.setVehiculo(aux_veh);
				
				//SINIESTROS
				Siniestros aux_siniestro = new Siniestros();
				aux_siniestro = gp.recuperarSiniestro(rs.getInt("siniestros"));
				retorno.setSiniestro(aux_siniestro);
			
				//TIPO DE COBERTURA
				Cobertura aux_cob = new Cobertura();
				aux_cob = gp.recuperarCobertura(rs.getInt("siniestros"));
				retorno.setTipo_cobertura(aux_cob);
				
				
				//DOMICILIO RIESGO
				DomicilioRiesgo aux_dom = new DomicilioRiesgo();
				aux_dom = gp.recuperarDomicilioRiesgo(rs.getInt("domicilio_riesgo"));
				retorno.setDomicilio_riesgo(aux_dom);
				
				//CLIENTE 
				Cliente aux_cliente = new Cliente();
				aux_cliente = gc.recuperarCliente(rs.getNString("cliente"));
				retorno.setCliente(aux_cliente);
				 
				falta terminar
				
				//MEDIDAS SEGURIDAD
				//DESCUENTOS 
				//HIJOS
				
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
