package tp.disenio.pantallas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPantallas;
public class PantallaLogin {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start() {

		// -------- MARCO ----------
		final Marco marco1 = new Marco(700,400,"EL ASEGURADO - LOGIN");
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
		// ------------------------

		// ------ ETIQUETAS --------
		JLabel nombre = new JLabel ("NOMBRE USUARIO");
		nombre.setFont(new Font("Serif", Font.BOLD, 18));
		nombre.setBounds(20, 50, 300, 50);

		JLabel contrasenia = new JLabel ("CONTRASEÑA");
		contrasenia.setFont(new Font("Serif", Font.BOLD, 18));
		contrasenia.setBounds(20, 125, 300, 50);
		// ------------------------------

		// ----- CAMPOS ----
		JTextField Cnombre = new JTextField(20);
		Cnombre.setBounds(250, 61, 248, 29);

		JPasswordField Ccontra = new JPasswordField(20);
		Ccontra.setBounds(250, 139, 248, 29);
		// ----------------------


		// ----- BOTON --------
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.setBounds(461, 245, 157, 40);
		aceptar.setFont(new Font("Serif", Font.PLAIN, 18));

		ActionListener accion = e -> {
			GestorPantallas.PantallaPrincipal();
			marco1.dispose();
		};

		aceptar.addActionListener(accion);

		//Acá habría que agregar cuando el gestor hace la validación lo que ocurre cuando el usuario y la contraseña no son validos
		//Falta agregar restricciones de cuando el campo fue completado o no.
		//VER manejo de errores

		// ---- PANEL
		marco1.getContentPane().add(aceptar);
		marco1.getContentPane().add(nombre);
		marco1.getContentPane().add(Cnombre);
		marco1.getContentPane().add(Ccontra);
		marco1.getContentPane().add(contrasenia);

		//

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
