package tp.disenio.clases;

public class SubsistemaFinanciero {

	public static float tasaInteres () {
		
		float retorno=0;
		int aleatorio = (int) (Math.random()*4);
		
		switch(aleatorio) {
		case 0: retorno = 10;
			break;
		case 1: retorno = 15;
			break;
		case 2: retorno = 20;
			break;
		case 3: retorno = 25;
			break;
		}
		
		return retorno; 
		
	}
	
	public static float descuentoPorAdelanto () {
		
		float retorno=0;
		int aleatorio = (int) (Math.random()*4);
		
		switch(aleatorio) {
		case 0: retorno = 10;
			break;
		case 1: retorno = 15;
			break;
		case 2: retorno = 20;
			break;
		case 3: retorno = 25;
			break;
		}
		
		return retorno; 
		
	}
	
}
