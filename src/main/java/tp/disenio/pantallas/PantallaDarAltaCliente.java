package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import tp.disenio.DAO.DAOProvincia;
import tp.disenio.DTO.ClienteDTO;
import tp.disenio.enumerators.CondicionIVA;
import tp.disenio.enumerators.EstadoCivil;
import tp.disenio.enumerators.Sexo;
import tp.disenio.enumerators.TipoDocumento;


public class PantallaDarAltaCliente {
	private static JTextField textField;
	private static JTextField textField_Apellido;
	private static JTextField textField_Nombre;
	private static JTextField textField_Documento;
	private static JTextField textField_FechNac;
	private static JTextField textField_Calle;
	private static JTextField textField_NroCalle;
	private static JTextField textField_Piso;
	private static JTextField textField_Dpto;
	private static JTextField textField_CP;
	private static JTextField textField_email;
	private static JTextField textField_anioRegistro;
	private static JTextField textField_Profesion;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start() {
		ClienteDTO cDTO;
		// --------------- MARCO ------------------------
		final Marco marco1 = new Marco(1333,730,"DAR DE ALTA CLIENTE");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		// ----------------------------------------------

		// ------------- ETIQUETAS ---------------------
		JLabel lblNroCliente = new JLabel("Nro. Cliente");
		lblNroCliente.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNroCliente.setBounds(25, 85, 113, 24);
		marco1.getContentPane().add(lblNroCliente);

		JLabel lblApellido = new JLabel("(*) Apellido");
		lblApellido.setFont(new Font("Serif", Font.PLAIN, 18));
		lblApellido.setBounds(420, 85, 113, 24);
		marco1.getContentPane().add(lblApellido);

		JLabel lblNombre = new JLabel("(*) Nombre");
		lblNombre.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNombre.setBounds(880, 85, 113, 24);
		marco1.getContentPane().add(lblNombre);

		JLabel lblTipoDocumento = new JLabel("(*) Tipo Documento");
		lblTipoDocumento.setFont(new Font("Serif", Font.PLAIN, 18));
		lblTipoDocumento.setBounds(25, 142, 149, 24);
		marco1.getContentPane().add(lblTipoDocumento);

		JLabel lblDocumento = new JLabel("(*) Documento");
		lblDocumento.setFont(new Font("Serif", Font.PLAIN, 18));
		lblDocumento.setBounds(420, 142, 113, 24);
		marco1.getContentPane().add(lblDocumento);

		JLabel lblNroCuil = new JLabel("(*) Nro CUIL");
		lblNroCuil.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNroCuil.setBounds(880, 142, 113, 24);
		marco1.getContentPane().add(lblNroCuil);

		JLabel lblSexo = new JLabel("(*) Sexo");
		lblSexo.setFont(new Font("Serif", Font.PLAIN, 18));
		lblSexo.setBounds(25, 197, 113, 24);
		marco1.getContentPane().add(lblSexo);

		JLabel lblFechaDe = new JLabel("(*) Fecha de nacimiento");
		lblFechaDe.setFont(new Font("Serif", Font.PLAIN, 18));
		lblFechaDe.setBounds(420, 197, 193, 24);
		marco1.getContentPane().add(lblFechaDe);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Serif", Font.BOLD, 20));
		lblDomicilio.setBounds(25, 252, 132, 24);
		marco1.getContentPane().add(lblDomicilio);

		JLabel lblDatoPersonales = new JLabel("Dato personales");
		lblDatoPersonales.setFont(new Font("Serif", Font.BOLD, 20));
		lblDatoPersonales.setBounds(25, 36, 169, 24);
		marco1.getContentPane().add(lblDatoPersonales);

		JLabel lblCalle = new JLabel("(*) Calle");
		lblCalle.setFont(new Font("Serif", Font.PLAIN, 18));
		lblCalle.setBounds(25, 312, 113, 24);
		marco1.getContentPane().add(lblCalle);

		JLabel lblCal = new JLabel("(*) Número");
		lblCal.setFont(new Font("Serif", Font.PLAIN, 18));
		lblCal.setBounds(337, 312, 113, 24);
		marco1.getContentPane().add(lblCal);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Serif", Font.PLAIN, 18));
		lblPiso.setBounds(672, 312, 113, 24);
		marco1.getContentPane().add(lblPiso);

		JLabel lblDpto = new JLabel("Dpto");
		lblDpto.setFont(new Font("Serif", Font.PLAIN, 18));
		lblDpto.setBounds(940, 312, 113, 24);
		marco1.getContentPane().add(lblDpto);

		JLabel lblProvincia = new JLabel("(*) Provincia");
		lblProvincia.setFont(new Font("Serif", Font.PLAIN, 18));
		lblProvincia.setBounds(337, 376, 113, 24);
		marco1.getContentPane().add(lblProvincia);

		JLabel lblLocalidad = new JLabel("(*) Localidad");
		lblLocalidad.setFont(new Font("Serif", Font.PLAIN, 18));
		lblLocalidad.setBounds(672, 376, 113, 24);
		marco1.getContentPane().add(lblLocalidad);

		JLabel lblCdigoPostal = new JLabel("(*) C.P.");
		lblCdigoPostal.setFont(new Font("Serif", Font.PLAIN, 18));
		lblCdigoPostal.setBounds(25, 432, 155, 24);
		marco1.getContentPane().add(lblCdigoPostal);

		JLabel lblCondicinIva = new JLabel("(*) Condición IVA");
		lblCondicinIva.setFont(new Font("Serif", Font.PLAIN, 18));
		lblCondicinIva.setBounds(25, 546, 155, 24);
		marco1.getContentPane().add(lblCondicinIva);

		JLabel lblCorreoElectrnico = new JLabel("(*) Correo electrónico");
		lblCorreoElectrnico.setFont(new Font("Serif", Font.PLAIN, 18));
		lblCorreoElectrnico.setBounds(420, 546, 193, 24);
		marco1.getContentPane().add(lblCorreoElectrnico);

		JLabel lblEstadoCivil = new JLabel("(*) Estado civil");
		lblEstadoCivil.setFont(new Font("Serif", Font.PLAIN, 18));
		lblEstadoCivil.setBounds(26, 612, 193, 24);
		marco1.getContentPane().add(lblEstadoCivil);

		JLabel lblProfesin = new JLabel("(*) Profesión");
		lblProfesin.setFont(new Font("Serif", Font.PLAIN, 18));
		lblProfesin.setBounds(420, 612, 193, 24);
		marco1.getContentPane().add(lblProfesin);

		JLabel lblAnioDe = new JLabel("(*) Año de registro");
		lblAnioDe.setFont(new Font("Serif", Font.PLAIN, 18));
		lblAnioDe.setBounds(846, 546, 193, 24);
		marco1.getContentPane().add(lblAnioDe);

		JLabel lblInformacinAdicional = new JLabel("Información Adicional");
		lblInformacinAdicional.setFont(new Font("Serif", Font.BOLD, 20));
		lblInformacinAdicional.setBounds(25, 490, 252, 32);
		marco1.getContentPane().add(lblInformacinAdicional);
		// ------------------------------------------------

		// --------------- COMBOBOX -----------------------
		JComboBox tipoDoc = new JComboBox();
		tipoDoc.setBounds(204, 147, 196, 20);
		marco1.getContentPane().add(tipoDoc);
		tipoDoc.setModel(new DefaultComboBoxModel(TipoDocumento.values()));

		JComboBox comboBox_Provincia = new JComboBox();
		comboBox_Provincia.setBounds(449, 381, 193, 20);
		marco1.getContentPane().add(comboBox_Provincia);
		comboBox_Provincia.setModel(new DefaultComboBoxModel(DAOProvincia.listaProvincia()));
		JComboBox comboBox_Localidad = new JComboBox();
		comboBox_Localidad.setBounds(795, 381, 193, 20);
		marco1.getContentPane().add(comboBox_Localidad);
		//comboBox_Localidad.setModel(new DefaultComboBoxModel(Localidades.values()));

		JComboBox comboBox_CondIVA = new JComboBox();
		comboBox_CondIVA.setBounds(190, 551, 193, 20);
		marco1.getContentPane().add(comboBox_CondIVA);
		comboBox_CondIVA.setModel(new DefaultComboBoxModel(CondicionIVA.values()));

		JComboBox comboBox_EstadoCivil = new JComboBox();
		comboBox_EstadoCivil.setBounds(190, 617, 193, 20);
		marco1.getContentPane().add(comboBox_EstadoCivil);
		comboBox_EstadoCivil.setModel(new DefaultComboBoxModel(EstadoCivil.values()));

		JComboBox sexo = new JComboBox();
		sexo.setBounds(204, 202, 196, 20);
		marco1.getContentPane().add(sexo);
		sexo.setModel(new DefaultComboBoxModel(Sexo.values()));

		// -------------------------------------------------

		// ---------------- CAMPOS DE TEXTO ---------------
		textField_Apellido = new JTextField();
		textField_Apellido.setColumns(10);
		textField_Apellido.setBounds(623, 90, 196, 20);
		marco1.getContentPane().add(textField_Apellido);

		textField_Nombre = new JTextField();
		textField_Nombre.setColumns(10);
		textField_Nombre.setBounds(1003, 90, 196, 20);
		marco1.getContentPane().add(textField_Nombre);

		textField_Documento = new JTextField();
		textField_Documento.setColumns(10);
		textField_Documento.setBounds(623, 147, 196, 20);
		marco1.getContentPane().add(textField_Documento);

		textField_FechNac = new JTextField();
		textField_FechNac.setColumns(10);
		textField_FechNac.setBounds(623, 202, 196, 20);
		marco1.getContentPane().add(textField_FechNac);

		textField_Calle = new JTextField();
		textField_Calle.setColumns(10);
		textField_Calle.setBounds(110, 317, 193, 20);
		marco1.getContentPane().add(textField_Calle);

		textField_NroCalle = new JTextField();
		textField_NroCalle.setColumns(10);
		textField_NroCalle.setBounds(449, 317, 196, 20);
		marco1.getContentPane().add(textField_NroCalle);

		textField_Piso = new JTextField();
		textField_Piso.setColumns(10);
		textField_Piso.setBounds(732, 317, 180, 20);
		marco1.getContentPane().add(textField_Piso);

		textField_Dpto = new JTextField();
		textField_Dpto.setColumns(10);
		textField_Dpto.setBounds(1003, 317, 180, 20);
		marco1.getContentPane().add(textField_Dpto);

		textField_CP = new JTextField();
		textField_CP.setColumns(10);
		textField_CP.setBounds(110, 437, 193, 20);
		marco1.getContentPane().add(textField_CP);

		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(623, 551, 196, 20);
		marco1.getContentPane().add(textField_email);

		textField_anioRegistro = new JTextField();
		textField_anioRegistro.setColumns(10);
		textField_anioRegistro.setBounds(1003, 551, 180, 20);
		marco1.getContentPane().add(textField_anioRegistro);

		textField_Profesion = new JTextField();
		textField_Profesion.setColumns(10);
		textField_Profesion.setBounds(623, 617, 196, 20);
		marco1.getContentPane().add(textField_Profesion);

		// ----------- BOTONES -------------------------
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(1021, 657, 143, 33);
		marco1.getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(arg0 -> {
			//cuando presiona aceptar tengo que hacer las validaciones.
			//VALIDO APELLIDO
			if (textField_Apellido.getText() != null  ) {
				String apellido;
				apellido = textField_Apellido.getText();
				if (apellido.matches("[0-9]+")== true ) {
					//el mensaje de error hay que meterlo en un ArrayList de tipo string que tenga todos los mensajes de errores para pasarselos a la pantalla de errores
					//acá habría que mostrar un mensaje de error diciendo que el apellido no puede contener numeros
				}
			}
			//acá iría un else diciendo que se llama a una pantalla de ERROR porque el campo obligatorio está incompleto


			//VALIDO NOMBRE
			if (textField_Nombre.getText() != null  ) {
				String nombre;
				nombre = textField_Nombre.getText();
				if (nombre.matches("[0-9]+")== true ) {
					//acá habría que mostrar un mensaje de error diciendo que el nombre no puede contener numeros
				}
			}
			//acá iría un else diciendo que se llama a una pantalla de ERROR porque el campo obligatorio está incompleto

			//VALIDO NRO DOCUMENTO
			if (textField_Documento.getText() != null  ) {
				String dni;
				dni = textField_Documento.getText();
				if (dni.matches("[a-zA-Z]+")== true ) {
					//acá habría que mostrar un mensaje de error diciendo que el DNI no puede tener letras
				}
			}
			//acá iría un else diciendo que se llama a una pantalla de ERROR porque el campo obligatorio está incompleto

			//VALIDO CUIL

			//VALIDO SEXO

			//VALIDO FECH NAC
			//VALIDO CALLE
			//VALIDO NUMERO
			//VALIDO PISO
			//VALIDO DPTO
			//VALIDO PAIS
			//VALIDO LOCALIDAD
			//VALIDO CODIGO POSTAL
			//VALIDO COND IVA
			//VALIDO CORREO ELECTRONICO
			//VALIDO ESTADO CIVIL
			//VALIDO PROFESION
			//VALIDO AÑO REGISTRO



		});

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(arg0 -> {

			//cuando esta pantalla es llamada desde DAR DE ALTA POLIZA debería retornar a esa pantalla exacta con los datos cargados
			//cuando esta pantalla es llamada desde MENU debe retornar al menu
		});
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(1174, 657, 143, 33);
		marco1.getContentPane().add(btnCancelar);

		// ------------ CAMPO DE TEXTO CON FORMATO ----------------------------

		MaskFormatter mascaraCUIL = null;
		try {
			mascaraCUIL = new MaskFormatter("##-########-#");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		mascaraCUIL.setPlaceholderCharacter('_');

		JFormattedTextField formattedTextField_NCUIL = new JFormattedTextField(mascaraCUIL);
		formattedTextField_NCUIL.setBounds(1003, 147, 196, 20);
		marco1.getContentPane().add(formattedTextField_NCUIL);

		MaskFormatter mascaraNCLIENTE = null;
		try {
			mascaraNCLIENTE = new MaskFormatter("##-########");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		mascaraNCLIENTE.setPlaceholderCharacter('_');

		JFormattedTextField formattedTextField_NCliente = new JFormattedTextField(mascaraNCLIENTE);
		formattedTextField_NCliente.setEditable(false);
		formattedTextField_NCliente.setEnabled(false);
		formattedTextField_NCliente.setBounds(204, 90, 193, 20);
		marco1.getContentPane().add(formattedTextField_NCliente);
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}


}
