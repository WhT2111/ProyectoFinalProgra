import java.io.Serializable;

public class Campeon implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre; 
	private String genero;
	private String especie; 
	private String recurso;
	private String region; 
	private String posicion; 
	private String tipoDeRango;
	private int añoDeSalida;
	private double winrate;
	private String rutaImagen;


	public Campeon() {
	}
	public String getRutaImagen() { 
		return null; 
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen; 
	}
	public double getWinrate() {
		return winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public String getRecurso() {
		return recurso;
	}
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public String getTipoDeRango() {
		return tipoDeRango;
	}
	public void setTipoDeRango(String tipoDeRango) {
		this.tipoDeRango = tipoDeRango;
	}
	public int getAñoDeSalida() {
		return añoDeSalida;
	}
	public void setAñoDeSalida(int añoDeSalida) {
		this.añoDeSalida = añoDeSalida;




	}
}
