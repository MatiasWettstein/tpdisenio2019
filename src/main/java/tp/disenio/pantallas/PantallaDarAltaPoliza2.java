package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.enumerators.FormaPagoEnum;
import tp.disenio.enumerators.TipoCoberturaEnum;
import tp.disenio.gestores.GestorPantallas;
import tp.disenio.gestores.GestorPoliza;

public class PantallaDarAltaPoliza2 {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start(ClienteDTO c, PolizaDTO p) {
		// --------------------------- MARCO -------------------------------
		final Marco marco1 = new Marco(700,600,"DAR DE ALTA POLIZA");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(Color.LIGHT_GRAY);
		marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// -----------------------------------------------------------------

		// ------------------ ETIQUETAS ------------------------------------
		JLabel lblTipoDe = new JLabel("(*) Tipo de cobertura");
		lblTipoDe.setFont(new Font("Serif", Font.PLAIN, 18));
		lblTipoDe.setBounds(37, 62, 200, 33);
		marco1.getContentPane().add(lblTipoDe);

		JLabel lblFechaInicio = new JLabel("(*) Fecha Inicio de vigencia");
		lblFechaInicio.setFont(new Font("Serif", Font.PLAIN, 18));
		lblFechaInicio.setBounds(37, 123, 213, 33);
		marco1.getContentPane().add(lblFechaInicio);

		JLabel lblFormaDe = new JLabel("(*) Forma de pago");
		lblFormaDe.setFont(new Font("Serif", Font.PLAIN, 18));
		lblFormaDe.setBounds(37, 186, 169, 33);
		marco1.getContentPane().add(lblFormaDe);
		// -----------------------------------------------------------------

		// -------------- COMBO BOX ----------------------------------------
		final JComboBox tipoComboBox = new JComboBox();
		tipoComboBox.setBounds(290, 71, 236, 20);
		marco1.getContentPane().add(tipoComboBox);
		tipoComboBox.setModel(new DefaultComboBoxModel(TipoCoberturaEnum.values()));

		final JComboBox pagoComboBox = new JComboBox();
		pagoComboBox.setBounds(290, 195, 236, 20);
		pagoComboBox.setModel(new DefaultComboBoxModel(FormaPagoEnum.values()));
		marco1.getContentPane().add(pagoComboBox);
		marco1.setLocationRelativeTo(null);
		// -----------------------------------------------------------------

		// --------------------- FORMATTED TEXT FIELD ----------------------
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		mascara.setPlaceholderCharacter('_');

		final JFormattedTextField fechaFormattedTextField = new JFormattedTextField(mascara);
		fechaFormattedTextField.setBounds(290, 132, 236, 20);
		marco1.getContentPane().add(fechaFormattedTextField);
		//POR DEFAULT TIENE QUE SER EL DIA SIGUIENTE

		/* Date fechaActual = new Date();   NO FUNCIONAAAAAAAAAAAAA
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		cal.add(Calendar.YEAR, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, 1);

		String dia_default = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		String mes_default = Integer.toString(cal.get(Calendar.MONTH));
		String anio_default = Integer.toString(cal.get(Calendar.YEAR));

		String value = dia_default + "/" + mes_default + "/" + anio_default;
		fechaFormattedTextField.setValue(value);
		 */
		// -----------------------------------------------------------------

		// -------------------- BOTONTES -----------------------------------

		// ACEPTAR
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(383, 493, 143, 33);
		marco1.getContentPane().add(btnAceptar);

		ActionListener aceptar = e -> {
			boolean formaPago = true;
			boolean tipoCobertura = true;
			boolean fechaValida = true;
			String fechaIn = fechaFormattedTextField.getText();
			String error = "";

			if (tipoComboBox.getSelectedItem().toString() == "Seleccionar_Tipo_Cobetura") {
				tipoCobertura = false;
				error += "Debe seleccionar un tipo de cobertura \n";

			}
			if (pagoComboBox.getSelectedItem().toString() == "Seleccionar_Forma_de_Pago") {
				formaPago = false;
				error += "Debe seleccionar una forma de pago \n";
			}

			try {

				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate fechaInicio = LocalDate.parse(fechaIn, fmt);
			}
			catch (Exception exx) {
				fechaValida = false;
				error += "La fecha ingresada es inválida \n";
			}
			if (error != null ) {
				JOptionPane.showMessageDialog(null, error);
			}


			if (tipoCobertura && formaPago && fechaValida) {
				GestorPoliza gestorPoliza = GestorPoliza.getInstance();


				if (pagoComboBox.getSelectedItem().toString() == "MENSUAL") {
					GestorPantallas.pantalla3AltaMensual();
				}
				else if (pagoComboBox.getSelectedItem().toString() == "SEMESTRAL") {
					GestorPantallas.pantalla3AltaSemestral();
				}
			}


		};

		btnAceptar.addActionListener(aceptar);


		// CANCELAR
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(536, 493, 143, 33);
		marco1.getContentPane().add(btnCancelar);

		ActionListener cancelar = e -> GestorPantallas.PantallaPrincipal();

		btnCancelar.addActionListener(cancelar);
		// -----------------------------------------------------------------
	}


}
