package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import tp.disenio.DAO.DAOLocalidad;
import tp.disenio.DAO.DAOProvincia;
import tp.disenio.DTO.ClienteDTO;
import tp.disenio.clases.Localidad;
import tp.disenio.clases.Provincia;
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
		lblProvincia.setBounds(25, 376, 113, 24);
		marco1.getContentPane().add(lblProvincia);

		JLabel lblLocalidad = new JLabel("(*) Localidad");
		lblLocalidad.setFont(new Font("Serif", Font.PLAIN, 18));
		lblLocalidad.setBounds(337, 376, 113, 24);
		marco1.getContentPane().add(lblLocalidad);

		JLabel lblCdigoPostal = new JLabel("(*) C.P.");
		lblCdigoPostal.setFont(new Font("Serif", Font.PLAIN, 18));
		lblCdigoPostal.setBounds(672, 376, 155, 24);
		marco1.getContentPane().add(lblCdigoPostal);

		JLabel lblCondicinIva = new JLabel("(*) Condición IVA");
		lblCondicinIva.setFont(new Font("Serif", Font.PLAIN, 18));
		lblCondicinIva.setBounds(25, 518, 155, 24);
		marco1.getContentPane().add(lblCondicinIva);

		JLabel lblCorreoElectrnico = new JLabel("(*) Correo electrónico");
		lblCorreoElectrnico.setFont(new Font("Serif", Font.PLAIN, 18));
		lblCorreoElectrnico.setBounds(420, 518, 193, 24);
		marco1.getContentPane().add(lblCorreoElectrnico);

		JLabel lblEstadoCivil = new JLabel("(*) Estado civil");
		lblEstadoCivil.setFont(new Font("Serif", Font.PLAIN, 18));
		lblEstadoCivil.setBounds(25, 584, 193, 24);
		marco1.getContentPane().add(lblEstadoCivil);

		JLabel lblProfesin = new JLabel("(*) Profesión");
		lblProfesin.setFont(new Font("Serif", Font.PLAIN, 18));
		lblProfesin.setBounds(420, 584, 193, 24);
		marco1.getContentPane().add(lblProfesin);

		JLabel lblAnioDe = new JLabel("(*) Año de registro");
		lblAnioDe.setFont(new Font("Serif", Font.PLAIN, 18));
		lblAnioDe.setBounds(845, 518, 193, 24);
		marco1.getContentPane().add(lblAnioDe);

		JLabel lblInformacinAdicional = new JLabel("Información Adicional");
		lblInformacinAdicional.setFont(new Font("Serif", Font.BOLD, 20));
		lblInformacinAdicional.setBounds(25, 453, 252, 32);
		marco1.getContentPane().add(lblInformacinAdicional);
		// ------------------------------------------------

		// --------------- COMBOBOX -----------------------
		JComboBox tipoDoc = new JComboBox();
		tipoDoc.setBounds(204, 147, 196, 20);
		marco1.getContentPane().add(tipoDoc);
		tipoDoc.setModel(new DefaultComboBoxModel(TipoDocumento.values()));

		JComboBox comboBox_Provincia = new JComboBox();
		comboBox_Provincia.setBounds(136, 381, 193, 20);
		marco1.getContentPane().add(comboBox_Provincia);
		comboBox_Provincia.setModel(new DefaultComboBoxModel(DAOProvincia.listaProvincia()));
		
		comboBox_Provincia.setRenderer(new MyComboBoxRenderer("SELECCIONE PROVINCIA"));
		comboBox_Provincia.setSelectedIndex(-1);
		
		JComboBox comboBox_Localidad = new JComboBox();
		comboBox_Localidad.setBounds(449, 381, 196, 20);
		marco1.getContentPane().add(comboBox_Localidad);
		
		comboBox_Provincia.addItemListener(arg0 -> {
			if (arg0.getStateChange() == ItemEvent.SELECTED) {

				DefaultComboBoxModel model = new DefaultComboBoxModel(DAOLocalidad.listaLocalidad((Provincia) comboBox_Provincia.getSelectedItem()));
				comboBox_Localidad.setModel(model);
				comboBox_Localidad.setRenderer(new MyComboBoxRenderer("SELECCIONE LOCALIDAD"));
				comboBox_Localidad.setSelectedIndex(-1);
				
			}
		});
		
		comboBox_Localidad.addItemListener(arg0 -> {
			if (arg0.getStateChange() == ItemEvent.SELECTED) {
				Localidad auxL = new Localidad();
				auxL=(Localidad) comboBox_Localidad.getSelectedItem();
				textField_CP.setText(auxL.getCodigoPostal());
				
			}
		});
				
		JComboBox comboBox_CondIVA = new JComboBox();
		comboBox_CondIVA.setBounds(190, 523, 193, 20);
		marco1.getContentPane().add(comboBox_CondIVA);
		comboBox_CondIVA.setModel(new DefaultComboBoxModel(CondicionIVA.values()));
		comboBox_CondIVA.setRenderer(new MyComboBoxRenderer("SELECCIONE COND IVA"));
		comboBox_CondIVA.setSelectedIndex(-1);


		JComboBox comboBox_EstadoCivil = new JComboBox();
		comboBox_EstadoCivil.setBounds(190, 589, 193, 20);
		marco1.getContentPane().add(comboBox_EstadoCivil);
		comboBox_EstadoCivil.setModel(new DefaultComboBoxModel(EstadoCivil.values()));
		comboBox_EstadoCivil.setRenderer(new MyComboBoxRenderer("SELECCIONE ESTADO CIVIL"));
		comboBox_EstadoCivil.setSelectedIndex(-1);

		JComboBox sexo = new JComboBox();
		sexo.setBounds(204, 202, 196, 20);
		marco1.getContentPane().add(sexo);
		sexo.setModel(new DefaultComboBoxModel(Sexo.values()));
		sexo.setRenderer(new MyComboBoxRenderer("SELECCIONE SEXO"));
		sexo.setSelectedIndex(-1);

		// -------------------------------------------------

		// ---------------- CAMPOS DE TEXTO ---------------
		textField_Apellido = new JTextField();
		textField_Apellido.setColumns(10);
		textField_Apellido.setBounds(623, 90, 196, 20);
		marco1.getContentPane().add(textField_Apellido);
		textField_Apellido.addKeyListener(new KeyAdapter() { //el NRO DOCUMENTO SOLO PUEDE TENER 9 CARACTERES 
			@Override
			public void keyTyped(KeyEvent e) {
				int max =40;
				if(textField_Apellido.getText().length() > max) {
					e.consume();
				}
			}
		});

		textField_Nombre = new JTextField();
		textField_Nombre.setColumns(10);
		textField_Nombre.setBounds(1003, 90, 196, 20);
		marco1.getContentPane().add(textField_Nombre);
		textField_Nombre.addKeyListener(new KeyAdapter() { //el NRO DOCUMENTO SOLO PUEDE TENER 9 CARACTERES 
			@Override
			public void keyTyped(KeyEvent e) {
				int max =40;
				if(textField_Nombre.getText().length() > max) {
					e.consume();
				}
			}
		});

		textField_Documento = new JTextField();
		textField_Documento.setColumns(10);
		textField_Documento.setBounds(623, 147, 196, 20);
		marco1.getContentPane().add(textField_Documento);
		textField_Documento.addKeyListener(new KeyAdapter() { //el NRO DOCUMENTO SOLO PUEDE TENER 9 CARACTERES 
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 9;
				if(textField_Documento.getText().length() > max) {
					e.consume();
				}
			}
		});
		
		
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##-##-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		mascara.setPlaceholderCharacter('_');
		final JFormattedTextField fechaNFormattedTextField = new JFormattedTextField(mascara);
		fechaNFormattedTextField.setValue("");
		fechaNFormattedTextField.setBounds(623, 202, 196, 20);
		marco1.getContentPane().add(fechaNFormattedTextField);

		
		textField_Calle = new JTextField();
		textField_Calle.setColumns(10);
		textField_Calle.setBounds(136, 317, 193, 20);
		marco1.getContentPane().add(textField_Calle);
		
		textField_Calle.addKeyListener(new KeyAdapter() { //el NRO DOCUMENTO SOLO PUEDE TENER 9 CARACTERES 
			@Override
			public void keyTyped(KeyEvent e) {
				int max =40;
				if(textField_Calle.getText().length() > max) {
					e.consume();
				}
			}
		});

		textField_NroCalle = new JTextField();
		textField_NroCalle.setColumns(10);
		textField_NroCalle.setBounds(449, 317, 196, 20);
		marco1.getContentPane().add(textField_NroCalle);
		textField_NroCalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 5;
				if(e.getKeyChar()!='1' && e.getKeyChar()!='2' && e.getKeyChar()!='3' && e.getKeyChar()!='4' && e.getKeyChar()!='5' && e.getKeyChar()!='6' && e.getKeyChar()!='7' && e.getKeyChar()!='8' && e.getKeyChar()!='9' && e.getKeyChar()!='0') e.consume();
				else if(textField_NroCalle.getText().length() > max) {
					e.consume();
				}
			}
		});
		

		textField_Piso = new JTextField();  //pueden ser sólo numeros 
		textField_Piso.setColumns(10);
		textField_Piso.setBounds(744, 317, 180, 20);
		marco1.getContentPane().add(textField_Piso);
		textField_Piso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 3;
				if(e.getKeyChar()!='1' && e.getKeyChar()!='2' && e.getKeyChar()!='3' && e.getKeyChar()!='4' && e.getKeyChar()!='5' && e.getKeyChar()!='6' && e.getKeyChar()!='7' && e.getKeyChar()!='8' && e.getKeyChar()!='9' && e.getKeyChar()!='0') e.consume();
				else if(textField_Piso.getText().length() > max) {
					e.consume();
				}
			}
		});
		

		textField_Dpto = new JTextField();
		textField_Dpto.setColumns(10);
		textField_Dpto.setBounds(1003, 317, 180, 20);
		marco1.getContentPane().add(textField_Dpto);
		textField_Dpto.addKeyListener(new KeyAdapter() { 
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 3;
				if(textField_Dpto.getText().length() > max) {
					e.consume();
				}
			}
		});
		

		textField_CP = new JTextField();  //se setea sólo no hay que validarlo 
		textField_CP.setColumns(10);
		textField_CP.setBounds(744, 381, 180, 20);
		marco1.getContentPane().add(textField_CP);

		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(623, 523, 196, 20);
		marco1.getContentPane().add(textField_email);
		textField_email.addKeyListener(new KeyAdapter() { 
			@Override
			public void keyTyped(KeyEvent e) {
				int max =40;
				if(textField_email.getText().length() > max) {
					e.consume();
				}
			}
		});

		textField_anioRegistro = new JTextField();
		textField_anioRegistro.setColumns(10);
		textField_anioRegistro.setBounds(1003, 523, 180, 20);
		textField_anioRegistro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 4;
				if(e.getKeyChar()!='1' && e.getKeyChar()!='2' && e.getKeyChar()!='3' && e.getKeyChar()!='4' && e.getKeyChar()!='5' && e.getKeyChar()!='6' && e.getKeyChar()!='7' && e.getKeyChar()!='8' && e.getKeyChar()!='9' && e.getKeyChar()!='0') e.consume();
				else if(textField_anioRegistro.getText().length() > max+1) {
					e.consume();
					String shortened = textField_anioRegistro.getText().substring(0, max);
					textField_anioRegistro.setText(shortened);
				}else if(textField_anioRegistro.getText().length() > max) {
					e.consume();
				}
			}
		});
				
		marco1.getContentPane().add(textField_anioRegistro);
		

		textField_Profesion = new JTextField();
		textField_Profesion.setColumns(10);
		textField_Profesion.setBounds(623, 589, 196, 20);
		marco1.getContentPane().add(textField_Profesion);
		
		textField_Profesion.addKeyListener(new KeyAdapter() { 
			@Override
			public void keyTyped(KeyEvent e) {
				int max =40;
				if(textField_Profesion.getText().length() > max) {
					e.consume();
				}
			}
		});
		// --------------------------------------------------------------------
		
		
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
				
				formattedTextField_NCUIL.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						int max = 13;
						if(e.getKeyChar()!='1' && e.getKeyChar()!='2' && e.getKeyChar()!='3' && e.getKeyChar()!='4' && e.getKeyChar()!='5' && e.getKeyChar()!='6' && e.getKeyChar()!='7' && e.getKeyChar()!='8' && e.getKeyChar()!='9' && e.getKeyChar()!='0') e.consume();
						else if(textField_anioRegistro.getText().length() > max) {
							e.consume();
						}
					}
				});
						
				
				

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
				
		// ----------- BOTONES -------------------------
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(1021, 657, 143, 33);
		marco1.getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(arg0 -> {//cuando presiona aceptar tengo que hacer las validaciones.
			
			String errores = "";
			boolean error = false; 
			//VALIDO APELLIDO
			try { 
				String apellido = textField_Apellido.getText();
				if (apellido.matches("[0-9]+")== true ) {
					errores += "El campo 'Apellido' no puede contenter números \n";
				}
			
			}
			catch (Exception eApellido) {
				errores += "El campo 'Apellido' es obligatorio  \n";
			}
			
			//VALIDO NOMBRE
			try {
				String nombre = textField_Nombre.getText();
				if (nombre.matches("[0-9]+")== true ) {
					errores += "El campo 'Nombre' no puede contenter números  \n";
				}
			}
			catch (Exception eNombre) {
				errores += "El campo 'Nombre' es obligatorio \n";
			}

			//VALIDO NRO DOCUMENTO
			try {
				String dni = textField_Documento.getText();
				if (tipoDoc.getSelectedItem().toString() == "DNI") {
					if (dni.matches("[a-zA-Z]+")== true) {//  acá habría que mostrar un mensaje de error diciendo que el DNI no puede tener letras
						errores += "El campo DNI no puede contener letras \n";
					}
				}
			}
			catch (Exception eDocumento) {
				errores += "El campo 'Nro. Documento' es obligatorio \n";
			}
			
			//VALIDO CUIL
			try { 
				String nCUIL = formattedTextField_NCUIL.getText();
				if (nCUIL.matches("[a-zA-Z]+")== true ) {
					errores += "El campo 'CUIL' no puede contenter letras \n";
				}
			
			}
			catch (Exception eApellido) {
				errores += "El campo 'CUIL' es obligatorio  \n";
			}

			//VALIDO SEXO
			try { 
				String aux_sexo = sexo.getSelectedItem().toString();			
			
			}
			catch (Exception eApellido) {
				errores += "El campo 'Sexo' es obligatorio  \n";
			}
			
			//VALIDO FECH NAC
			//VALIDO CALLE
			//VALIDO NUMERO
			//VALIDO PISO
			//VALIDO DPTO
			//VALIDO PAIS
			//VALIDO LOCALIDAD
			//VALIDO COND IVA
			try { 
				String estadoC = comboBox_EstadoCivil.getSelectedItem().toString();			
			}
			catch (Exception eApellido) {
				errores += "El campo 'Cond IVA' es obligatorio  \n";
			}
			
			//VALIDO CORREO ELECTRONICO
			//VALIDO ESTADO CIVIL
			try { 
				String estadoC = comboBox_EstadoCivil.getSelectedItem().toString();			
			}
			catch (Exception eApellido) {
				errores += "El campo 'Estado Civil' es obligatorio  \n";
			}
			
			//VALIDO PROFESION
			//VALIDO AÑO REGISTRO

			if (errores != null) { //muestro los mensajes de error
				error = true;
				JOptionPane.showMessageDialog(null, errores);
			}


		});

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(arg0 -> {

			//cuando esta pantalla es llamada desde DAR DE ALTA POLIZA debería retornar a esa pantalla exacta con los datos cargados
			//cuando esta pantalla es llamada desde MENU debe retornar al menu
		});
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(1174, 657, 143, 33);
		marco1.getContentPane().add(btnCancelar);

		

	}


}
