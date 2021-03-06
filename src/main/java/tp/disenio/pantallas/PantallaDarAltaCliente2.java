package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import tp.disenio.DTO.ClienteDTO;
import tp.disenio.gestores.GestorCliente;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPantallas;

public class PantallaDarAltaCliente2 {
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start(ClienteDTO cliente, boolean vienedepoliza) {

		// --------- MARCO -------------------------------------------------
		final Marco marco1 = new Marco(700,400,"DAR DE ALTA CLIENTE");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		marco1.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});
		// -----------------------------------------------------------------

		// ----------------- ETIQUETAS -------------------------------------
		JLabel lblNroCliente = new JLabel("Nro. Cliente");
		lblNroCliente.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNroCliente.setBounds(96, 106, 113, 24);
		marco1.getContentPane().add(lblNroCliente);
		// -----------------------------------------------------------------

		// ----------------- FORMATTED TEXT FIELD --------------------------


		MaskFormatter mascaraNCLIENTE = null;
		try {
			mascaraNCLIENTE = new MaskFormatter("##-########");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		mascaraNCLIENTE.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextField_NCliente = new JFormattedTextField(mascaraNCLIENTE);
		formattedTextField_NCliente.setEditable(false);
		formattedTextField_NCliente.setEnabled(false);
		formattedTextField_NCliente.setBounds(262, 111, 216, 20);
		formattedTextField_NCliente.setText(GestorCliente.generarNumeroCliente());
		marco1.getContentPane().add(formattedTextField_NCliente);
		// -----------------------------------------------------------------

		// ------------ BOTONES --------------------------------------------
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(541, 327, 143, 33);
		ActionListener cancel = e -> {
			GestorPantallas.PantallaPrincipal();
			GestorDB gdb = GestorDB.getInstance();
			gdb.cerrarConexion();
			marco1.dispose();
		};
		btnCancelar.addActionListener(cancel);
		marco1.getContentPane().add(btnCancelar);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(388, 327, 143, 33);
		ActionListener aceptarAccion = e -> {
			cliente.setNroCliente(formattedTextField_NCliente.getText());
			cliente.setTipoC("NORMAL");
			cliente.setEstadoCliente("ACTIVO");
			GestorCliente gc = GestorCliente.getInstance();
			boolean flag = gc.guardarCliente(cliente);
			if (flag) {
				JOptionPane.showMessageDialog(null, "Cliente generado con éxito");


				if(vienedepoliza) {
					GestorPantallas.PantallaDarAltaPoliza(cliente, null, null,null, null);
					GestorDB gdb = GestorDB.getInstance();
					gdb.cerrarConexion();
					marco1.dispose();
				} else {
					GestorPantallas.PantallaPrincipal();
					GestorDB gdb = GestorDB.getInstance();
					gdb.cerrarConexion();
					marco1.dispose();
				}

			}else {
				JOptionPane.showMessageDialog(null, "ERROR CRITICO, no se pudo guardar el cliente.");
				GestorPantallas.PantallaDarAltaCliente(vienedepoliza);

			}
		};
		btnAceptar.addActionListener(aceptarAccion);
		marco1.getContentPane().add(btnAceptar);
		// -----------------------------------------------------------------

	}

	protected static void close() {
		// TODO Auto-generated method stub
		GestorPantallas.PantallaPrincipal();
	}
}
