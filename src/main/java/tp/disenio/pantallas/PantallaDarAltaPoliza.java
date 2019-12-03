package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.DTO.DomicilioRiesgoDTO;
import tp.disenio.DTO.HijoDTO;
import tp.disenio.DTO.LocalidadDTO;
import tp.disenio.DTO.MarcaDTO;
import tp.disenio.DTO.ModeloDTO;
import tp.disenio.DTO.PolizaDTO;
import tp.disenio.DTO.ProvinciaDTO;
import tp.disenio.DTO.VehiculoDTO;
import tp.disenio.clases.Localidad;
import tp.disenio.clases.Marca;
import tp.disenio.clases.Modelo;
import tp.disenio.clases.Provincia;
import tp.disenio.clases.SubsistemaSiniestros;
import tp.disenio.enumerators.EstadoCivil;
import tp.disenio.enumerators.Sexo;
import tp.disenio.enumerators.TipoDocumento;
import tp.disenio.gestores.GestorCliente;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPantallas;
import tp.disenio.gestores.GestorParametros;

public class PantallaDarAltaPoliza {
	private static JTable tablaCliente = new JTable();
	static DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
					"Nro. Cliente", "Apellido", "Nombre", "Tipo Documento", "Nro. Documento"
			}
			){

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int i, int i1) {
			return false;
		}
	};


	private static JTable tablaHijos = new JTable();

	static DefaultTableModel modelhijos =new DefaultTableModel(
			new Object[][] {},
			new String[] {
					"Fecha de Nacimiento", "Sexo", "Estado Civil"
			}
			){

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int i, int i1) {
			return false;
		}
	};
	static JTextField SiniestroText = new JTextField();

	private static JTextField motorTexto;
	private static JTextField chasisText;
	private static JTextField patenteText;
	private static JFormattedTextField textNroCliente;
	private static JTextField textNroDoc;
	private static JTextField textNombre;
	private static JTable table;

	/**
	 * @wbp.parser.entryPoint
	 */
	static ClienteDTO c;
	static ArrayList<HijoDTO> listaHijos = new ArrayList <>();
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start(ClienteDTO c1, PolizaDTO p1, VehiculoDTO v1,ArrayList<HijoDTO> listahijos1, DomicilioRiesgoDTO dom1) {
		// --------------- MARCO --------------
		final Marco marco1 = new Marco(1333,730,"DAR DE ALTA POLIZA");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


		JScrollPane scrollPaneCliente = new JScrollPane();
		scrollPaneCliente.setBounds(25, 62, 1267, 43);
		marco1.getContentPane().add(scrollPaneCliente);

		if(c1==null) {
			c = new ClienteDTO();
			listaHijos = new ArrayList <>();
			model = new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null},
					},
					new String[] {
							"Nro. Cliente", "Apellido", "Nombre", "Tipo Documento", "Nro. Documento"
					}
					){

				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int i, int i1) {
					return false;
				}
			};

			modelhijos =new DefaultTableModel(
					new Object[][] {},
					new String[] {
							"Fecha de Nacimiento", "Sexo", "Estado Civil"
					}
					){

				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int i, int i1) {
					return false;
				}
			};
			tablaCliente.setModel(model);
			scrollPaneCliente.setViewportView(tablaCliente);
		}else {

			model.setValueAt(c1.getNroCliente(), 0, 0);
			model.setValueAt(c1.getApellido(), 0, 1);
			model.setValueAt(c1.getNombre(), 0, 2);
			model.setValueAt(c1.getTipoDoc(), 0, 3);
			model.setValueAt(c1.getDocumento(), 0, 4);
			tablaCliente.setModel(model);
			scrollPaneCliente.setViewportView(tablaCliente);
		}

		// ------- ETIQUETAS -----------
		JLabel domicilio = new JLabel("Domicilio de riesgo");
		domicilio.setSize(196, 38);
		domicilio.setLocation(25, 109);
		domicilio.setFont(new Font("Serif", Font.BOLD, 19));
		marco1.getContentPane().add(domicilio);

		//
		JLabel provincia = new JLabel("(*) Provincia");
		provincia.setSize(135, 27);
		provincia.setLocation(25, 152);
		provincia.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(provincia);
		//
		JLabel localidad = new JLabel("(*)Localidad");
		localidad.setSize(122, 50);
		localidad.setLocation(433, 139);
		localidad.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(localidad);
		//
		JLabel infoV = new JLabel("Información del vehículo");
		infoV.setSize(236, 50);
		infoV.setLocation(25, 185);
		infoV.setFont(new Font("Serif", Font.BOLD, 19));
		marco1.getContentPane().add(infoV);
		//
		JLabel marca = new JLabel("(*) Marca");
		marca.setSize(100, 50);
		marca.setLocation(25, 222);
		marca.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(marca);
		//
		JLabel modelo = new JLabel("(*) Modelo");
		modelo.setLocation(317, 231);
		modelo.setSize(90, 33);
		modelo.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(modelo);
		//
		JLabel anio = new JLabel("(*) Año");
		anio.setLocation(623, 231);
		anio.setSize(81, 33);
		anio.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(anio);
		//
		JLabel suma = new JLabel("Suma Asegurada");
		suma.setSize(130, 33);
		suma.setLocation(920, 231);
		suma.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(suma);
		//
		JLabel motor = new JLabel("(*) Motor");
		motor.setSize(100, 20);
		motor.setLocation(25, 283);
		motor.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(motor);
		//
		JLabel chasis = new JLabel("(*) Chasis");
		chasis.setSize(100, 20);
		chasis.setLocation(317, 283);
		chasis.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(chasis);
		//
		JLabel patente = new JLabel("(*) Patente");
		patente.setSize(100, 20);
		patente.setLocation(623, 283);
		patente.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(patente);
		//
		JLabel kmAnio = new JLabel("(*) Km por año");
		kmAnio.setSize(135, 20);
		kmAnio.setLocation(920, 283);
		kmAnio.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(kmAnio);
		//
		JLabel medidas = new JLabel("Medidas de seguridad");
		medidas.setSize(196, 33);
		medidas.setLocation(25, 321);
		medidas.setFont(new Font("Serif", Font.BOLD, 19));
		marco1.getContentPane().add(medidas);
		//
		JLabel garage = new JLabel("¿Se guarda en garage?");
		garage.setSize(185, 33);
		garage.setLocation(25, 353);
		garage.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(garage);
		//
		JLabel alarma = new JLabel("¿Tiene alarma?");
		alarma.setSize(122, 33);
		alarma.setLocation(619, 353);
		alarma.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(alarma);
		//
		JLabel dispositivo = new JLabel("¿Posee dispositivo de rastreo?");
		dispositivo.setSize(236, 33);
		dispositivo.setLocation(298, 353);
		dispositivo.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(dispositivo);
		//
		JLabel tuercas = new JLabel ("¿Posee tuercas antirobo en las cuatro ruedas?");
		tuercas.setSize(335, 33);
		tuercas.setLocation(836, 353);
		tuercas.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(tuercas);
		//
		JLabel siniestros = new JLabel ("(*) Número de siniestros en el último año");
		siniestros.setSize(310, 33);
		siniestros.setLocation(25, 397);
		siniestros.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(siniestros);
		//
		JLabel hijos = new JLabel ("Hijos entre 18 y 30 años");
		hijos.setSize(222, 33);
		hijos.setLocation(25, 435);
		hijos.setFont(new Font("Serif", Font.BOLD, 19));
		marco1.getContentPane().add(hijos);
		// -------------------------------------------------------------------------


		// ------------- COMBOBOX ----------
		final JComboBox provinciaCombo = new JComboBox();

		provinciaCombo.setMaximumRowCount(23);
		provinciaCombo.setModel(new DefaultComboBoxModel(GestorParametros.obtenerProvincias()));
		provinciaCombo.setBounds(144, 158, 222, 20);
		marco1.getContentPane().add(provinciaCombo);

		provinciaCombo.setRenderer(new MyComboBoxRenderer("SELECCIONE PROVINCIA"));
		provinciaCombo.setSelectedIndex(-1);

		final JComboBox localidadCombo = new JComboBox();
		localidadCombo.setMaximumRowCount(150);
		localidadCombo.setBounds(565, 158, 222, 20);
		marco1.getContentPane().add(localidadCombo);

		provinciaCombo.addItemListener(arg0 -> {
			if (arg0.getStateChange() == ItemEvent.SELECTED) {

				DefaultComboBoxModel model = new DefaultComboBoxModel(GestorParametros.obtenerLocalidad((Provincia) provinciaCombo.getSelectedItem()));
				localidadCombo.setModel(model);
				localidadCombo.setRenderer(new MyComboBoxRenderer("SELECCIONE LOCALIDAD"));
				localidadCombo.setSelectedIndex(-1);


			}
		});

		final JComboBox marcaCombo = new JComboBox();
		marcaCombo.setBounds(111, 240, 196, 20);
		marcaCombo.setModel(new DefaultComboBoxModel(GestorParametros.obtenerMarcas()));
		marco1.getContentPane().add(marcaCombo);

		marcaCombo.setRenderer(new MyComboBoxRenderer("SELECCIONE MARCA"));
		marcaCombo.setSelectedIndex(-1);

		final JComboBox modeloCombo = new JComboBox();
		modeloCombo.setBounds(417, 240, 196, 20);
		marco1.getContentPane().add(modeloCombo);

		marcaCombo.addItemListener(arg0 -> {
			if (arg0.getStateChange() == ItemEvent.SELECTED) {

				DefaultComboBoxModel model = new DefaultComboBoxModel(GestorParametros.obtenerModelos((Marca) marcaCombo.getSelectedItem()));
				modeloCombo.setModel(model);
				modeloCombo.setRenderer(new MyComboBoxRenderer("SELECCIONE MODELO"));
				modeloCombo.setSelectedIndex(-1);
			}
		});


		final JComboBox anioCombo = new JComboBox();
		anioCombo.setBounds(714, 240, 196, 20);
		marco1.getContentPane().add(anioCombo);

		modeloCombo.addItemListener(arg0 -> {
			if (arg0.getStateChange() == ItemEvent.SELECTED) {

				DefaultComboBoxModel model = new DefaultComboBoxModel(GestorParametros.obtenerAnios((Modelo) modeloCombo.getSelectedItem()));
				anioCombo.setModel(model);
				anioCombo.setRenderer(new MyComboBoxRenderer("SELECCIONE AÑO"));
				anioCombo.setSelectedIndex(-1);
			}
		});


		// ----------- FORMATTED TEXT FIELD ---------------
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("$######,##");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		mascara.setPlaceholderCharacter('_');

		JFormattedTextField sumaFormattedTextField = new JFormattedTextField();
		sumaFormattedTextField.setEditable(false);
		sumaFormattedTextField.setBounds(1060, 240, 197, 20);
		marco1.getContentPane().add(sumaFormattedTextField);

		anioCombo.addItemListener(arg0 -> {
			if (arg0.getStateChange() == ItemEvent.SELECTED) {
				String aux_anio = anioCombo.getSelectedItem().toString();
				Modelo aux_modelo = new Modelo();
				aux_modelo = (Modelo) modeloCombo.getSelectedItem();
				sumaFormattedTextField.setValue(GestorParametros.obtenerSumaAsegurada(aux_modelo, aux_anio));
			}
		});

		// ------------------------------------


		// ----------- CAMPOS DE TEXTO ------------

		SiniestroText.setEnabled(false);
		SiniestroText.setBounds(345, 406, 196, 20);
		marco1.getContentPane().add(SiniestroText);

		motorTexto = new JTextField();
		motorTexto.setBounds(111, 287, 196, 20);
		marco1.getContentPane().add(motorTexto);
		motorTexto.setColumns(10);

		if(v1==null) {
			motorTexto.addKeyListener(new KeyAdapter() { // EL MOTOR PUEDEN SER HASTA 12 CARACTERES
				@Override
				public void keyTyped(KeyEvent e) {
					int max = 11;
					if(motorTexto.getText().length() > max+1) {
						e.consume();
						String shortened = motorTexto.getText().substring(0, max);
						motorTexto.setText(shortened);
					}else if(motorTexto.getText().length() > max) {
						e.consume();
					}
				}
			});
		}else {

			motorTexto.setText(v1.getMotor());
			motorTexto.addKeyListener(new KeyAdapter() { // EL MOTOR PUEDEN SER HASTA 12 CARACTERES
				@Override
				public void keyTyped(KeyEvent e) {
					int max = 11;
					if(motorTexto.getText().length() > max+1) {
						e.consume();
						String shortened = motorTexto.getText().substring(0, max);
						motorTexto.setText(shortened);
					}else if(motorTexto.getText().length() > max) {
						e.consume();
					}
				}
			});
		}

		chasisText = new JTextField();
		chasisText.setBounds(417, 287, 196, 20);
		marco1.getContentPane().add(chasisText);
		chasisText.setColumns(10);

		if(v1==null) {
			chasisText.addKeyListener(new KeyAdapter() { // EL CHASIS PUEDEN SER HASTA 17 CARACTERES
				@Override
				public void keyTyped(KeyEvent e) {
					int max = 16;
					if(e.getKeyChar()=='?' || e.getKeyChar()=='<' || e.getKeyChar()=='>' || e.getKeyChar()=='*' || e.getKeyChar()=='+' && e.getKeyChar()!='¡' && e.getKeyChar()!='_' && e.getKeyChar()!='|' && e.getKeyChar()!='¬' && e.getKeyChar()!='°' && e.getKeyChar()!='=') e.consume();
					else if(chasisText.getText().length() > max+1) {
						e.consume();
						String shortened = chasisText.getText().substring(0, max);
						chasisText.setText(shortened);
					}else if(chasisText.getText().length() > max) {
						e.consume();
					}
				}
			});
		} else {
			chasisText.setText(v1.getChasis());
			chasisText.addKeyListener(new KeyAdapter() { // EL CHASIS PUEDEN SER HASTA 17 CARACTERES
				@Override
				public void keyTyped(KeyEvent e) {
					int max = 16;
					if(chasisText.getText().length() > max+1) {
						e.consume();
						String shortened = chasisText.getText().substring(0, max);
						chasisText.setText(shortened);
					}else if(chasisText.getText().length() > max) {
						e.consume();
					}
				}
			});

		}

		patenteText = new JTextField();
		patenteText.setBounds(714, 286, 196, 20);
		marco1.getContentPane().add(patenteText);
		patenteText.setColumns(10);


		if(v1==null) {
			patenteText.addKeyListener(new KeyAdapter() { // LA PATENTE PUEDEN SER HASTA 7 CARACTERES
				@Override
				public void keyTyped(KeyEvent e) {
					int max = 6;
					if(patenteText.getText().length() > max+1) {
						e.consume();
						String shortened = patenteText.getText().substring(0, max);
						patenteText.setText(shortened);
					}else if(patenteText.getText().length() > max) {
						e.consume();
					}
				}
			});
		} else {

			patenteText.setText(v1.getPatente());
			patenteText.addKeyListener(new KeyAdapter() { // LA PATENTE PUEDEN SER HASTA 7 CARACTERES
				@Override
				public void keyTyped(KeyEvent e) {
					int max = 6;
					if(patenteText.getText().length() > max+1) {
						e.consume();
						String shortened = patenteText.getText().substring(0, max);
						patenteText.setText(shortened);
					}else if(patenteText.getText().length() > max) {
						e.consume();
					}
				}
			});

		}

		// ------- JSPINNER -----------------------

		final JSpinner kmSpinner;
		if(p1==null) {
			kmSpinner = new JSpinner(new SpinnerNumberModel(10000, 10000, 9000000, 10000));
			kmSpinner.setToolTipText("");
			kmSpinner.setBounds(1060, 286, 197, 20);
			marco1.getContentPane().add(kmSpinner);
		} else {
			kmSpinner = new JSpinner(new SpinnerNumberModel(p1.getKmPorAnio(), 10000, 9000000, 10000));
			kmSpinner.setToolTipText("");
			kmSpinner.setBounds(1060, 286, 197, 20);
			marco1.getContentPane().add(kmSpinner);
		}


		// ----------- RADIO BUTTON ------------------

		//GARAGE
		final JRadioButton garageRdbtnSi = new JRadioButton("SI");
		garageRdbtnSi.setBackground(Color.LIGHT_GRAY);
		garageRdbtnSi.setBounds(198, 361, 49, 23);
		marco1.getContentPane().add(garageRdbtnSi);
		garageRdbtnSi.setActionCommand("SI");

		final JRadioButton garageRdbtnNo = new JRadioButton("NO");
		garageRdbtnNo.setBackground(Color.LIGHT_GRAY);
		garageRdbtnNo.setBounds(243, 361, 49, 23);
		marco1.getContentPane().add(garageRdbtnNo);
		garageRdbtnNo.setActionCommand("NO");

		final ButtonGroup grupoGarage = new ButtonGroup();
		grupoGarage.add(garageRdbtnSi);
		grupoGarage.add(garageRdbtnNo);
		///					DISPOSITIVO
		final JRadioButton dispositivoRdbtnSi = new JRadioButton("SI");
		dispositivoRdbtnSi.setBackground(Color.LIGHT_GRAY);
		dispositivoRdbtnSi.setBounds(521, 361, 49, 23);
		marco1.getContentPane().add(dispositivoRdbtnSi);
		dispositivoRdbtnSi.setActionCommand("SI");

		final JRadioButton dispositivoRdbtnNo = new JRadioButton("NO");
		dispositivoRdbtnNo.setBackground(Color.LIGHT_GRAY);
		dispositivoRdbtnNo.setBounds(567, 361, 46, 23);
		marco1.getContentPane().add(dispositivoRdbtnNo);
		dispositivoRdbtnNo.setActionCommand("NO");

		final ButtonGroup grupoDisp = new ButtonGroup();
		grupoDisp.add(dispositivoRdbtnSi);
		grupoDisp.add(dispositivoRdbtnNo);
		///					ALARMA
		final JRadioButton alarmaRdbtnSi = new JRadioButton("SI");
		alarmaRdbtnSi.setBackground(Color.LIGHT_GRAY);
		alarmaRdbtnSi.setBounds(736, 361, 51, 23);
		marco1.getContentPane().add(alarmaRdbtnSi);
		alarmaRdbtnSi.setActionCommand("SI");

		final JRadioButton alarmaRdbtnNo = new JRadioButton("NO");
		alarmaRdbtnNo.setBackground(Color.LIGHT_GRAY);
		alarmaRdbtnNo.setBounds(784, 361, 46, 23);
		marco1.getContentPane().add(alarmaRdbtnNo);
		alarmaRdbtnNo.setActionCommand("NO");

		final ButtonGroup grupoAlarma = new ButtonGroup();
		grupoAlarma.add(alarmaRdbtnSi);
		grupoAlarma.add(alarmaRdbtnNo);
		///					TUERCAS
		final JRadioButton tuercasRdbtnSi = new JRadioButton("SI");
		tuercasRdbtnSi.setBackground(Color.LIGHT_GRAY);
		tuercasRdbtnSi.setBounds(1166, 361, 49, 23);
		marco1.getContentPane().add(tuercasRdbtnSi);
		tuercasRdbtnSi.setActionCommand("SI");

		final JRadioButton tuercasRdbtnNo = new JRadioButton("NO");
		tuercasRdbtnNo.setBackground(Color.LIGHT_GRAY);
		tuercasRdbtnNo.setBounds(1211, 361, 46, 23);
		marco1.getContentPane().add(tuercasRdbtnNo);
		tuercasRdbtnNo.setActionCommand("NO");

		final ButtonGroup grupoTuerca = new ButtonGroup();
		grupoTuerca.add(tuercasRdbtnSi);
		grupoTuerca.add(tuercasRdbtnNo);

		// --------- SCROLL PANE ----------------

		JScrollPane scrollPaneHijos = new JScrollPane();
		scrollPaneHijos.setBounds(284, 471, 693, 168);
		marco1.getContentPane().add(scrollPaneHijos);

		JScrollBar scrollBarHijos = new JScrollBar();
		scrollBarHijos.setBackground(Color.LIGHT_GRAY);
		scrollPaneHijos.setRowHeaderView(scrollBarHijos);

		tablaHijos.setModel(modelhijos);
		scrollPaneHijos.setViewportView(tablaHijos);


		// ---------- BOTONES ------------
		JButton buscarC = new JButton("BUSCAR CLIENTE");
		ActionListener buscarCliente = e -> {

			GestorPantallas.buscarcliente();
		};

		buscarC.addActionListener(buscarCliente);
		buscarC.setFont(new Font("Serif", Font.BOLD, 12));
		buscarC.setBounds(25, 10, 222, 33);
		marco1.getContentPane().add(buscarC);

		//
		JButton altaC = new JButton ("DAR DE ALTA CLIENTE");
		ActionListener altaCliente = e -> {
			if (SiniestroText.getText() == null) SiniestroText.setText(SubsistemaSiniestros.cantidadSiniestros());
			GestorPantallas.PantallaDarAltaCliente(); //hay que ver si esta es la forma porque no se como hacer que te devuelva a esta pntalla desp
			marco1.dispose();
		};
		altaC.addActionListener(altaCliente);
		altaC.setFont(new Font("Serif", Font.BOLD, 12));
		altaC.setBounds(1070, 10, 222, 33);
		marco1.getContentPane().add(altaC);

		//
		JButton agregarH = new JButton("AGREGAR DECLARACIÓN DE HIJO");
		agregarH.setFont(new Font("Serif", Font.BOLD, 12));
		agregarH.setSize(236, 33);
		agregarH.setLocation(25, 471);
		ActionListener altaHijo = e -> GestorPantallas.agregarHijos();
		agregarH.addActionListener(altaHijo);
		marco1.getContentPane().add(agregarH);

		//
		JButton quitarH = new JButton("QUITAR DECLARACIÓN DE HIJO");
		quitarH.setFont(new Font("Serif", Font.BOLD, 12));
		quitarH.setSize(236, 33);
		quitarH.setLocation(25, 515);
		quitarH.addActionListener(e -> {
			HijoDTO H_aux = new HijoDTO();
			H_aux = listaHijos.get(tablaHijos.getSelectedRow());
			modelhijos.removeRow(tablaHijos.getSelectedRow());
			listaHijos.remove(H_aux);

		});
		marco1.getContentPane().add(quitarH);

		//
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.setFont(new Font("Serif", Font.BOLD, 12));
		aceptar.setSize(143, 33);
		aceptar.setLocation(1021, 627);
		ActionListener acept = e -> {

			//HAGO LAS VALIDACIONES CUANDO SE APRIETA EL BOTON ACEPTAR

			LocalDate fechaActual = LocalDate.now();
			String error = "";
			boolean anio1 = true;
			boolean errores = false;
			boolean patente_valida = false;

			//valido la provincia -- Estos try y catch son para NullPointerException o sea para cuando no se completó el campo.
			try {
				provinciaCombo.getSelectedItem().toString();

			}
			catch (NullPointerException eprov) {
				error += "El campo Provincia es obligatorio \n";
			}
			//valido la localidad
			try {
				localidadCombo.getSelectedItem().toString();

			}
			catch (NullPointerException eloc) {
				error += "El campo Localidad es obligatorio  \n";
			}
			//valido la marca
			try {
				marcaCombo.getSelectedItem().toString();

			}
			catch(NullPointerException emarca) {
				error += "El campo Marca es obligatorio  \n";
			}
			//valido el modelo
			try {
				modeloCombo.getSelectedItem().toString();
			}
			catch (NullPointerException emodelo) {
				error += "El campo Modelo es obligatorio \n";
			}
			//valido el año
			try {
				if (Integer.parseInt(anioCombo.getSelectedItem().toString()) > fechaActual.getYear()) { // el año es mayor al año actual
					anio1 = false;
					error += "El año ingresado no es valido \n";
				}
			}
			catch (Exception efecha) {
				error += "El campo Año es obligatorio  \n";
			}

			if (motorTexto.getText().length() < 12) {
				error += "El Número de Motor debe tener 12 caracteres \n";
			}
			if (motorTexto.getText().matches("[A-Za-z0-9]+")== false ) {
				error += "El campo 'Motor' no puede contenter caracteres especiales   \n";
			}
			if (chasisText.getText().length() < 17) {
				error += "El Número de Chasis debe tener 17 caracteres \n";
			}
			if (chasisText.getText().matches("[A-Za-z0-9]+")== false ) {
				error += "El campo 'Chasis' no puede contenter caracteres especiales   \n";
			}
			if (patenteText.getText().length() < 6) {
				error += "La Patente debe tener entre 6 y 7 caracteres \n";
			}
			if (patenteText.getText().matches("[A-Za-z0-9]+")== false  ) {
				error += "El campo 'Patente' no puede contenter caracteres especiales   \n";
			}
			
			
			if (Pattern.matches("[a-zA-Z]{3,3}[0-9]{3,3}", patenteText.getText()) || Pattern.matches("[a-zA-Z]{2,2}[0-9]{3,3}[a-zA-Z]{2,2}", patenteText.getText())) { //formato patentes viejas 
				patente_valida =true;
			}
			else {
				error += "La patente ingresada es inválida   \n";
			}
			

			//VALIDO ALARMA
			try {
				String aux_alarma = grupoAlarma.getSelection().getActionCommand();
			}
			catch (Exception eAlarma) {
				error += "El campo Alarma es obligatorio  \n";
			}

			//VALIDO tuerca
			try {
				String aux_Tuerca = grupoTuerca.getSelection().getActionCommand();
			}
			catch (Exception eTuerca) {
				error += "El campo Tuerca es obligatorio  \n";
			}
			//VALIDO DISP
			try {
				String aux_Disp = grupoDisp.getSelection().getActionCommand();
			}
			catch (Exception eDispR) {
				error += "El campo Dispositivo de Rastreo es obligatorio  \n";
			}
			//VALIDO GARAGE
			try {
				String aux_garage = grupoGarage.getSelection().getActionCommand();

			}
			catch (Exception eGarage) {
				error += "El campo Garage es obligatorio  \n";
			}


			ClienteDTO auxiliar = new ClienteDTO();

			if(c.getNroCliente() == auxiliar.getNroCliente() ) error += "Seleccionar un cliente es obligatorio  \n";


			if (error != "") { //muestro los mensajes de error
				errores = true;
				JOptionPane.showMessageDialog(null, error);
			}

			if (errores == false && patente_valida) {


				VehiculoDTO vehiculodto = new VehiculoDTO();
				ModeloDTO modelodto = new ModeloDTO();
				MarcaDTO marcadto = new MarcaDTO();
				Marca aux_marca = new Marca();
				aux_marca=(Marca) marcaCombo.getSelectedItem();

				marcadto.setIdmarca(aux_marca.getIdMarca());
				marcadto.setNombre(aux_marca.getNombre());

				Modelo aux_modelo = new Modelo();
				aux_modelo= (Modelo) modeloCombo.getSelectedItem();

				modelodto.setIdmodelo(aux_modelo.getIdModelo());
				modelodto.setNombre(aux_modelo.getNombre());
				modelodto.setMarca(marcadto);
				modelodto.setPorcentaje(aux_modelo.getPorcentaje());

				vehiculodto.setAnio((int) anioCombo.getSelectedItem());
				vehiculodto.setChasis(chasisText.getText());
				vehiculodto.setModelo(modelodto);
				vehiculodto.setMotor(motorTexto.getText());
				vehiculodto.setPatente(patenteText.getText().toUpperCase()); //LAS PATENTES SE GUARDAN EN MAYUSCULA 

				vehiculodto.setPorcentaje(modelodto.getPorcentaje());

				String sumalocal= sumaFormattedTextField.getText();
				int tamsuma = sumaFormattedTextField.getText().length();
				String sumasinpuntos = "";
				for(int i=0; i<tamsuma; i++) {
					if(sumalocal.charAt(i) != '.') {
						sumasinpuntos += sumalocal.charAt(i);
					}
				}
				int valormil = Integer.parseInt(sumasinpuntos);
				float valordivmil= valormil/1000;

				vehiculodto.setPorcentaje(aux_modelo.getPorcentaje());

				int sumaAsegurada = GestorParametros.castSumaAsegurada(sumaFormattedTextField.getText());

				PolizaDTO pDTO = new PolizaDTO();
				pDTO.setKmPorAnio((Integer) kmSpinner.getValue());
				pDTO.setSumaasegurada(sumaAsegurada);
				pDTO.setSiniestro(SiniestroText.getText());

				if (grupoAlarma.getSelection().getActionCommand() == "SI") { //ALARMA
					pDTO.setAlarma(true);
				} else pDTO.setAlarma(false);
				if (grupoGarage.getSelection().getActionCommand() == "SI") { //GARAGE
					pDTO.setGarage(true);
				} else pDTO.setGarage(false);
				if (grupoDisp.getSelection().getActionCommand() == "SI") { //DISPOSITVO
					pDTO.setDispRastreo(true);
				} else pDTO.setDispRastreo(false);
				if (grupoTuerca.getSelection().getActionCommand() == "SI") { //TUERCAS
					pDTO.setTuercas(true);
				} else pDTO.setTuercas(false);

				Localidad aux_localidad = (Localidad) localidadCombo.getSelectedItem();
				Provincia aux_provincia = (Provincia) provinciaCombo.getSelectedItem();

				ProvinciaDTO provdto= new ProvinciaDTO();
				provdto.setId_provincia(aux_provincia.getId_provincia());
				provdto.setNombre(aux_provincia.getNombre());
				provdto.setPais(aux_provincia.getPais());

				LocalidadDTO locdto = new LocalidadDTO();
				locdto.setId_localidad(aux_localidad.getId_localidad());
				locdto.setCodigoPostal(aux_localidad.getCodigoPostal());
				locdto.setNombre(aux_localidad.getNombre());
				locdto.setPorcentaje(aux_localidad.getPorcentaje());
				locdto.setProvincia(provdto);

				DomicilioRiesgoDTO domriesgodto = new DomicilioRiesgoDTO();

				domriesgodto.setLocalidad(locdto);
				domriesgodto.setPorcentajeDomicilio(aux_localidad.getPorcentaje());

				GestorPantallas.Pantalla2Alta(c, vehiculodto, listaHijos,  pDTO, domriesgodto);
				marco1.dispose();
			}

		};
		aceptar.addActionListener(acept);
		marco1.getContentPane().add(aceptar);

		//
		JButton cancelar = new JButton("CANCELAR");
		cancelar.setFont(new Font("Serif", Font.BOLD, 12));
		cancelar.setSize(143, 33);
		cancelar.setLocation(1174, 627);
		ActionListener cancel = e -> {
			GestorPantallas.PantallaPrincipal(); // si cancelo vuelvo a la pantalla de MENU.
			GestorDB gdb = GestorDB.getInstance();
			gdb.cerrarConexion();
			marco1.dispose();
		};
		cancelar.addActionListener(cancel);
		marco1.getContentPane().add(cancelar);


	}

	static ArrayList<ClienteDTO> lista = new ArrayList <>();
	private static JTextField textApellido;

	/**
	 * @wbp.parser.entryPoint
	 */

	public static void buscarcliente() {
		// ------------- MARCO ---------------------------------------------
		final Marco marco1 = new Marco(1200,600,"BUSCAR CLIENTE");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		// -----------------------------------------------------------------

		// ----------------- ETIQUETAS -------------------------------------
		JLabel lblNroCliente = new JLabel("Nro. Cliente");
		lblNroCliente.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNroCliente.setBounds(25, 35, 205, 31);
		marco1.getContentPane().add(lblNroCliente);

		JLabel lblTipoDocumento = new JLabel("Tipo documento");
		lblTipoDocumento.setFont(new Font("Serif", Font.PLAIN, 18));
		lblTipoDocumento.setBounds(389, 35, 205, 31);
		marco1.getContentPane().add(lblTipoDocumento);

		JLabel lblNroDocumento = new JLabel("Nro. documento");
		lblNroDocumento.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNroDocumento.setBounds(764, 35, 205, 31);
		marco1.getContentPane().add(lblNroDocumento);

		JLabel lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNombreCliente.setBounds(25, 103, 205, 31);
		marco1.getContentPane().add(lblNombreCliente);

		JLabel lblApellidoCliente = new JLabel("Apellido Cliente");
		lblApellidoCliente.setFont(new Font("Serif", Font.PLAIN, 18));
		lblApellidoCliente.setBounds(389, 103, 205, 31);
		marco1.getContentPane().add(lblApellidoCliente);

		// -----------------------------------------------------------------

		// ------------------- CAMPOS DE TEXTO -----------------------------

		MaskFormatter mascaraNCLIENTE = null;
		try {
			mascaraNCLIENTE = new MaskFormatter("##-########");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		mascaraNCLIENTE.setPlaceholderCharacter('_');
		textNroCliente = new JFormattedTextField(mascaraNCLIENTE);
		textNroCliente.setBounds(161, 43, 196, 20);
		marco1.getContentPane().add(textNroCliente);
		textNroCliente.setColumns(10);

		textNroDoc = new JTextField();
		textNroDoc.setColumns(10);
		textNroDoc.setBounds(918, 43, 196, 20);
		marco1.getContentPane().add(textNroDoc);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(162, 111, 196, 20);
		marco1.getContentPane().add(textNombre);

		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(534, 111, 196, 20);
		marco1.getContentPane().add(textApellido);
		// -----------------------------------------------------------------

		// --------------- COMBO BOX ---------------------------------------
		final JComboBox comboTipoDoc = new JComboBox();
		comboTipoDoc.setBounds(534, 43, 196, 20);
		comboTipoDoc.setModel(new DefaultComboBoxModel(TipoDocumento.values()));
		marco1.getContentPane().add(comboTipoDoc);
		//comboTipoDoc.setRenderer(new MyComboBoxRenderer("SELECCIONE TIPO DOC"));
		//comboTipoDoc.setSelectedIndex(-1);
		// -----------------------------------------------------------------

		// --------------------------SCROLL PANE ---------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 258, 1089, 138);
		marco1.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
						"Nro. Cliente", "Apellido", "Nombre", "Tipo Documento", "Nro. Documento"
				}
				)
		{

			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		}	);

		scrollPane.setViewportView(table);
		// -----------------------------------------------------------------

		// ------------------------ BOTONES --------------------------------

		JButton botonBuscar = new JButton("BUSCAR");
		botonBuscar.setFont(new Font("Serif", Font.BOLD, 12));
		ActionListener accionBuscar = e -> {

			lista = GestorCliente.buscarCliente(textNroCliente.getText(), comboTipoDoc.getSelectedItem().toString(), textNroDoc.getText(), textNombre.getText(), textApellido.getText());
			int cantCliente = lista.size();
			int fila =0;
			Object[][] listaMuestra = new Object[cantCliente][6];
			for(ClienteDTO c:lista) {

				listaMuestra[fila][0] = c.getNroCliente();
				listaMuestra[fila][1] = c.getApellido();
				listaMuestra[fila][2] = c.getNombre();
				listaMuestra[fila][3] = c.getTipoDoc();
				listaMuestra[fila][4] = c.getDocumento();
				fila++;

			}

			DefaultTableModel model = new DefaultTableModel(listaMuestra,new String[] {"Nro. Cliente", "Apellido", "Nombre", "Tipo Documento", "Nro. Documento"}) {

				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int i, int i1) {
					return false;
				}
			};
			table.setModel(model);
		};

		botonBuscar.addActionListener(accionBuscar);
		botonBuscar.setBounds(25, 180, 143, 33);

		marco1.getContentPane().add(botonBuscar);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(e -> {

			if(table.getSelectedRow()>=0) {

				c = lista.get(table.getSelectedRow());
				if(c!=null) {
					model.setValueAt(c.getNroCliente(), 0, 0);
					model.setValueAt(c.getApellido(), 0, 1);
					model.setValueAt(c.getNombre(), 0, 2);
					model.setValueAt(c.getTipoDoc(), 0, 3);
					model.setValueAt(c.getDocumento(), 0, 4);

				}

				SiniestroText.setText(SubsistemaSiniestros.cantidadSiniestros());
				marco1.dispose();

			} else marco1.dispose();
		});
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(888, 527, 143, 33);
		marco1.getContentPane().add(btnAceptar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(1041, 527, 143, 33);
		marco1.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(e -> marco1.dispose());


		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// -----------------------------------------------------------------

	}


	public static void agregarhijo() {
		// --------------- MARCO --------------
		final Marco marco1 = new Marco(700,600,"AGREGAR DECLARACIÓN DE HIJO");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// -----------------------------------------------------------------

		// -------------------- ETIQUETAS ----------------------------------
		JLabel fechaN = new JLabel ("(*) Fecha de nacimiento");
		fechaN.setFont(new Font("Serif", Font.PLAIN, 18));
		fechaN.setSize(222, 33);
		fechaN.setLocation(37, 62);
		marco1.getContentPane().add(fechaN);

		JLabel lblSexo = new JLabel("(*) Sexo");
		lblSexo.setFont(new Font("Serif", Font.PLAIN, 18));
		lblSexo.setBounds(37, 151, 180, 33);
		marco1.getContentPane().add(lblSexo);

		JLabel lblEstadoCivil = new JLabel("(*) Estado Civil");
		lblEstadoCivil.setFont(new Font("Serif", Font.PLAIN, 18));
		lblEstadoCivil.setBounds(37, 227, 180, 33);
		marco1.getContentPane().add(lblEstadoCivil);


		// -----------------------------------------------------------------

		// --------------------- FORMATTED TEXT FIELD ----------------------

		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##-##-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		mascara.setPlaceholderCharacter('_');
		final JFormattedTextField fechaNFormattedTextField = new JFormattedTextField(mascara);
		fechaNFormattedTextField.setValue("");
		fechaNFormattedTextField.setBounds(232, 69, 196, 25);
		marco1.getContentPane().add(fechaNFormattedTextField);
		// -----------------------------------------------------------------

		// ------------------- COMBO BOX -----------------------------------
		final JComboBox comboBox_EstadoCivil = new JComboBox();
		comboBox_EstadoCivil.setBounds(232, 234, 203, 25);
		comboBox_EstadoCivil.setModel(new DefaultComboBoxModel(EstadoCivil.values()));
		marco1.getContentPane().add(comboBox_EstadoCivil);
		comboBox_EstadoCivil.setRenderer(new MyComboBoxRenderer("SELECCIONE ESTADO CIVIL"));
		comboBox_EstadoCivil.setSelectedIndex(-1);


		JComboBox sexo = new JComboBox();
		sexo.setBounds(232, 158, 203, 23);
		marco1.getContentPane().add(sexo);
		sexo.setModel(new DefaultComboBoxModel(Sexo.values()));
		sexo.setRenderer(new MyComboBoxRenderer("SELECCIONE SEXO"));
		sexo.setSelectedIndex(-1);
		// -----------------------------------------------------------------

		// ------------------- BOTONES -------------------------------------
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(383, 493, 143, 33);
		marco1.getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(e -> { //SE HACEN LAS VALIDACIONES CUANDO SE PRESIONA ACEPTAR
			//la idea es recolectar en el ArrayList errores, todos los errores que surjan de validacion y pasarselo para que la pantalla de error muestre un popup
			boolean fechaValida = true;
			boolean edadHijo = true;
			boolean campoCompleto = true;
			boolean estadoCivil = true;

			String error = "";
			String fechaVacia = "__-__-____";

			if (fechaNFormattedTextField.getText().equals(fechaVacia)){
				campoCompleto = false;
				error += "No se completó el campo Fecha de Nacimiento que es obligatorio \n";
			}

			if (campoCompleto == true ) { //si se completó el campo fecha
				String fecha = fechaNFormattedTextField.getText();

				//VERIFICO QUE SEA UNA FECHA VALIDA
				try {
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate fechaNac = LocalDate.parse(fecha, fmt);
					LocalDate ahora = LocalDate.now();
					Period periodo = Period.between(fechaNac, ahora);
					//VERIFICO QUE LA EDAD ESTE ENTRE 18 y 30 AÑOS
					if (periodo.getYears() < 18 || periodo.getYears() > 30) {
						edadHijo = false;
						error += "La edad del hijo no está entre los 18 y 30 años \n";
					}
				}
				catch (Exception eFecha) {
					fechaValida = false;
					error += "La fecha ingresada es inválida\n";

				}
			}

			HijoDTO hijoDTO = new HijoDTO();
			try { // SI NO SE SELECCIONÓ EL SEXO DEBERÍA MOSTRAR UN MENSAJE DE ERROR Y DAR LA OPORTUNIDAD DE VOLVER A SELECCIONAR
				String aux_sexo = sexo.getSelectedItem().toString();
			}
			catch (Exception excep) {

				error += "El campo 'Sexo' que es obligatorio \n";
			}

			try {
				String aux_estadoCivil = comboBox_EstadoCivil.getSelectedItem().toString();

			}
			catch (Exception eEstadoC) {
				error += "El campo 'Estado Civil' es obligatorio \n";
				estadoCivil = false;
			}

			if (error == "") {
				JOptionPane.showMessageDialog(null, "Hijo agregado con exito");
			}else JOptionPane.showMessageDialog(null, error);

			if (fechaValida && error=="") {
				hijoDTO.setEstadoCivil(comboBox_EstadoCivil.getSelectedItem().toString());
				hijoDTO.setSexo(sexo.getSelectedItem().toString());
				hijoDTO.setFechaNac(fechaNFormattedTextField.getText());
				Object[] aux = {hijoDTO.getFechaNac(),hijoDTO.getSexo(),hijoDTO.getEstadoCivil()};
				listaHijos.add(hijoDTO);
				modelhijos.addRow(aux);
				marco1.dispose();
			}
			/*crear un arreglo de hijos global en donde se guardaran los hijos declarados hasta que se cree la poliza para
			asi poder mostrarlos en la tabla, el metodo eliminar debe agarrar la ubicacion del hijo en la tabla y borrarlo de la lista.
			 */

		}
				);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(536, 493, 143, 33);
		marco1.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(e -> marco1.dispose());
		// -----------------------------------------------------------------

	}
}