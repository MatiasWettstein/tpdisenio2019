package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPantallas;

public class PantallaRegistrarPago2 {
	private JTextField textMontoAPagar;
	private JTextField textMontoAbonado;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void start () {
		final Marco marco1 = new Marco(700,600,"REGISTRAR PAGO POLIZA");
		marco1.getContentPane().setLayout(null);
		marco1.getContentPane().setBackground(new Color (192, 192, 192));
		marco1.setLocationRelativeTo(null);
		marco1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		
		// ------------ ETIQUETAS ----------------
		JLabel lblMontoTotalAPagar = new JLabel("Monto total a pagar");
		lblMontoTotalAPagar.setFont(new Font("Serif", Font.PLAIN, 18));
		lblMontoTotalAPagar.setBounds(71, 72, 180, 33);
		marco1.getContentPane().add(lblMontoTotalAPagar);
		
		JLabel lblMontoAbonado = new JLabel("Monto abonado");
		lblMontoAbonado.setFont(new Font("Serif", Font.PLAIN, 18));
		lblMontoAbonado.setBounds(71, 174, 180, 33);
		marco1.getContentPane().add(lblMontoAbonado);
		// ---------------------------------------
		// ------------ CAMPOS DE TEXTO ----------

		textMontoAPagar = new JTextField();
		textMontoAPagar.setEnabled(false);
		textMontoAPagar.setBounds(263, 80, 196, 23);
		marco1.getContentPane().add(textMontoAPagar);
		textMontoAPagar.setColumns(10);
		
		textMontoAbonado = new JTextField();
		textMontoAbonado.setColumns(10);
		textMontoAbonado.setBounds(263, 182, 196, 23);
		textMontoAbonado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 10;
				if(e.getKeyChar()!='1' && e.getKeyChar()!='2' && e.getKeyChar()!='3' && e.getKeyChar()!='4' && e.getKeyChar()!='5' && e.getKeyChar()!='6' && e.getKeyChar()!='7' && e.getKeyChar()!='8' && e.getKeyChar()!='9' && e.getKeyChar()!='0' && e.getKeyChar() !='.' && e.getKeyChar()!=',') e.consume();
				else if(textMontoAbonado.getText().length() > max) {
					e.consume();
				}
			}
		});
		marco1.getContentPane().add(textMontoAbonado);
		
		// ---------------------------------------
		// ------------ BOTONES ------------------
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(383, 493, 143, 33);
		marco1.getContentPane().add(btnAceptar);
		//CUANDO DAN ACEPTAR HAY QUE CALCULAR EL VUELTO Y MOSTRARLO POR UN OPTION PANE 
		
		
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Serif", Font.BOLD, 12));
		btnCancelar.setBounds(536, 493, 143, 33);
		btnCancelar.addActionListener(arg0 -> {
			GestorPantallas.PantallaPrincipal();
			GestorDB gdb = GestorDB.getInstance();
			gdb.cerrarConexion();
			marco1.dispose();
		});
		marco1.getContentPane().add(btnCancelar);
		
		JButton btnSeleccionarOtrasCuotas = new JButton("SELECCIONAR OTRAS CUOTAS");
		btnSeleccionarOtrasCuotas.setFont(new Font("Serif", Font.BOLD, 12));
		btnSeleccionarOtrasCuotas.setBounds(10, 493, 239, 33);
		marco1.getContentPane().add(btnSeleccionarOtrasCuotas);
		//TIENE QUE VOLVER A LA PANTALLA ANTERIOR Y PERMITIRLE SELECCIONAR OTRAS CUOTAS 
		
		// ------------------------------------------
		
		
		
		
	}
}
