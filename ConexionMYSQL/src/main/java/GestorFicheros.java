import java.io.*;
import java.util.*;

public class GestorFicheros {
    private static final String RUTA = "datos.dat";

    public static void guardar(List<POJO> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA))) {
            oos.writeObject(lista);
        } catch (IOException e) { e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
	public static List<POJO> cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA))) {
            return (List<POJO>) ois.readObject();
        } catch (Exception e) { return new ArrayList<>(); }
    }
}