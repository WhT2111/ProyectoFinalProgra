import java.sql.*;
public class FuncionesSelects {
	static final  String url = "jdbc:mysql://localhost:3306/proyecto";
	static final  String user = "root";
	static final  String password = "root";
	public static void SelectNombre(String nombre) {
		String Select = "SELECT * FROM personaje WHERE nombre = " +nombre; 
		try (Connection conexion = DriverManager.getConnection(url, user, password)){
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(Select);
			ResultSetMetaData metadata = resultado.getMetaData();
			int numColumnas = metadata.getColumnCount();
			for (int i = 1; i <= numColumnas; i++) {
				System.out.printf("%-15s", metadata.getColumnName(i));
			}
			System.out.println();
			while (resultado.next()) {
				for(int i = 1; i<=numColumnas; i++) {
					Object valor = resultado.getObject(i);
					System.out.printf("%-15s", valor);
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void SelectGenero(String Genero) {
		String Select = "SELECT * FROM personaje WHERE Género = " +Genero; 
		try (Connection conexion = DriverManager.getConnection(url, user, password)){
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(Select);
			ResultSetMetaData metadata = resultado.getMetaData();
			int numColumnas = metadata.getColumnCount();
			for (int i = 1; i <= numColumnas; i++) {
				System.out.printf("%-15s", metadata.getColumnName(i));
			}
			System.out.println();
			while (resultado.next()) {
				for(int i = 1; i<=numColumnas; i++) {
					Object valor = resultado.getObject(i);
					System.out.printf("%-15s", valor);
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();

		}
	}
	public static void SelectEspecie(String Especie) {
		String Select = "SELECT * FROM personaje WHERE Especie = " +Especie; 
		try (Connection conexion = DriverManager.getConnection(url, user, password)){
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(Select);
			ResultSetMetaData metadata = resultado.getMetaData();
			int numColumnas = metadata.getColumnCount();
			for (int i = 1; i <= numColumnas; i++) {
				System.out.printf("%-15s", metadata.getColumnName(i));
			}
			System.out.println();
			while (resultado.next()) {
				for(int i = 1; i<=numColumnas; i++) {
					Object valor = resultado.getObject(i);
					System.out.printf("%-15s", valor);
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void SelectRecurso(String Recurso) {
		String Select = "SELECT * FROM personaje WHERE Recurso = " +Recurso; 
		try (Connection conexion = DriverManager.getConnection(url, user, password)){
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(Select);
			ResultSetMetaData metadata = resultado.getMetaData();
			int numColumnas = metadata.getColumnCount();
			for (int i = 1; i <= numColumnas; i++) {
				System.out.printf("%-15s", metadata.getColumnName(i));
			}
			System.out.println();
			while (resultado.next()) {
				for(int i = 1; i<=numColumnas; i++) {
					Object valor = resultado.getObject(i);
					System.out.printf("%-15s", valor);
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void SelectRegion(String Region) {
		String Select = "SELECT * FROM personaje WHERE Región = " +Region; 
		try (Connection conexion = DriverManager.getConnection(url, user, password)){
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(Select);
			ResultSetMetaData metadata = resultado.getMetaData();
			int numColumnas = metadata.getColumnCount();
			for (int i = 1; i <= numColumnas; i++) {
				System.out.printf("%-15s", metadata.getColumnName(i));
			}
			System.out.println();
			while (resultado.next()) {
				for(int i = 1; i<=numColumnas; i++) {
					Object valor = resultado.getObject(i);
					System.out.printf("%-15s", valor);
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void SelectPosicion(String Pos) {
		String Select = "SELECT * FROM personaje WHERE Posición = " +Pos; 
		try (Connection conexion = DriverManager.getConnection(url, user, password)){
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(Select);
			ResultSetMetaData metadata = resultado.getMetaData();
			int numColumnas = metadata.getColumnCount();
			for (int i = 1; i <= numColumnas; i++) {
				System.out.printf("%-15s", metadata.getColumnName(i));
			}
			System.out.println();
			while (resultado.next()) {
				for(int i = 1; i<=numColumnas; i++) {
					Object valor = resultado.getObject(i);
					System.out.printf("%-15s", valor);
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void SelectRango(String Rango) {
		String Select = "SELECT * FROM personaje WHERE Tipo_de_rango = " +Rango; 
		try (Connection conexion = DriverManager.getConnection(url, user, password)){
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(Select);
			ResultSetMetaData metadata = resultado.getMetaData();
			int numColumnas = metadata.getColumnCount();
			for (int i = 1; i <= numColumnas; i++) {
				System.out.printf("%-15s", metadata.getColumnName(i));
			}
			System.out.println();
			while (resultado.next()) {
				for(int i = 1; i<=numColumnas; i++) {
					Object valor = resultado.getObject(i);
					System.out.printf("%-15s", valor);
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void SelectAnyo(String año) {
		String Select = "SELECT * FROM personaje WHERE Año_de_salida = " +año; 
		try (Connection conexion = DriverManager.getConnection(url, user, password)){
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(Select);
			ResultSetMetaData metadata = resultado.getMetaData();
			int numColumnas = metadata.getColumnCount();
			for (int i = 1; i <= numColumnas; i++) {
				System.out.printf("%-15s", metadata.getColumnName(i));
			}
			System.out.println();
			while (resultado.next()) {
				for(int i = 1; i<=numColumnas; i++) {
					Object valor = resultado.getObject(i);
					System.out.printf("%-15s", valor);
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
