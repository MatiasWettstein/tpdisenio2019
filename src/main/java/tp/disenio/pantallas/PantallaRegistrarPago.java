package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.clases.Mensual;
import tp.disenio.clases.Poliza;
import tp.disenio.clases.Semestral;
import tp.disenio.gestores.GestorPantallas;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
	tablaPoliza.setModel(modelPoliza);
	scrollPanePoliza.setViewportView(tablaPoliza);
	
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
		
	////////////////////////////////////CUANDO YA BUSCO EL CLIENTE
		
		if (p != null) {
			textNCliente.setText(p.getCliente().getNroCliente());
			textNombreC.setText(p.getCliente().getNombre());
			textApellidoC.setText(p.getCliente().getApellido());
			textMarca.setText(p.getVehiculo().getModelo().getMarca().getNombre());
			textModelo.setText(p.getVehiculo().getModelo().getNombre());
			textPatente.setText(p.getVehiculo().getPatente());
			
			if  (p.getForma_pago().getNombre() == "MENSUAL") {
		/*		list 
				int fila =0;
				Object[][] listaMuestra = new Object[6][6];
				for(ClienteDTO c:lista) {

					listaMuestra[fila][0] = c.getNroCliente();
					listaMuestra[fila][1] = c.getApellido();
					listaMuestra[fila][2] = c.getNombre();
					listaMuestra[fila][3] = c.getTipoDoc();
					listaMuestra[fila][4] = c.getDocumento();
					fila++;

				}*/
			}
			else if (p.getForma_pago().getNombre() == "SEMESTRAL") {
				//MOSTRAR CUOTA UNICA
			}
			
		}
		
	
	}
	
	
	
}
