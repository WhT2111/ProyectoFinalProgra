
import java.sql.*;
public class ConexionMYSQL {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
        try {
            // connection
            String url = "jdbc:mysql://localhost:3306/proyecto";
            String user = "root";
            String password = "root";
            String nombre = "'2024'";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");
            FuncionesSelects.SelectAnyo(nombre);
            
            
          
} catch (SQLException ex) {
            System.out.println("Exception ::"+ex.getMessage());
            ex.printStackTrace();
        }

	}
}
