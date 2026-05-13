import java.io.*;
import java.util.*;

public class GestorFicheros {
	private static final String RUTA = "datos.dat";

	public static void guardar(List<Campeon> lista) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTA))) {
			outputStream.writeObject(lista);
		} catch (IOException e) { e.printStackTrace(); }
	}

	@SuppressWarnings("unchecked")
	public static List<Campeon> cargar() {
		try (ObjectInputStream imputStream = new ObjectInputStream(new FileInputStream(RUTA))) {
			Object listado = imputStream.readObject();
			if(listado instanceof List<?> listabuena) {
				if(listabuena.getFirst() instanceof Campeon){
					return (List<Campeon>) imputStream.readObject();
				}
				else { return new ArrayList<>(); }
			}
			else { return new ArrayList<>();}
		} catch (Exception e) { return new ArrayList<>(); }
	}
}
