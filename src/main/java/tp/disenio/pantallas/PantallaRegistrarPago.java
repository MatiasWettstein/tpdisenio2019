package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tp.disenio.DTO.CuotaDTO;
import tp.disenio.clases.Cuota;
import tp.disenio.clases.Mensual;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.Semestral;
import tp.disenio.gestores.GestorCobro;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPantallas;
import javax.swing.SwingConstants;

public class PantallaRegistrarPago {

	/**
	 * @wbp.parser.entryPoint
	 */

	static double  montoaPagar_semestral;
	static double  montoaPagar_mensual;

	private static JTable tablaPoliza = new JTable();
	static DefaultTableModel modelPoliza =new DefaultTableModel(
			new Object[][] {},
			new String[] {
					"Nro.Poliza", "Inicio vigencia", "Fin vigencia", "Estado poliza"
			}
			){

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int i, int i1) {
			return false;
		}
	};


	private static JTable tablaCuotas = new JTable();

	static DefaultTableModel modelCuota =new DefaultTableModel(
			new Object[][] {},
			new String[] {
					"Cuota", "Fecha vencimiento", "Valor original", "Valor actual"
			}
			){

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int i, int i1) {
			return false;
		}
	};
	private static JTextField textNCliente;
	private static JTextField textNombreC;
	private static JTextField textApellidoC;
	private static JTextField textMarca;
	private static JTextField textModelo;
	private static JTextField textPatente;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start (Poliza p, ArrayList<CuotaDTO> cuotas, CuotaDTO c1, double montoTotal) {

		// ---------- MARCO ------------
		final Marco marco1 = new Marco(1333,730,"REGISTRAR PAGO POLIZA");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		marco1.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});
		tablaCuotas.getTableHeader().setReorderingAllowed(false);
		tablaCuotas.getTableHeader().setResizingAllowed(false);
		tablaPoliza.getTableHeader().setReorderingAllowed(false);
		tablaPoliza.getTableHeader().setResizingAllowed(false);
		JScrollPane scrollPanePoliza = new JScrollPane();
		scrollPanePoliza.setBounds(25, 62, 1267, 43);
		marco1.getContentPane().add(scrollPanePoliza);

		JScrollPane scrollPaneCuotas = new JScrollPane();
		scrollPaneCuotas.setBounds(25, 365, 977, 150);
		marco1.getContentPane().add(scrollPaneCuotas);

		// ---------- TABLAS ------------

		montoaPagar_mensual=0;
		montoaPagar_semestral=0;
		if(p==null) {
			modelPoliza =new DefaultTableModel(
					new Object[][] {},
					new String[] {
							"Nro.Poliza", "Inicio vigencia", "Fin vigencia", "Estado poliza"
					}
					){

				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int i, int i1) {
					return false;
				}
			};

			tablaPoliza.setModel(modelPoliza);
			scrollPanePoliza.setViewportView(tablaPoliza);
		} else {

			modelPoliza =new DefaultTableModel(
					new Object[][] {
						{p.getNroPoliza(),p.getInicio_vigencia(),p.getFin_vigencia(),p.getEstado_poliza()}
					},
					new String[] {
							"Nro.Poliza", "Inicio vigencia", "Fin vigencia", "Estado poliza"
					}
					){

				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int i, int i1) {
					return false;
				}
			};

			tablaPoliza.setModel(modelPoliza);
			scrollPanePoliza.setViewportView(tablaPoliza);
		}

		tablaCuotas.setModel(modelCuota);
		scrollPaneCuotas.setViewportView(tablaCuotas);

		// ---------------------------------
		// ---------- ETIQUETAS ------------
		JLabel lblTitular = new JLabel("Titular del seguro");
		lblTitular.setBounds(25, 130, 192, 25);
		lblTitular.setFont(new Font("Serif", Font.BOLD, 19));
		marco1.getContentPane().add(lblTitular);

		JLabel lblNroCliente = new JLabel("Nro. Cliente ");
		lblNroCliente.setBounds(25, 176, 129, 25);
		lblNroCliente.setFont(new Font("Serif", Font.PLAIN, 18));
		marco1.getContentPane().add(lblNroCliente);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Serif", Font.PLAIN, 18));
		lblApellido.setBounds(706, 176, 129, 25);
		marco1.getContentPane().add(lblApellido);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNombre.setBounds(373, 176, 129, 25);
		marco1.getContentPane().add(lblNombre);

		JLabel lblInformacinDelVehiculo = new JLabel("Información del vehículo");
		lblInformacinDelVehiculo.setFont(new Font("Serif", Font.BOLD, 19));
		lblInformacinDelVehiculo.setBounds(25, 231, 273, 25);
		marco1.getContentPane().add(lblInformacinDelVehiculo);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Serif", Font.PLAIN, 18));
		lblMarca.setBounds(25, 272, 129, 25);
		marco1.getContentPane().add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Serif", Font.PLAIN, 18));
		lblModelo.setBounds(373, 272, 129, 25);
		marco1.getContentPane().add(lblModelo);

		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setFont(new Font("Serif", Font.PLAIN, 18));
		lblPatente.setBounds(706, 272, 129, 25);
		marco1.getContentPane().add(lblPatente);

		JLabel lblCuotasPendientesDe = new JLabel("Cuotas pendientes de pago");
		lblCuotasPendientesDe.setFont(new Font("Serif", Font.BOLD, 19));
		lblCuotasPendientesDe.setBounds(25, 329, 273, 25);
		marco1.getContentPane().add(lblCuotasPendientesDe);
		
		JLabel listaVacia = new JLabel("Todas las coutas estan pagas");
		listaVacia.setHorizontalAlignment(SwingConstants.CENTER);
		listaVacia.setBounds(250, 331, 192, 25);
		listaVacia.setFont(new Font("Serif", Font.PLAIN, 16));
		listaVacia.setVisible(false);
		marco1.getContentPane().add(listaVacia);

		// ---------- CAMPOS DE TEXTO ------------

		textNCliente = new JTextField();
		textNCliente.setEnabled(true);
		textNCliente.setEditable(false);
		textNCliente.setBounds(131, 181, 196, 20);
		marco1.getContentPane().add(textNCliente);
		textNCliente.setColumns(10);

		textNombreC = new JTextField();
		textNombreC.setEnabled(true);
		textNombreC.setEditable(false);
		textNombreC.setColumns(10);
		textNombreC.setBounds(449, 181, 196, 20);
		marco1.getContentPane().add(textNombreC);

		textApellidoC = new JTextField();
		textApellidoC.setEnabled(true);
		textApellidoC.setEditable(false);
		textApellidoC.setColumns(10);
		textApellidoC.setBounds(806, 181, 196, 20);
		marco1.getContentPane().add(textApellidoC);

		textMarca = new JTextField();
		textMarca.setEnabled(true);
		textMarca.setEditable(false);
		textMarca.setColumns(10);
		textMarca.setBounds(131, 277, 196, 20);
		marco1.getContentPane().add(textMarca);

		textModelo = new JTextField();
		textModelo.setEnabled(true);
		textModelo.setEditable(false);
		textModelo.setColumns(10);
		textModelo.setBounds(449, 277, 196, 20);
		marco1.getContentPane().add(textModelo);


		textPatente = new JTextField();
		textPatente.setEnabled(true);
		textPatente.setEditable(false);
		textPatente.setColumns(10);
		textPatente.setBounds(806, 277, 196, 20);
		marco1.getContentPane().add(textPatente);



		////////////////////////////////////CUANDO YA BUSCO LA POLIZA
		GestorCobro gc = GestorCobro.getInstance();
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate ahora = LocalDate.now();
		ahora.format(fmt1);
		Object[][] listaMuestraMensual = new Object[6][6];
		Object[][] listaMuestraSemestral = new Object[1][6];
		ArrayList<Double> montosActualizadosCuotas = new ArrayList<>();


		if (p == null) {

			modelCuota =new DefaultTableModel(
					new Object[][] {},
					new String[] {
							"Cuota", "Fecha vencimiento", "Valor original", "Valor actual"
					}
					){

				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int i, int i1) {
					return false;
				}
			};


			tablaCuotas.setModel(modelCuota);
			scrollPaneCuotas.setViewportView(tablaCuotas);
		}
		else{
			textNCliente.setText(p.getCliente().getNroCliente());
			textNombreC.setText(p.getCliente().getNombre());
			textApellidoC.setText(p.getCliente().getApellido());
			textMarca.setText(p.getVehiculo().getModelo().getMarca().getNombre());
			textModelo.setText(p.getVehiculo().getModelo().getNombre());
			textPatente.setText(p.getVehiculo().getPatente());

			if  (p.getForma_pago().getNombre().equals("MENSUAL")) {
				ArrayList<Cuota> lista = new ArrayList<>();
				lista = ((Mensual)p.getForma_pago()).getCuotas();
				int fila =0;
				int i =1;
				DecimalFormat dec = new DecimalFormat("#.##");
				for(Cuota c : lista) {
					if(!c.isPagada()) {

						listaMuestraMensual[fila][0] =  i++; //acá se muestra el numero de cuotas - NO LA ID DE LA CUOTA
						listaMuestraMensual[fila][1] = c.getFecha_vencimiento();
						listaMuestraMensual[fila][2] = c.getMonto();

						DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						LocalDate vencimiento = LocalDate.parse(c.getFecha_vencimiento(), fmt);
						Period periodo = Period.between(vencimiento, ahora);

						if (ahora.isBefore(vencimiento)) { //si la fecha de vencimiento es posterior a la actual
							listaMuestraMensual[fila][3] = dec.format(gc.aplicarDescuento((float) c.getMonto()));
							montosActualizadosCuotas.add((double) gc.aplicarDescuento((float) c.getMonto()));

						}else {

							if (periodo.getYears()>0) { //si está vencida por un año o mas
								listaMuestraMensual[fila][3] =  dec.format(gc.aplicarInteres((float) c.getMonto()));
								montosActualizadosCuotas.add((double) gc.aplicarInteres((float) c.getMonto()));

							}
							else if (periodo.getMonths()>0 || periodo.getMonths()<12) {//Vencida por mes
								listaMuestraMensual[fila][3] = dec.format(gc.aplicarInteres((float) c.getMonto()));
								montosActualizadosCuotas.add((double) gc.aplicarInteres((float) c.getMonto()));
							}
							else if (periodo.getDays()<31) {//Vencida por dia
								listaMuestraMensual[fila][3] =  dec.format(gc.aplicarInteres((float) c.getMonto()));
								montosActualizadosCuotas.add((double) gc.aplicarInteres((float) c.getMonto()));
							}
						}

						fila++;
					}
					if (listaMuestraMensual.length==0) {
						listaVacia.setVisible(true);
					}

				}
				modelCuota =new DefaultTableModel(
						listaMuestraMensual,
						new String[] {
								"Cuota", "Fecha vencimiento", "Valor original", "Valor actual"
						}
						){

					private static final long serialVersionUID = 1L;

					@Override
					public boolean isCellEditable(int i, int i1) {
						return false;
					}
				};
				tablaCuotas.setModel(modelCuota);
				scrollPaneCuotas.setViewportView(tablaCuotas);

			}else {
				DecimalFormat dec = new DecimalFormat("#.##");
				Cuota unica;
				unica = ((Semestral)p.getForma_pago()).getCuota1();
				if(!unica.isPagada()) {
					double montoOriginal = ((Semestral)p.getForma_pago()).getCuota1().getMonto();
					listaMuestraSemestral[0][0]="Unica Cuota";
					listaMuestraSemestral[0][1]=((Semestral)p.getForma_pago()).getFecha_Vencimiento();
					listaMuestraSemestral[0][2]= montoOriginal;
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate vencimiento = LocalDate.parse(((Semestral)p.getForma_pago()).getFecha_Vencimiento(), fmt);
					Period periodo = Period.between(vencimiento, ahora);

					if (ahora.isBefore(vencimiento)) { //si la fecha de vencimiento es posterior a la actual
						listaMuestraSemestral[0][3] = dec.format(gc.aplicarDescuento((float) montoOriginal));
						montoaPagar_semestral = gc.aplicarDescuento((float) montoOriginal);
					}
					else {
						if (periodo.getYears()>0) { //si está vencida por un año o mas
							listaMuestraSemestral[0][3] =  dec.format(gc.aplicarInteres((float) montoOriginal));
							montoaPagar_semestral =  gc.aplicarInteres((float) montoOriginal);
						}
						else if (periodo.getMonths()>0 || periodo.getMonths()<12) {//Vencida por mes
							listaMuestraSemestral[0][3] =  dec.format(gc.aplicarInteres((float) montoOriginal));
							montoaPagar_semestral =  gc.aplicarInteres((float) montoOriginal);
						}
						else if (periodo.getDays()<31) {//Vencida por dia
							listaMuestraSemestral[0][3] =  dec.format(gc.aplicarInteres((float) montoOriginal));
							montoaPagar_semestral =  gc.aplicarInteres((float) montoOriginal);
						}
					}



					modelCuota =new DefaultTableModel(
							listaMuestraSemestral,
							new String[] {
									"Cuota", "Fecha vencimiento", "Valor original", "Valor actual"
							}
							){

						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int i, int i1) {
							return false;
						}
					};
					tablaCuotas.setModel(modelCuota);
					scrollPaneCuotas.setViewportView(tablaCuotas);
				}
				else {
					listaVacia.setVisible(true);
				}
			}

		}

		// ---------- BOTONES ------------


		JButton buscarP = new JButton("BUSCAR POLIZA");
		ActionListener buscarCliente = e -> {
			GestorPantallas.buscarPoliza();
			marco1.dispose();
		};

		buscarP.addActionListener(buscarCliente);
		buscarP.setFont(new Font("Serif", Font.BOLD, 12));
		buscarP.setBounds(25, 10, 222, 33);
		marco1.getContentPane().add(buscarP);

		JButton cancelar = new JButton("CANCELAR");
		cancelar.setFont(new Font("Serif", Font.BOLD, 12));
		cancelar.setSize(143, 33);
		cancelar.setLocation(1174, 627);
		ActionListener cancel = e -> {
			GestorPantallas.PantallaPrincipal(); // si cancelo vuelvo a la pantalla de MENU
			GestorDB gdb = GestorDB.getInstance();
			gdb.cerrarConexion(); //cierro la conexion cuando vuelvo a la pantalla principal
			marco1.dispose();
		};
		cancelar.addActionListener(cancel);
		marco1.getContentPane().add(cancelar);

		JButton aceptar = new JButton("ACEPTAR");
		aceptar.setFont(new Font("Serif", Font.BOLD, 12));
		aceptar.setSize(143, 33);
		aceptar.setLocation(1021, 627);
		marco1.getContentPane().add(aceptar);
		ActionListener accionaceptar = e ->{
			if(p.getForma_pago().getNombre().equals("MENSUAL")) {
				ArrayList<Cuota> listaCuotas=((Mensual)p.getForma_pago()).getCuotas();
				int seleccionadas [];
				seleccionadas = tablaCuotas.getSelectedRows();
				if (seleccionadas.length > 0) {
					boolean flag=true;

					if(seleccionadas[0]>0) {
						flag = false;
					}
					else {
						for (int i =0; i<seleccionadas.length;i++) {
							if (seleccionadas[i]>=seleccionadas.length) {
								flag = false;
							}
						}
					}

					if(flag==false) {

						JOptionPane.showMessageDialog(null, "Falta cancelar cuota anteriores.", "Error", JOptionPane.WARNING_MESSAGE);

					} else {

						int cuotas_seleccionadas [];
						cuotas_seleccionadas = tablaCuotas.getSelectedRows();
						ArrayList<CuotaDTO> cuotasAPagar = new ArrayList<>();
						ArrayList<Double> montoSeleccionado = new ArrayList<>();
						if (cuotas_seleccionadas.length > 0) {
							int tamSeleccionadas = cuotas_seleccionadas.length;
							for (int i =0; i<tamSeleccionadas; i++) { //ARMO EL ARRAYLIST DE LAS CUOTAS A PAGAR
								CuotaDTO cuotaDTO = new CuotaDTO();
								Cuota cuota = new Cuota();
								cuota = listaCuotas.get(i);
								cuotaDTO.setId_cuota(cuota.getId_cuota());
								cuotasAPagar.add(cuotaDTO);
								montoSeleccionado.add(montosActualizadosCuotas.get(i));
							}
						}
						montoaPagar_mensual = gc.calcularMontoTotal(montoSeleccionado);
						GestorPantallas.registrarPago2(p, cuotasAPagar, null, montoaPagar_mensual);
						marco1.dispose();

					}
				}
			}
			else { //si es de forma semestral solo tengo una cuota que pagar

				//el monto total a pagar es la cuota seleccionada con su valor actual
				CuotaDTO cuota = new CuotaDTO();

				cuota.setId_cuota(((Semestral)p.getForma_pago()).getCuota1().getId_cuota());

				GestorPantallas.registrarPago2(p, null, cuota, montoaPagar_semestral);
				marco1.dispose();
			}


		};
		aceptar.addActionListener(accionaceptar);


	}
	protected static void close() {
		// TODO Auto-generated method stub
		GestorPantallas.PantallaPrincipal();
	}





}
