package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tp.disenio.DAO.DAOCliente;
import tp.disenio.gestores.GestorCliente;
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
		marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//------- TITULO --------------------
		JLabel titulo= new JLabel("BIENVENIDO");
		titulo.setBounds(144, 73, 406, 120);
		titulo.setFont(new Font("Serif", Font.BOLD, 60));
		// -------------------------------

		//------ BOTON ----------------
		JButton boton = new JButton("LOGIN");
		boton.setBounds(234, 449, 226, 50);
		boton.setFont(new Font("Serif", Font.PLAIN, 20));

		boton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				GestorPantallas.PantallaLogin();
				marco1.dispose();

			}
		});
	


		//---------------PANEL---------------

		marco1.getContentPane().add(boton);
		marco1.getContentPane().add(titulo);

		/*FALTA AGREGAR IMAGEN ENTRE TITULO Y BOTON  --- REVISTAR ESTÉTICA

		Image img = new ImageIcon("/IMG/Logo.jpg").getImage();
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(img));
		lblImg.setBounds (243,204,200,200);
		marco1.getContentPane().add(lblImg);
		
		*/
	}
}