package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import tp.disenio.DAO.DAOCuota;
import tp.disenio.DAO.DAOReciboPago;
import tp.disenio.clases.Cuota;
import tp.disenio.clases.Mensual;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.Semestral;
import tp.disenio.gestores.GestorPantallas;
import tp.disenio.gestores.GestorPoliza;

public class PantallaBuscarPoliza {
	private static JTextField textFieldNPoliza;
	/**
	 * @wbp.parser.entryPoint
	 */

	private static JTable tablaPoliza = new JTable();

	static DefaultTableModel modelPoliza =new DefaultTableModel(
			new Object[][] {},
			new String[] {
					"Nro. Poliza", "Nro. Cliente", "Apellido", "Nombre", "Tipo documento", "Nro. Documento", "Ultimo pago"
			}
			){

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int i, int i1) {
			return false;
		}
	};

	/**
	 * @wbp.parser.entryPoint
	 */
	private static Poliza poliza_encontrada = new Poliza();
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start() {
		// --------------- MARCO --------------
		final Marco marco1 = new Marco(1200,600,"BUSCAR POLIZA");
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 263, 1063, 126);
		marco1.getContentPane().add(scrollPane);

		tablaPoliza.setModel(modelPoliza);
		scrollPane.setViewportView(tablaPoliza);
		tablaPoliza.getTableHeader().setReorderingAllowed(false);
		tablaPoliza.getTableHeader().setResizingAllowed(false);
		// ------------------------------------
		// --------------- ETIQUETAS --------------
		JLabel lblNroPoliza = new JLabel("Nro Poliza");
		lblNroPoliza.setBounds(43, 83, 142, 38);
		lblNroPoliza.setFont(new Font("Serif", Font.PLAIN, 19));
		marco1.getContentPane().add(lblNroPoliza);
		
		JLabel listaVacia = new JLabel("No se econtraron coincidencias con la busqueda");
		listaVacia.setHorizontalAlignment(SwingConstants.CENTER);
		listaVacia.setBounds(43, 220, 300, 25);
		listaVacia.setFont(new Font("Serif", Font.PLAIN, 16));
		listaVacia.setVisible(false);
		marco1.getContentPane().add(listaVacia);
		// --------------- CAMPOS DE TEXTO --------------
		textFieldNPoliza = new JTextField();
		textFieldNPoliza.setBounds(155, 96, 196, 20);
		marco1.getContentPane().add(textFieldNPoliza);
		textFieldNPoliza.setColumns(10);
		textFieldNPoliza.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 12;
				if(e.getKeyChar()!='1' && e.getKeyChar()!='2' && e.getKeyChar()!='3' && e.getKeyChar()!='4' && e.getKeyChar()!='5' && e.getKeyChar()!='6' && e.getKeyChar()!='7' && e.getKeyChar()!='8' && e.getKeyChar()!='9' && e.getKeyChar()!='0') e.consume();
				else if(textFieldNPoliza.getText().length() > max) {
					e.consume();
				}
			}
		});

		// --------------- BOTONES --------------
		JButton btnBuscarPoliza = new JButton("BUSCAR POLIZA");
		btnBuscarPoliza.setBounds(43, 182, 143, 33);
		btnBuscarPoliza.setFont(new Font("Serif", Font.BOLD, 12));
		marco1.getContentPane().add(btnBuscarPoliza);

		ActionListener accionBuscar = e -> {
			//DEVUELVE UNA INSTANCIA DE POLIZA PARA MOSTRAR POR PANTALLA
			GestorPoliza gp = GestorPoliza.getInstance();
			if(textFieldNPoliza.getText().length() != 13) {
				JOptionPane.showMessageDialog(null, "El Nro. de poliza ingresado es inválido");
			}
			else {
				Boolean encontrada = gp.polizaExiste(textFieldNPoliza.getText());
				if (!encontrada) {
					JOptionPane.showMessageDialog(null, "No se encontró ninguna poliza bajo ese Nro");
					listaVacia.setVisible(true);
				}
				if (encontrada ) {
					listaVacia.setVisible(false);
					poliza_encontrada = gp.buscarPoliza(textFieldNPoliza.getText());
					Object[][] listaMuestra = new Object[1][7];

					listaMuestra[0][0] = poliza_encontrada.getNroPoliza();
					listaMuestra[0][1] = poliza_encontrada.getCliente().getNroCliente();
					listaMuestra[0][2] = poliza_encontrada.getCliente().getApellido();
					listaMuestra[0][3] = poliza_encontrada.getCliente().getNombre();
					listaMuestra[0][4] = poliza_encontrada.getCliente().getTipoDocumento();
					listaMuestra[0][5] = poliza_encontrada.getCliente().getDocumento();
					if (poliza_encontrada.getForma_pago().getNombre().equals("MENSUAL")) {
						ArrayList<Cuota> cuotas = new ArrayList<>();
						cuotas = ((Mensual)poliza_encontrada.getForma_pago()).getCuotas();
						ArrayList<Cuota> todascuotas = DAOCuota.recuperarTodasCuotas(poliza_encontrada.getNroPoliza());
						boolean flag = true;
						int i =1;

						if (!todascuotas.get(0).isPagada()) {
							listaMuestra[0][6] = "aun no se han registrado pagos";
							flag = false;
						}

						while (flag) {

							if (todascuotas.get(i).isPagada()) {
								listaMuestra[0][6] = DAOReciboPago.obtenerfechapago(todascuotas.get(i).getRecibo());
								i++;
							}else {
								i -=1;
								listaMuestra[0][6] = DAOReciboPago.obtenerfechapago(todascuotas.get(i).getRecibo());
								flag = false;
							}
							if(i==5) {
								flag=false;
							}
						}

					}
					else {

						Cuota c = ((Semestral) poliza_encontrada.getForma_pago()).getCuota1();
						if (c.isPagada()) {
							listaMuestra[0][6] = DAOReciboPago.obtenerfechapago(c.getRecibo());
						}
						else {
							listaMuestra[0][6] = "aun no se han registrado pagos";
						}



					}



					DefaultTableModel model = new DefaultTableModel(listaMuestra,new String[] { "Nro. Poliza", "Nro. Cliente", "Apellido", "Nombre", "Tipo documento", "Nro. Documento", "Ultimo pago"}) {

						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int i, int i1) {
							return false;
						}
					};
					tablaPoliza.setModel(model);
				}
			}

		};

		btnBuscarPoliza.addActionListener(accionBuscar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(1041, 527, 143, 33);
		marco1.getContentPane().add(btnCancelar);
		ActionListener accionCancelar = e -> {

			GestorPantallas.registrarPago(null, null, null, -1);
			marco1.dispose();

		};
		btnCancelar.addActionListener(accionCancelar);
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(888, 527, 143, 33);
		marco1.getContentPane().add(btnAceptar);
		ActionListener accionAceptar = e -> {
			if(tablaPoliza.getSelectedRow()<0) {
				GestorPantallas.registrarPago(null,null,null,-1);
				marco1.dispose();
			}else {
				GestorPantallas.registrarPago(poliza_encontrada,null,null,-1);
				marco1.dispose();
			}
		};
		btnAceptar.addActionListener(accionAceptar);


		// -----------------------------------------------------------------
	}
	protected static void close() {
		// TODO Auto-generated method stub
		GestorPantallas.registrarPago(null, null, null, -1);
	}
}
