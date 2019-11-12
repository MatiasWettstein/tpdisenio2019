package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tp.disenio.pantallas.Marco;

public class PantallaDarAltaCliente2 {
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start() {
		
	// --------- MARCO -------------------------------------------------
	final Marco marco1 = new Marco(700,400,"DAR DE ALTA CLIENTE");
	marco1.getContentPane().setLayout(null);
	marco1.getContentPane().setBackground(new Color (192, 192, 192));
	marco1.setLocationRelativeTo(null);
	marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// -----------------------------------------------------------------
	
	// ----------------- ETIQUETAS -------------------------------------
	JLabel lblNroCliente = new JLabel("Nro. Cliente");
	lblNroCliente.setFont(new Font("Serif", Font.PLAIN, 18));
	lblNroCliente.setBounds(96, 106, 113, 24);
	marco1.getContentPane().add(lblNroCliente);
	// -----------------------------------------------------------------
	
	// ----------------- FORMATTED TEXT FIELD --------------------------
	JFormattedTextField formattedTextField_NCliente = new JFormattedTextField();
	formattedTextField_NCliente.setEditable(false);
	formattedTextField_NCliente.setEnabled(false);
	formattedTextField_NCliente.setBounds(262, 111, 216, 20);
	marco1.getContentPane().add(formattedTextField_NCliente);
	// -----------------------------------------------------------------
	
	// ------------ BOTONES --------------------------------------------
	JButton btnCancelar = new JButton("CANCELAR");
	btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
	btnCancelar.setBounds(541, 327, 143, 33);
	marco1.getContentPane().add(btnCancelar);
	
	JButton btnAceptar = new JButton("ACEPTAR");
	btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
	btnAceptar.setBounds(388, 327, 143, 33);
	marco1.getContentPane().add(btnAceptar);
	// -----------------------------------------------------------------
	
	}
}
