package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tp.disenio.pantallas.Marco;

public class PantallaDarAltaPoliza3Mensual {
	public static void start() {
		
		// ----------------------- MARCO -----------------------------------
		final Marco marco1 = new Marco(1333,730,"DAR DE ALTA POLIZA");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// -----------------------------------------------------------------
		
		// --------------------- ETIQUETAS ---------------------------------
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
		lblPremio.setBounds(826, 284, 139, 22);
		marco1.getContentPane().add(lblPremio);

		JLabel lblImportesPorDescuentos = new JLabel("Importes por descuentos");
		lblImportesPorDescuentos.setFont(new Font("Serif", Font.BOLD, 20));
		lblImportesPorDescuentos.setBounds(24, 329, 249, 33);
		marco1.getContentPane().add(lblImportesPorDescuentos);

		JLabel lblMontoTotalA = new JLabel("Monto total a pagar");
		lblMontoTotalA.setFont(new Font("Serif", Font.PLAIN, 18));
		lblMontoTotalA.setBounds(24, 421, 175, 22);
		marco1.getContentPane().add(lblMontoTotalA);

		// -----------------------------------------------------------------

		// ------------------- CAMPO DE TEXTO ------------------------------
		JTextField textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(218, 46, 222, 20);
		marco1.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextField textField_fechInicio = new JTextField();
		textField_fechInicio.setEditable(false);
		textField_fechInicio.setBounds(160, 230, 196, 20);
		marco1.getContentPane().add(textField_fechInicio);
		textField_fechInicio.setColumns(10);

		JTextField textField_fechFin = new JTextField();
		textField_fechFin.setEditable(false);
		textField_fechFin.setBounds(570, 234, 196, 20);
		marco1.getContentPane().add(textField_fechFin);
		textField_fechFin.setColumns(10);
		
		JTextField textField_fPago = new JTextField();
		textField_fPago.setEditable(false);
		textField_fPago.setBounds(160, 288, 196, 20);
		marco1.getContentPane().add(textField_fPago);
		textField_fPago.setColumns(10);

		JTextField textField_suma = new JTextField();
		textField_suma.setEditable(false);
		textField_suma.setColumns(10);
		textField_suma.setBounds(570, 288, 196, 20);
		marco1.getContentPane().add(textField_suma);

		JTextField textField_Premio = new JTextField();
		textField_Premio.setEditable(false);
		textField_Premio.setColumns(10);
		textField_Premio.setBounds(913, 288, 196, 20);
		marco1.getContentPane().add(textField_Premio);

		JTextField textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(218, 425, 196, 20);
		marco1.getContentPane().add(textField_2);

		// -----------------------------------------------------------------
		
		// -------------------- SCROLL PANE --------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 132, 1267, 43);
		marco1.getContentPane().add(scrollPane);

		JTable tableVehiculo = new JTable();
		tableVehiculo.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
						"Marca", "Modelo", "Motor", "Chasis", "Patente"
				}
				));
		scrollPane.setViewportView(tableVehiculo);
		

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 367, 898, 43);
		marco1.getContentPane().add(scrollPane_1);

		JTable tableDescuentos = new JTable();
		tableDescuentos.setModel(new DefaultTableModel(
				new Object[][] {
					{null},
				},
				new String[] {
						"Descuento por m\u00E1s de una unidad asegurada"
				}
				));
		tableDescuentos.getColumnModel().getColumn(0).setPreferredWidth(483);
		scrollPane_1.setViewportView(tableDescuentos);
		

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 477, 898, 123);
		marco1.getContentPane().add(scrollPane_2);

		JTable tableCuotas = new JTable();
		tableCuotas.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
						"Cuota", "Monto a pagar", "\u00DAltimo d\u00EDa de pago"
				}
				));
		scrollPane_2.setViewportView(tableCuotas);
		// -----------------------------------------------------------------
		
		// ----------------- BOTONES ---------------------------------------
		JButton btnSeleccionarOtroTipo = new JButton("SELECCIONAR OTRO TIPO DE COBERTURA");
		btnSeleccionarOtroTipo.setFont(new Font("Serif", Font.BOLD, 12));
		btnSeleccionarOtroTipo.setBounds(24, 657, 316, 33);
		marco1.getContentPane().add(btnSeleccionarOtroTipo);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(1021, 657, 143, 33);
		marco1.getContentPane().add(btnAceptar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(1174, 657, 143, 33);
		marco1.getContentPane().add(btnCancelar);
		// -----------------------------------------------------------------

	}
}
