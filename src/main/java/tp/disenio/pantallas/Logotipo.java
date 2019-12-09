package tp.disenio.pantallas;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Logotipo {
	ImageIcon dibujo = new ImageIcon (new ImageIcon(getClass().getResource("/imagenes/Icono2.jpg")).getImage());

public void cargarImagen (Graphics g) {
	g.drawImage(dibujo.getImage(), 25, 25, 200, 200, null);
	}	
}
