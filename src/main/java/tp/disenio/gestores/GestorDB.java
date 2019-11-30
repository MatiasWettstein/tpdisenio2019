package tp.disenio.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorDB {


	static final String URL = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/mmvdwfly";
	static final String USER = "mmvdwfly";
	static final String PASS = "ZBlqwzDu5ne0a1vx96EfwzOtemDW4FCr";

	public Connection conec; 
	
	

	private static GestorDB GDB ; // Patron Singleton -- Unica instancia tipo gestor creada.

	private GestorDB(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
	
		try {
			this.conec = this.crearConexion();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	public static GestorDB getInstance() { // Patron Singleton -- Devuelve la instancia, si no existe la crea
		if ( GDB == null) {
			GDB = new GestorDB();	
		}
		return GDB;
	}

	public Connection crearConexion() throws ClassNotFoundException, SQLException{

		Class.forName("org.postgresql.Driver");

		Connection conexion = DriverManager.getConnection(URL, USER, PASS);
		if (conexion != null){
			System.out.print("Conexion establecida...");
			return conexion;

		}
		return null;

	}


}
