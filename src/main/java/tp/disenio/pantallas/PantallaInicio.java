package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPantallas;
public class PantallaInicio {


	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start() {

		// -------- MARCO ----------

		final Marco marco1 = new Marco(700, 600,"EL ASEGURADO");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (0, 128, 128));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		marco1.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});

		//------- TITULO --------------------
		JLabel titulo= new JLabel("BIENVENIDO");
		titulo.setBounds(144, 73, 406, 120);
		titulo.setFont(new Font("Serif", Font.BOLD, 60));
		// -------------------------------

		//------ BOTON ----------------
		JButton boton = new JButton("LOGIN");
		boton.setBounds(234, 449, 226, 50);
		boton.setFont(new Font("Serif", Font.PLAIN, 20));

		boton.addActionListener(e -> {
			GestorPantallas.PantallaLogin();
			marco1.dispose();

		});



		//---------------PANEL---------------

		marco1.getContentPane().add(boton);
		marco1.getContentPane().add(titulo);


	}

	protected static void close() {
		// TODO Auto-generated method stub
		GestorDB gdb = GestorDB.getInstance();
		if (JOptionPane.showConfirmDialog(null, "¿Desea realmente salir del sistema?",
				"Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			gdb.cerrarConexion();

			System.exit(0);
		}
	}
}