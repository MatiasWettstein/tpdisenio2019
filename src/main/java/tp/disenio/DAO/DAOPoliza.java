package tp.disenio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tp.disenio.clases.Caracteristicas;
import tp.disenio.clases.Cliente;
import tp.disenio.clases.Cobertura;
import tp.disenio.clases.Cuota;
import tp.disenio.clases.Descuentos;
import tp.disenio.clases.DomicilioRiesgo;
import tp.disenio.clases.Hijo;
import tp.disenio.clases.MedidasSeguridad;
import tp.disenio.clases.Mensual;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.PolizaModificada;
import tp.disenio.clases.Premio;
import tp.disenio.clases.Semestral;
import tp.disenio.clases.Siniestros;
import tp.disenio.clases.Vehiculo;
import tp.disenio.gestores.GestorCliente;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPoliza;

public class DAOPoliza {



	public static Boolean cargarPoliza(Poliza poliza) {

		boolean retorno = false;
		boolean flag_poliza=false;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

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

			st.setLong(1, poliza.getNroPoliza()); //nro_poliza
			st.setInt(2, poliza.getKmPorAnio()); //km_por_anio
			st.setDouble(3, poliza.getSumaasegurada()); //suma_asegurada
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

			flag_poliza = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean flag_mds = false;
		boolean flag_hijo = false;
		boolean flag_cuota = false;
		boolean flag_carac = false;
		boolean flag_desc= false;
		if(flag_poliza) { //si pude cargar la poliza

			/*cargar tablas:
			 * CUOTA - listo
			 * CATACTERISTICAS - listo
			 * DESCUENTOS - LISTO
			 * POLIZA TIENE MDS - listo
			 * AGREGAR HIJO DECLARADO - listo
			 */
			flag_mds = DAOMedidasSeguridad.cargarPolizaTieneMDS(poliza);
			flag_hijo = DAOHijo.cargarHijos(poliza);
			flag_cuota = DAOCuota.cargarCuota(poliza);
			flag_carac = DAOCaracteristicas.cargarCaracteristicas(poliza);
			flag_desc= DAODescuentos.cargarDescuentos(poliza);


			if (flag_mds && flag_cuota && flag_carac && flag_desc) {
				retorno=true;
			}
		}
		else retorno = false;



		return retorno;
	}

	public static Poliza buscarPoliza(String nroP) {

		Poliza retorno = new Poliza();
		ResultSet rs = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		String Consulta;


		try {
			long nroPoliza = Long.parseLong(nroP);
			Consulta = "select * from poliza where nro_poliza = " + nroPoliza;
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
				retorno.setNroPoliza(rs.getLong("nro_poliza"));
				retorno.setKmPorAnio(rs.getInt("km_por_anio"));
				retorno.setSumaasegurada( (float) rs.getDouble("suma_asegurada"));
				retorno.setInicio_vigencia(rs.getString("inicio_vigencia"));
				retorno.setFin_vigencia(rs.getString("fin_vigencia"));
				if (rs.getString("forma_pago").equals("MENSUAL")) {
					Mensual aux_mens = new Mensual();
					aux_mens.setNombre(rs.getString("forma_pago"));
					aux_mens.setCuotas(gp.recuperarListaCuotas(rs.getLong("nro_poliza")));
					retorno.setForma_pago(aux_mens);
					//no hago el set de monto total
				}
				else {
					Semestral aux_sem = new Semestral();
					Cuota aux_cuota = new Cuota();
					aux_cuota=gp.recupearCuota(rs.getLong("nro_poliza"));
					aux_sem.setCuota1(aux_cuota);
					aux_sem.setNombre(rs.getString("forma_pago"));
					aux_sem.setFecha_Vencimiento(aux_cuota.getFecha_vencimiento());
					aux_sem.setMontoTotal(aux_cuota.getMonto());
					retorno.setForma_pago(aux_sem);
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
				aux_cliente = gc.recuperarCliente(rs.getString("cliente")); ///////
				retorno.setCliente(aux_cliente);


				//MEDIDAS SEGURIDAD
				MedidasSeguridad aux_medS = new MedidasSeguridad();
				aux_medS = gp.recuperarMedidasSeguridad(rs.getLong("nro_poliza"));
				retorno.setSeguridad(aux_medS);

				//DESCUENTOS
				Descuentos aux_desc = new Descuentos();
				aux_desc = gp.recuperarDescuentos(rs.getLong("nro_poliza"));
				retorno.setDescuento(aux_desc);

				//HIJOS
				ArrayList<Hijo> aux_hijos = new ArrayList<>();
				aux_hijos = gp.recuperarHijos(rs.getLong("nro_poliza"));
				retorno.setHijos_declarados(aux_hijos);

				//CARACTERISTICAS
				Caracteristicas aux_car = new Caracteristicas();
				aux_car = gp.recuperarCaracteristicas(rs.getLong("nro_poliza"));
				retorno.setCaracteristicas(aux_car);

				retorno.setPoliza_modificada(new PolizaModificada());
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

	public static long recupearUltimoNID() {

		long retorno = 0;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;
		ResultSet rs = null;



		try {
			String Consulta = "select max(nro_poliza) from poliza";
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();


			while(rs.next()) {
				retorno = rs.getLong("max");
			}
			st.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	public static Boolean polizaExiste(String nroP) {

		Boolean retorno = false;
		ResultSet rs = null;
		GestorDB gdb = GestorDB.getInstance();
		Connection con = gdb.conec;

		String Consulta;


		try {
			long nroPoliza = Long.parseLong(nroP);
			Consulta = "select * from poliza where nro_poliza = " + nroPoliza;
			PreparedStatement st = con.prepareStatement(Consulta);
			rs = st.executeQuery();

			if(rs.next()==true) { //si la encuentra retorna true
				retorno = true;
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

}


