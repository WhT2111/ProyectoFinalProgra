
import java.sql.*;
public class ConexionMYSQL {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/proyecto";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");
       
            
            
          
} catch (SQLException ex) {
            System.out.println("Exception ::"+ex.getMessage());
            ex.printStackTrace();
        }

	}
}
