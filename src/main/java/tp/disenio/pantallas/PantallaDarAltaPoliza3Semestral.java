package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.DomicilioRiesgoDTO;
import tp.disenio.DTO.HijoDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.DTO.PremioDTO;
import tp.disenio.DTO.VehiculoDTO;
import tp.disenio.gestores.GestorCliente;
import tp.disenio.gestores.GestorPantallas;
import tp.disenio.gestores.GestorPoliza;

public class PantallaDarAltaPoliza3Semestral {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start(ClienteDTO c, PolizaDTO p, VehiculoDTO v,ArrayList<HijoDTO> listahijos, DomicilioRiesgoDTO dom) {

		// --------------- MARCO -------------------------------------------
		final Marco marco1 = new Marco(1333,730,"DAR DE ALTA POLIZA");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		// -----------------------------------------------------------------

		// ---------------- ETIQUETAS --------------------------------------
		JLabel lblTitularDelSeguro = new JLabel("Titular del seguro");
		lblTitularDelSeguro.setFont(new Font("Serif", Font.PLAIN, 18));
		lblTitularDelSeguro.setBounds(24, 37, 147, 33);
		marco1.getContentPane().add(lblTitularDelSeguro);

		JLabel lblDatosDelVehiculo = new JLabel("Datos del vehiculo");
		lblDatosDelVehiculo.setFont(new Font("Serif", Font.BOLD, 20));
		lblDatosDelVehiculo.setBounds(24, 88, 175, 33);
		marco1.getContentPane().add(lblDatosDelVehiculo);

		JLabel lblVigencia = new JLabel("Vigencia");
		lblVigencia.setFont(new Font("Serif", Font.BOLD, 20));
		lblVigencia.setBounds(24, 186, 147, 33);
		marco1.getContentPane().add(lblVigencia);

		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio");
		lblFechaDeInicio.setFont(new Font("Serif", Font.PLAIN, 18));
		lblFechaDeInicio.setBounds(24, 230, 126, 22);
		marco1.getContentPane().add(lblFechaDeInicio);

		JLabel lblFechaDeFin = new JLabel("Fecha de fin");
		lblFechaDeFin.setFont(new Font("Serif", Font.PLAIN, 18));
		lblFechaDeFin.setBounds(431, 230, 120, 22);
		marco1.getContentPane().add(lblFechaDeFin);

		JLabel lblFormaDePago = new JLabel("Forma de pago");
		lblFormaDePago.setFont(new Font("Serif", Font.PLAIN, 18));
		lblFormaDePago.setBounds(24, 284, 131, 22);
		marco1.getContentPane().add(lblFormaDePago);

		JLabel lblSumaAsegurada = new JLabel("Suma asegurada");
		lblSumaAsegurada.setFont(new Font("Serif", Font.PLAIN, 18));
		lblSumaAsegurada.setBounds(431, 284, 139, 22);
		marco1.getContentPane().add(lblSumaAsegurada);

		JLabel lblPremio = new JLabel("Premio");
		lblPremio.setFont(new Font("Serif", Font.PLAIN, 18));
		lblPremio.setBounds(812, 286, 139, 22);
		marco1.getContentPane().add(lblPremio);

		JLabel lblImportesPorDescuentos = new JLabel("Importes por descuentos");
		lblImportesPorDescuentos.setFont(new Font("Serif", Font.BOLD, 20));
		lblImportesPorDescuentos.setBounds(24, 329, 249, 33);
		marco1.getContentPane().add(lblImportesPorDescuentos);

		JLabel lblUltimoDiaDe = new JLabel("Ultimo dia de pago");
		lblUltimoDiaDe.setFont(new Font("Serif", Font.PLAIN, 18));
		lblUltimoDiaDe.setBounds(24, 439, 175, 22);
		marco1.getContentPane().add(lblUltimoDiaDe);

		JLabel lblMontoTotalA = new JLabel("Monto total a pagar");
		lblMontoTotalA.setFont(new Font("Serif", Font.PLAIN, 18));
		lblMontoTotalA.setBounds(24, 495, 175, 22);
		marco1.getContentPane().add(lblMontoTotalA);

		// -----------------------------------------------------------------

		// ---------------- CAMPOS DE TEXTO --------------------------------
		JTextField textField = new JTextField();
		textField.setEditable(false);
		textField.setText(c.getApellido() + " " + c.getNombre());
		textField.setBounds(218, 46, 222, 20);
		marco1.getContentPane().add(textField);
		textField.setColumns(10);

		JTextField textField_fechInicio = new JTextField();
		textField_fechInicio.setEditable(false);
		textField_fechInicio.setText(p.getInicio_vigencia());
		textField_fechInicio.setBounds(160, 230, 196, 20);
		marco1.getContentPane().add(textField_fechInicio);
		textField_fechInicio.setColumns(10);

		JTextField textField_fechFin = new JTextField();
		textField_fechFin.setEditable(false);
		textField_fechFin.setText(p.getFin_vigencia());
		textField_fechFin.setBounds(570, 234, 196, 20);
		marco1.getContentPane().add(textField_fechFin);
		textField_fechFin.setColumns(10);

		JTextField textField_fPago = new JTextField();
		textField_fPago.setEditable(false);
		textField_fPago.setText(p.getForma_pago());
		textField_fPago.setBounds(160, 288, 196, 20);
		marco1.getContentPane().add(textField_fPago);
		textField_fPago.setColumns(10);

		JTextField textField_suma = new JTextField();
		textField_suma.setEditable(false);
		textField_suma.setText(String.valueOf(p.getSumaasegurada()*1000));
		textField_suma.setColumns(10);
		textField_suma.setBounds(570, 288, 196, 20);
		marco1.getContentPane().add(textField_suma);

		JTextField textField_Premio = new JTextField();
		textField_Premio.setEditable(false);
		GestorPoliza gp = GestorPoliza.getInstance();
		textField_Premio.setText(String.valueOf(gp.calcularPremio(gp.calcularPrima(p.getSumaasegurada()*1000), gp.calcularDerecho(p.getSumaasegurada()*1000))));
		textField_Premio.setColumns(10);
		textField_Premio.setBounds(915, 288, 196, 20);
		marco1.getContentPane().add(textField_Premio);

		PremioDTO premio = new PremioDTO();

		premio.setDerechoEmision(gp.calcularDerecho(p.getSumaasegurada()*1000));
		premio.setPrima(gp.calcularPrima(p.getSumaasegurada()*1000));
		premio.setMontoTotal(gp.calcularPremio(gp.calcularPrima(p.getSumaasegurada()*1000), gp.calcularDerecho(p.getSumaasegurada()*1000)));

		JTextField textField_2 = new JTextField();
		textField_2.setEditable(false);
		GestorCliente gc = GestorCliente.getInstance();
		int cant= gc.cantidadPoliza(c);
		double montototalapagar = premio.getMontoTotal()*gp.descuentos(cant)*0.9;
		textField_2.setText(String.valueOf(montototalapagar));
		textField_2.setColumns(10);
		textField_2.setBounds(224, 499, 196, 20);
		marco1.getContentPane().add(textField_2);




		DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal1 = Calendar.getInstance();
		try {
			cal1.setTime(dateFormat1.parse(p.getFin_vigencia()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cal1.add(Calendar.DAY_OF_MONTH,-1);
		String fechaultimopago = dateFormat1.format(cal1.getTime());
		JTextField textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText(fechaultimopago);
		textField_1.setColumns(10);
		textField_1.setBounds(224, 443, 196, 20);
		marco1.getContentPane().add(textField_1);

		// -----------------------------------------------------------------

		// ---------------- SCROLL PANE-------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 132, 1267, 43);
		marco1.getContentPane().add(scrollPane);

		JTable tableVehiculo = new JTable();
		tableVehiculo.setModel(new DefaultTableModel(
				new Object[][] {
					{v.getModelo().getMarca().getNombre(), v.getModelo().getNombre(), v.getMotor(), v.getChasis(), v.getPatente()},
				},
				new String[] {
						"Marca", "Modelo", "Motor", "Chasis", "Patente"
				}){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		});
		scrollPane.setViewportView(tableVehiculo);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 367, 898, 43);
		marco1.getContentPane().add(scrollPane_1);

		JTable tableDescuentos = new JTable();
		String desc = Double.toString(100-gp.descuentos(cant)*100)  + "%";
		tableDescuentos.setModel(new DefaultTableModel(
				new Object[][] {
					{desc, "10%"},
				},
				new String[] {
						"Descuento por m\u00E1s de una unidad asegurada", "Descuento por pago semestral"
				}
				){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		});
		scrollPane_1.setViewportView(tableDescuentos);

		// -----------------------------------------------------------------

		// --------------- BOTONES -----------------------------------------
		JButton btnSeleccionarOtroTipo = new JButton("SELECCIONAR OTRO TIPO DE COBERTURA");
		btnSeleccionarOtroTipo.setFont(new Font("Serif", Font.BOLD, 12));
		btnSeleccionarOtroTipo.setBounds(24, 657, 316, 33);
		marco1.getContentPane().add(btnSeleccionarOtroTipo);
		ActionListener tipocob = e -> {
			GestorPantallas.Pantalla2Alta(c, v, listahijos, p, dom);
			marco1.dispose();
		};
		btnSeleccionarOtroTipo.addActionListener(tipocob);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(1021, 657, 143, 33);
		marco1.getContentPane().add(btnAceptar);
		ActionListener aceptar = e -> {

			//aca va el dar alta poliza





		};
		btnAceptar.addActionListener(aceptar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		ActionListener cancel = e -> {

			GestorPantallas.PantallaPrincipal();
			marco1.dispose();

		};
		btnCancelar.setBounds(1174, 657, 143, 33);
		btnCancelar.addActionListener(cancel);
		marco1.getContentPane().add(btnCancelar);
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// -----------------------------------------------------------------

	}

}
