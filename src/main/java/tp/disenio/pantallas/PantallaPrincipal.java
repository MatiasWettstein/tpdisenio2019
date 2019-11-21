package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tp.disenio.gestores.GestorPantallas;

public class PantallaPrincipal {
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start() {

		// -------- MARCO ----------
		final Marco marco1 = new Marco(1200,600,"EL ASEGURADO - MENU");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (0, 128, 128));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// ------------------------

		// ------ BOTONES -------
		JButton altaC = new JButton("ALTA CLIENTE");

		ActionListener altaCliente = e -> {
			GestorPantallas.PantallaDarAltaCliente();
			marco1.dispose();
		};
		altaC.addActionListener(altaCliente);
		altaC.setFont(new Font("Serif", Font.BOLD, 12));
		altaC.setBounds(50, 81, 200, 50);

		//
		JButton consultaC = new JButton("CONSULTAR CLIENTE");

		ActionListener consultaCliente = e -> marco1.dispose();
		consultaC.addActionListener(consultaCliente);
		consultaC.setFont(new Font("Serif", Font.BOLD, 12));
		consultaC.setBounds(300, 81, 200, 50);

		//
		JButton altaP = new JButton("ALTA POLIZA");
		ActionListener altaPoliza = e -> {
			GestorPantallas.PantallaDarAltaPoliza(null, null, null,null, null);
			marco1.dispose();
		};
		altaP.addActionListener(altaPoliza);
		altaP.setFont(new Font("Serif", Font.BOLD, 12));
		altaP.setBounds(50, 180, 200, 50);


		//
		JButton consultaP = new JButton("CONSULTAR POLIZA");
		ActionListener consultaPoliza = e -> marco1.dispose();
		consultaP.addActionListener(consultaPoliza);
		consultaP.setFont(new Font("Serif", Font.BOLD, 12));
		consultaP.setBounds(300, 180, 200, 50);

		//
		JButton registrarP = new JButton("REGISTRAR PAGO DE POLIZA");
		ActionListener pagoPoliza = e -> marco1.dispose();
		registrarP.addActionListener(pagoPoliza);
		registrarP.setFont(new Font("Serif", Font.BOLD, 12));
		registrarP.setBounds(536, 180, 250, 50);

		//
		JButton factoresC = new JButton("ACTUALIZAR FACTORES DE CARACTERISTICAS");
		ActionListener actualizarFactores = e -> marco1.dispose();
		factoresC.addActionListener(actualizarFactores);
		factoresC.setFont(new Font("Serif", Font.BOLD, 12));
		factoresC.setBounds(50, 366, 350, 50);


		//
		JButton generarPR = new JButton ("GENERAR PROPUESTA DE RENOVACION");
		ActionListener generarPropuesta = e -> marco1.dispose();
		generarPR.addActionListener(generarPropuesta);
		generarPR.setFont(new Font("Serif", Font.BOLD, 12));
		generarPR.setBounds(50, 255, 300, 50);


		//
		JButton generarListadoPR = new JButton("GENERAR LISTADO DE PROPUESTA DE RENOVACION");
		ActionListener generarListado = e -> marco1.dispose();
		generarListadoPR.addActionListener(generarListado);
		generarListadoPR.setFont(new Font("Serif", Font.BOLD, 12));
		generarListadoPR.setBounds(386, 255, 400, 50);



		//
		JButton informe = new JButton("GENERAR INFORME DE RESULTADO MENSUAL");
		ActionListener informeM = e -> marco1.dispose();
		informe.addActionListener(informeM);
		informe.setFont(new Font("Serif", Font.BOLD, 12));
		informe.setBounds(436, 366, 350, 50);
		// ---------------------------

		// --------- ETIQUETAS ------
		JLabel sectorC = new JLabel("CLIENTE");
		sectorC.setFont(new Font("Serif", Font.BOLD, 20));
		sectorC.setBounds(50, 30, 100, 50);
		JLabel sectorP = new JLabel("POLIZA");
		sectorP.setFont(new Font("Serif", Font.BOLD, 20));
		sectorP.setBounds(50, 130, 100, 50);
		JLabel sectorI = new JLabel("INFORMES Y CARACTERÍSTICAS");
		sectorI.setFont(new Font("Serif", Font.BOLD, 20));
		sectorI.setBounds(50, 319, 500, 50);
		//---------------------------

		// -------- PANEL -----------
		marco1.getContentPane().add(altaC);
		marco1.getContentPane().add(consultaC);
		marco1.getContentPane().add(altaP);
		marco1.getContentPane().add(consultaP);
		marco1.getContentPane().add(factoresC);
		marco1.getContentPane().add(registrarP);
		marco1.getContentPane().add(generarListadoPR);
		marco1.getContentPane().add(generarPR);
		marco1.getContentPane().add(informe);
		marco1.getContentPane().add(sectorI);
		marco1.getContentPane().add(sectorC);
		marco1.getContentPane().add(sectorP);

		// ------------------------------
	}
}
