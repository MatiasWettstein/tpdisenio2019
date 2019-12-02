package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tp.disenio.clases.Poliza;
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
	public static void start() {
		// --------------- MARCO --------------
		final Marco marco1 = new Marco(1200,600,"BUSCAR POLIZA");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 263, 1063, 126);
		marco1.getContentPane().add(scrollPane);

		tablaPoliza.setModel(modelPoliza);
		scrollPane.setViewportView(tablaPoliza);
		// ------------------------------------
		// --------------- ETIQUETAS --------------
		JLabel lblNroPoliza = new JLabel("Nro Poliza");
		lblNroPoliza.setBounds(43, 83, 142, 38);
		lblNroPoliza.setFont(new Font("Serif", Font.PLAIN, 19));
		marco1.getContentPane().add(lblNroPoliza);
		// --------------- CAMPOS DE TEXTO --------------
		textFieldNPoliza = new JTextField();
		textFieldNPoliza.setBounds(155, 96, 196, 20);
		marco1.getContentPane().add(textFieldNPoliza);
		textFieldNPoliza.setColumns(10);
		textFieldNPoliza.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 13;
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
			poliza_encontrada = GestorPoliza.buscarPoliza(textFieldNPoliza.getText());
			Object[][] listaMuestra = new Object[1][6];


			System.out.println(poliza_encontrada.getNroPoliza());
			listaMuestra[0][0] = poliza_encontrada.getNroPoliza();
			listaMuestra[0][1] = poliza_encontrada.getCliente().getNroCliente();
			listaMuestra[0][2] = poliza_encontrada.getCliente().getApellido();
			listaMuestra[0][3] = poliza_encontrada.getCliente().getNombre();
			listaMuestra[0][4] = poliza_encontrada.getCliente().getTipoDocumento();
			listaMuestra[0][5] = poliza_encontrada.getCliente().getDocumento();
			// listaMuestra[0][6] = NO SE COMO MOSTRAR ULTIMO PAGO


			DefaultTableModel model = new DefaultTableModel(listaMuestra,new String[] { "Nro. Poliza", "Nro. Cliente", "Apellido", "Nombre", "Tipo documento", "Nro. Documento", "Ultimo pago"}) {

				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int i, int i1) {
					return false;
				}
			};
			tablaPoliza.setModel(model);


		};

		btnBuscarPoliza.addActionListener(accionBuscar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(1041, 527, 143, 33);
		marco1.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(e -> marco1.dispose());

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(888, 527, 143, 33);
		marco1.getContentPane().add(btnAceptar);
		ActionListener accionAceptar = e -> {
			if(tablaPoliza.getSelectedRow()<0) {
				marco1.dispose();
			}else {
				GestorPantallas.registrarPago(poliza_encontrada);
				marco1.dispose();
			}
		};
		btnAceptar.addActionListener(accionAceptar);


		// -----------------------------------------------------------------
	}
}
