import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class FuncionesSelects {
	static final  String url = "jdbc:mysql://localhost:3306/proyecto";
	static final  String user = "root";
	static final  String password = "root";
	
	//Create
	public static void guardarPersonaje(POJO personaje) {
		String Select = "INSERT INTO personaje (`Nombre`, `Género`, `Especie`, `Recurso`, `Región`, `Posición`, `Tipo_de_rango`, `Año_de_salida`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(Select)) {
			pstmt.setString(1, personaje.getNombre());
			pstmt.setString(2, personaje.getGenero());
			pstmt.setString(3, personaje.getEspecie());
			pstmt.setString(4, personaje.getRecurso());
			pstmt.setString(5, personaje.getRegion());
			pstmt.setString(6, personaje.getPosicion());
			pstmt.setString(7, personaje.getTipoDeRango());
			pstmt.setInt(8, personaje.getAñoDeSalida());


			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static List<POJO> obtenerTodos() {
		List<POJO> lista = new ArrayList<>();
		String sql = "SELECT * FROM personaje";
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				lista.add(mapearPersonaje(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	//Read 
	public static List<POJO> buscar(String nombre, String genero, String especie, 
			String recurso, String region, String posicion, 
			String tipoDeRango, Integer año) {

		List<POJO> lista = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM personaje WHERE 1=1");

		if (nombre != null && !nombre.isEmpty()) sql.append(" AND Nombre LIKE ?");
		if (genero != null && !genero.isEmpty()) sql.append(" AND Género = ?");
		if (especie != null && !especie.isEmpty()) sql.append(" AND Especie = ?");
		if (recurso != null && !recurso.isEmpty()) sql.append(" AND Recurso = ?");
		if (region != null && !region.isEmpty()) sql.append(" AND Región = ?");
		if (posicion != null && !posicion.isEmpty()) sql.append(" AND Posición = ?");
		if (tipoDeRango != null && !tipoDeRango.isEmpty()) sql.append(" AND Tipo_de_rango = ?");
		if (año != null && año > 0) sql.append(" AND Año_de_salida = ?");

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

			int index = 1;
			if (nombre != null && !nombre.isEmpty()) pstmt.setString(index++, "%" + nombre + "%");
			if (genero != null && !genero.isEmpty()) pstmt.setString(index++, genero);
			if (especie != null && !especie.isEmpty()) pstmt.setString(index++, especie);
			if (recurso != null && !recurso.isEmpty()) pstmt.setString(index++, recurso);
			if (region != null && !region.isEmpty()) pstmt.setString(index++, region);
			if (posicion != null && !posicion.isEmpty()) pstmt.setString(index++, posicion);
			if (tipoDeRango != null && !tipoDeRango.isEmpty()) pstmt.setString(index++, tipoDeRango);
			if (año != null && año > 0) pstmt.setInt(index++, año);

			ResultSet setResultados = pstmt.executeQuery();

			while (setResultados.next()) {
				lista.add(mapearPersonaje(setResultados));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}


	private static POJO mapearPersonaje(ResultSet rs) throws SQLException {
		POJO p = new POJO();
		p.setNombre(rs.getString("Nombre"));
		p.setGenero(rs.getString("Género"));
		p.setEspecie(rs.getString("Especie"));
		p.setRecurso(rs.getString("Recurso"));
		p.setRegion(rs.getString("Región"));
		p.setPosicion(rs.getString("Posición"));
		p.setTipoDeRango(rs.getString("Tipo_de_rango"));
		p.setAñoDeSalida(rs.getInt("Año_de_salida"));
		return p;
	}
	
	//Delete
	public static void eliminarPersonaje(String nombre) {
		String sql = "DELETE FROM personaje WHERE Nombre = ?";
		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nombre);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Update
	public static void actualizar(POJO personaje) {
	    String sql = "UPDATE personaje SET Género=?, Especie=?, Recurso=?, Región=?, Posición=?, Tipo_de_rango=?, Año_de_salida=? WHERE Nombre=?";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, personaje.getGenero());
	        pstmt.setString(2, personaje.getEspecie());
	        pstmt.setString(3, personaje.getRecurso());
	        pstmt.setString(4, personaje.getRegion());
	        pstmt.setString(5, personaje.getPosicion());
	        pstmt.setString(6, personaje.getTipoDeRango());
	        pstmt.setInt(7, personaje.getAñoDeSalida());
	        pstmt.setString(8, personaje.getNombre());
	        pstmt.executeUpdate();
	    } catch (SQLException e) { e.printStackTrace(); }
	}
}
