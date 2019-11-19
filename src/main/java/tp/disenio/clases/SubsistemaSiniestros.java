package tp.disenio.clases;

public class SubsistemaSiniestros {
	
	
	public static String cantidadSiniestros () {
		
		String retorno = null;
		int aleatorio = (int) (Math.random()*4);
		
		switch(aleatorio) {
		case 0: retorno = "NINGUNO";
			break;
		case 1: retorno = "UNO";
			break;
		case 2: retorno = "DOS";
			break;
		case 3: retorno = "MAS DE DOS";
			break;
		}
		
		return retorno; 
		
	}
	

}
