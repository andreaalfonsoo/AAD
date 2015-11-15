import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

	public static void main(String[] args) throws SQLException {
		
		Connection conexion;
		Statement instruccion = null;
		ResultSet conjuntoResultados = null;
		String url = "jdbc:mysql://localhost/LiberacionRecursos";
		String user = "root";
		String pw = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Conexion DriverManager
		try {
			//obtenemos la conexión con DriverManager
			conexion = DriverManager.getConnection(url, user, pw);
			System.out.println("Conexión realizada usando Drivermanager");
			
			//consultaremos la tabla
			instruccion = (Statement) conexion.createStatement();
			conjuntoResultados = instruccion.executeQuery("SELECT * FROM accesoDatos");
			
			//instroduciremos datos
			instruccion.executeUpdate("INSERT INTO accesoDatos VALUES (5, 'AAD', '3A', '2015-11-11', '2015-11-15', true);");
			instruccion.executeUpdate("INSERT INTO accesoDatos VALUES (6, 'AAD', '3B', '2015-11-11', '2015-11-15', true);");
			instruccion.executeUpdate("INSERT INTO accesoDatos VALUES (7, 'AAD', '4a', '2015-11-14', '2015-11-23', false);");
			
		} catch (SQLException e) {
			//comprobamos el codigo de error SQLState y damos informacion al usuario
			if(e.getSQLState().equals("28000")){
				System.out.println("Error de autentificación");
			}else
				throw e;
			e.printStackTrace();
			
		} finally {
			//cerramos el conjunto de resultados si no es nulo o si no esta cerrado
			try{
				if(conjuntoResultados!=null && !conjuntoResultados.isClosed())
					conjuntoResultados.close();
			}catch(SQLException e){
				//si se lanza una excepcion al cerrar se guardara en el log
				Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
			}
			//cerramos la instruccion si no es nula o si no esta cerrada
			try{
				if(instruccion!=null && !instruccion.isClosed())
					instruccion.close();
			}catch(SQLException e){
				//si se lanza una excepcion al cerrar se guardara en el log
				Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);		
			}
		}
	}
}
