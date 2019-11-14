package tp.disenio.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorDB {


	static final String URL = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/mmvdwfly";
	static final String USER = "mmvdwfly";
	static final String PASS = "ZBlqwzDu5ne0a1vx96EfwzOtemDW4FCr";


	private static GestorDB GDB ; // Patron Singleton -- Unica instancia tipo gestor creada.

	private GestorDB(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
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
