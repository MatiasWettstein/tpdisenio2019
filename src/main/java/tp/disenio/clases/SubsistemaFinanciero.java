package tp.disenio.clases;

public class SubsistemaFinanciero {

	public static double tasaInteres () {
		double retorno=0;
		int aleatorio = (int) (Math.random()*4);
		
		switch(aleatorio) {
		case 0: retorno =  0.10;
			break;
		case 1: retorno = 0.15;
			break;
		case 2: retorno = 0.20;
			break;
		case 3: retorno = 0.25;
			break;
		}
		
		return retorno; 
		
	}
	
	public static double descuentoPorAdelanto () {
		
		double retorno=0;
		int aleatorio = (int) (Math.random()*4);
		
		switch(aleatorio) {
		case 0: retorno = 0.10;
			break;
		case 1: retorno = 0.15;
			break;
		case 2: retorno = 0.20;
			break;
		case 3: retorno = 0.25;
			break;
		}
		
		return retorno; 
		
	}
	
}
