package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.DomicilioRiesgoDTO;
import tp.disenio.DTO.HijoDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.DTO.VehiculoDTO;
import tp.disenio.enumerators.FormaPagoEnum;
import tp.disenio.enumerators.TipoCoberturaEnum;
import tp.disenio.gestores.GestorPantallas;

public class PantallaDarAltaPoliza2 {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start(ClienteDTO c, PolizaDTO p, VehiculoDTO v,ArrayList<HijoDTO> listahijos, DomicilioRiesgoDTO dom) {
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

		int anioVehiculo = v.getAnio();
		int anioActual = Calendar.getInstance().get(Calendar.YEAR);
		final JComboBox tipoComboBox = new JComboBox();
		tipoComboBox.setBounds(290, 71, 236, 20);
		marco1.getContentPane().add(tipoComboBox);

		//VALIDO SI TIENE MAS DE 10 AÑOS EL VEHICULO
		if (anioActual - anioVehiculo>10) {
			String labels[] = { "RESPONSABILIDAD CIVIL" };
			final DefaultComboBoxModel model = new DefaultComboBoxModel(labels);
			tipoComboBox.setModel(model);
			tipoComboBox.setRenderer(new MyComboBoxRenderer("SELECCIONE TIPO DE COBERTURA"));
			tipoComboBox.setSelectedIndex(-1);

		}
		else {

			tipoComboBox.setModel(new DefaultComboBoxModel(TipoCoberturaEnum.values()));
			tipoComboBox.setRenderer(new MyComboBoxRenderer("SELECCIONE TIPO DE COBERTURA"));
			tipoComboBox.setSelectedIndex(-1);
		}

		//SELECCIONE_TIPO_COBERTURA,

		final JComboBox pagoComboBox = new JComboBox();
		pagoComboBox.setBounds(290, 195, 236, 20);
		pagoComboBox.setModel(new DefaultComboBoxModel(FormaPagoEnum.values()));
		marco1.getContentPane().add(pagoComboBox);
		marco1.setLocationRelativeTo(null);
		pagoComboBox.setRenderer(new MyComboBoxRenderer("SELECCIONE FORMA DE PAGO"));
		pagoComboBox.setSelectedIndex(-1);



		// -----------------------------------------------------------------

		// --------------------- FORMATTED TEXT FIELD ----------------------
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##-##-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		mascara.setPlaceholderCharacter('_');

		final JFormattedTextField fechaFormattedTextField = new JFormattedTextField(mascara);
		fechaFormattedTextField.setBounds(290, 132, 236, 20);
		marco1.getContentPane().add(fechaFormattedTextField);
		//POR DEFAULT TIENE QUE SER EL DIA SIGUIENTE

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);

		String strDate = dateFormat.format(cal.getTime());
		fechaFormattedTextField.setText(strDate);

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

				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate fechaInicio = LocalDate.parse(fechaIn, fmt);
			}
			catch (Exception exx) {
				fechaValida = false;
				error += "La fecha ingresada es inválida \n";
			}
			if (error != "" ) {
				JOptionPane.showMessageDialog(null, error);
			}


			if (tipoCobertura && formaPago && fechaValida) {

				p.setInicio_vigencia(fechaFormattedTextField.getText());

				DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal1 = Calendar.getInstance();
				try {
					cal1.setTime(dateFormat1.parse(fechaFormattedTextField.getText()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				cal1.add(Calendar.MONTH, 6);
				String strDate1 = dateFormat.format(cal1.getTime());
				p.setFin_vigencia(strDate1);

				p.setTipoCobertura(tipoComboBox.getSelectedItem().toString());
				p.setForma_pago(pagoComboBox.getSelectedItem().toString());

				if (pagoComboBox.getSelectedItem().toString() == "MENSUAL") {
					GestorPantallas.pantalla3AltaMensual(c, p, v, listahijos, dom);
				}
				else if (pagoComboBox.getSelectedItem().toString() == "SEMESTRAL") {
					GestorPantallas.pantalla3AltaSemestral(c, p, v, listahijos, dom);
				}

			}


		};

		btnAceptar.addActionListener(aceptar);


		// CANCELAR
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(536, 493, 143, 33);
		marco1.getContentPane().add(btnCancelar);

		ActionListener cancelar = e -> {

			GestorPantallas.PantallaDarAltaPoliza(c,  p, v, listahijos, dom);

			//GestorPantallas.PantallaPrincipal();
		};

		btnCancelar.addActionListener(cancelar);
		// -----------------------------------------------------------------
	}


}
