package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tp.disenio.clases.Cuota;
import tp.disenio.clases.Mensual;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.Semestral;
import tp.disenio.gestores.GestorCobro;
import tp.disenio.gestores.GestorPantallas;

public class PantallaRegistrarPago {

	/**
	 * @wbp.parser.entryPoint
	 */

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
	public static void start (Poliza p) {

		// ---------- MARCO ------------
		final Marco marco1 = new Marco(1333,730,"REGISTRAR PAGO POLIZA");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JScrollPane scrollPanePoliza = new JScrollPane();
		scrollPanePoliza.setBounds(25, 62, 1267, 43);
		marco1.getContentPane().add(scrollPanePoliza);



		JScrollPane scrollPaneCuotas = new JScrollPane();
		scrollPaneCuotas.setBounds(25, 365, 977, 150);
		marco1.getContentPane().add(scrollPaneCuotas);

		// ---------- TABLAS ------------


		if(p==null) {
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


		JButton aceptar = new JButton("ACEPTAR");
		aceptar.setFont(new Font("Serif", Font.BOLD, 12));
		aceptar.setSize(143, 33);
		aceptar.setLocation(1021, 627);
		marco1.getContentPane().add(aceptar);

		JButton cancelar = new JButton("CANCELAR");
		cancelar.setFont(new Font("Serif", Font.BOLD, 12));
		cancelar.setSize(143, 33);
		cancelar.setLocation(1174, 627);
		ActionListener cancel = e -> {
			GestorPantallas.PantallaPrincipal(); // si cancelo vuelvo a la pantalla de MENU
			marco1.dispose();
		};
		cancelar.addActionListener(cancel);
		marco1.getContentPane().add(cancelar);

		ActionListener accionaceptar = e ->{
			int seleccionadas [];
			seleccionadas = tablaCuotas.getSelectedRows();
			if (seleccionadas.length > 0) {
				//CON GESTOR COBROS, CAMBIAR A PAGAS
			}
		};
		aceptar.addActionListener(accionaceptar);

		////////////////////////////////////CUANDO YA BUSCO EL CLIENTE

		if (p != null) {
			GestorCobro gc = GestorCobro.getInstance();
			Calendar cal = Calendar.getInstance();
			float descuento = gc.obtenerDescuentoPorAdelanto()/100;
			float interes = gc.obtenerTasaInteres() /100;
			
			textNCliente.setText(p.getCliente().getNroCliente());
			textNombreC.setText(p.getCliente().getNombre());
			textApellidoC.setText(p.getCliente().getApellido());
			textMarca.setText(p.getVehiculo().getModelo().getMarca().getNombre());
			textModelo.setText(p.getVehiculo().getModelo().getNombre());
			textPatente.setText(p.getVehiculo().getPatente());
			
			System.out.println(p.getForma_pago().getNombre()); //BORRAR
			System.out.println("TIPO" +p.getForma_pago().getClass()); //BORRAR ACA ESTA EL PROBLEMA; LO LEVANTA COMO SEMESTRAL
			if  (p.getForma_pago().getNombre().equals("MENSUAL")) {
				System.out.println("Entro1");
				ArrayList<Cuota> lista = new ArrayList<>();
				lista = ((Mensual)p.getForma_pago()).getCuotas();
				int fila =0;
				boolean sinVencer = false;
				Object[][] listaMuestra = new Object[6][6];
				for(Cuota c : lista) {
					if(!c.isPagada()) {

						listaMuestra[fila][0] = c.getId_cuota();
						listaMuestra[fila][1] = c.getFecha_vencimiento();
						listaMuestra[fila][2] = c.getMonto();
						int diaV = Integer.parseInt(c.getFecha_vencimiento().substring(0, 2));
						int mesV = Integer.parseInt(c.getFecha_vencimiento().substring(3, 5));
						int anioV = Integer.parseInt(c.getFecha_vencimiento().substring(6, 10));
						
						if (sinVencer) { //Si la cuota anterior no esta vencida, entonces la siguiente seria pagada por adelantado
							listaMuestra[fila][3] = c.getMonto()*(1-descuento);
						}
						else {
							if (cal.YEAR > anioV) { //Vencida por año
								listaMuestra[fila][3] = anioV/*c.getMonto()*(1+interes)*/;
							}
							else if  (cal.MONTH > mesV){ //Vencida por mes
								listaMuestra[fila][3] = mesV/*c.getMonto()*(1+interes)*/;
							}
							else if (cal.DAY_OF_MONTH > diaV) {//Vencida por dia
								listaMuestra[fila][3] = diaV/*c.getMonto()*(1+interes)*/;
							}
							else { //AL DIA 
								sinVencer = true; 
								listaMuestra[fila][3] = c.getMonto();
							}
						}
						
						fila++;
					}

				}
				modelCuota =new DefaultTableModel(
						listaMuestra,
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
				System.out.println("Entro2");
				Cuota unica;
				unica = ((Semestral)p.getForma_pago()).getCuota1();
				Object[][] listaMuestra = new Object[6][6];	
				int diaV = Integer.parseInt(unica.getFecha_vencimiento().substring(0, 2));
				int mesV = Integer.parseInt(unica.getFecha_vencimiento().substring(3, 5));
				int anioV = Integer.parseInt(unica.getFecha_vencimiento().substring(6, 10));
				listaMuestra[0][0]="Unica Cuota";
				listaMuestra[0][1]=((Semestral)p.getForma_pago()).getFecha_Vencimiento();
				listaMuestra[0][2]=((Semestral)p.getForma_pago()).getCuota1().getMonto();
				if (cal.YEAR > anioV) { //Vencida por año
					listaMuestra[0][3] = 2/*unica.getMonto()*(1+interes)*/;
				}
				else if  (cal.MONTH > mesV){ //Vencida por mes
					listaMuestra[0][3] = 3/*unica.getMonto()*(1+interes)*/;
				}
				else if (cal.DAY_OF_MONTH > diaV) {//Vencida por dia
					listaMuestra[0][3] = 4/*unica.getMonto()*(1+interes)*/;
				}
				else { //AL DIA 
					listaMuestra[0][3] = unica.getMonto();
				}
	

				modelCuota =new DefaultTableModel(
						listaMuestra,
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

				//MOSTRAR CUOTA UNICA
			}

		}

		//


	}






}
