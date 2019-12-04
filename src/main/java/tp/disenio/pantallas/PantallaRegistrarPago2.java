package tp.disenio.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tp.disenio.DTO.CuotaDTO;
import tp.disenio.clases.Poliza;
import tp.disenio.gestores.GestorCobro;
import tp.disenio.gestores.GestorDB;
import tp.disenio.gestores.GestorPantallas;

public class PantallaRegistrarPago2 {
	private static JTextField textMontoAPagar;
	private static JTextField textMontoAbonado;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void start (Poliza p, ArrayList<CuotaDTO> cuotas, CuotaDTO c, double montoTotal) {
		final Marco marco1 = new Marco(700,600,"REGISTRAR PAGO POLIZA");
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
		DecimalFormat dec = new DecimalFormat("#.##");
		textMontoAPagar.setText(dec.format(montoTotal));

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
		GestorCobro gc = GestorCobro.getInstance();
		String fechaPago = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Serif", Font.BOLD, 12));
		btnAceptar.setBounds(383, 493, 143, 33);
		marco1.getContentPane().add(btnAceptar);
		ActionListener accionaceptar = e ->{	//CUANDO DAN ACEPTAR HAY QUE CALCULAR EL VUELTO Y MOSTRARLO POR UN OPTION PANE
			double montoAbonado = Double.parseDouble(textMontoAbonado.getText());


			String convertir = textMontoAPagar.getText().replace(',', '.');

			double precio= Float.parseFloat(convertir);
			double vuelto = 0;
			if(montoAbonado<precio) {
				JOptionPane.showMessageDialog(null, "El monto abonado es menor al monto a pagar, reingrese");
			}
			else {
				vuelto = montoAbonado-precio;
				JOptionPane.showMessageDialog(null, "El vuelto del cliente es: " + dec.format(vuelto));
				if(cuotas==null) {//si es de forma semestral solo tengo una cuota que pagar
					boolean flag = gc.registrarPagoCuotaSemestral(c, fechaPago, montoTotal);
					if (flag ) {
						JOptionPane.showMessageDialog(null, "Pago registrado con éxito");
						GestorDB gdb = GestorDB.getInstance();
						gdb.cerrarConexion();
						GestorPantallas.PantallaPrincipal();
						marco1.dispose();
					}
				}
				else { //si es de forma mensual tengo que actualizar todas las cutoas
					boolean flag = gc.registrarPagoCuotaMensual(cuotas, fechaPago, montoTotal);
					if (flag ) {
						JOptionPane.showMessageDialog(null, "Pago registrado con éxito");
						GestorDB gdb = GestorDB.getInstance();
						gdb.cerrarConexion();
						GestorPantallas.PantallaPrincipal();
						marco1.dispose();
					}

				}
			}

		};
		btnAceptar.addActionListener(accionaceptar);

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

		ActionListener accionseleccionarotra = e ->{

			GestorPantallas.registrarPago(p, cuotas, c, montoTotal);

		};

		btnSeleccionarOtrasCuotas.addActionListener(accionseleccionarotra);
		marco1.getContentPane().add(btnSeleccionarOtrasCuotas);
		//TIENE QUE VOLVER A LA PANTALLA ANTERIOR Y PERMITIRLE SELECCIONAR OTRAS CUOTAS

		// ------------------------------------------

	}
	protected static void close() {
		// TODO Auto-generated method stub
		GestorPantallas.PantallaPrincipal();
	}
}
