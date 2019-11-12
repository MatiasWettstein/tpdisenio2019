package tp.disenio.gestores;

import tp.disenio.gestores.GestorCliente;

public class GestorCliente {
	
	private static GestorCliente GCliente ; // Patron Singleton -- Unica instancia tipo gestor creada.

	private GestorCliente(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
	}

	public static GestorCliente getInstance() { // Patron Singleton -- Devuelve la instancia, si no existe la crea
		if ( GCliente == null) {
			GCliente = new GestorCliente();
		}
		return GCliente;
	}

}
