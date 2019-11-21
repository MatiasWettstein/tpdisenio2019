package tp.disenio.gestores;

public class GestorPoliza {
	//---------- Patron Singleton
	private static GestorPoliza GPoliza ; // Patron Singleton -- Unica instancia tipo gestor creada.

	private GestorPoliza(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
	}

	public static GestorPoliza getInstance() { // Patron Singleton -- Devuelve la instancia, si no existe la crea
		if ( GPoliza == null) {
			GPoliza = new GestorPoliza();
		}
		return GPoliza;
	}


	public float calcularPrima(float precio) {
		return precio/100 * 25;

	}
	public float calcularDerecho(float precio) {
		return precio/1000;
	}
	public float calcularPremio(float prima, float emision) {
		return prima+emision;




	}
}
