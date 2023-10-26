import java.util.ArrayList;

public class Archivo {
	private String nombre;
	private int inicio;
	private int tamanio;
	private ArrayList<Integer> bloques_asignados;
	
	Archivo() {
		this.bloques_asignados = new ArrayList<Integer>();
	}

	Archivo(String nombre, int inicio, int tamanio) {
		this.setNombre(nombre);
		this.setInicio(inicio);
		this.setTamanio(tamanio);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getInicio() {
		return this.inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	public void setBloques_asignados(int n){
		this.bloques_asignados.add(n);
	}
	public ArrayList<Integer> getBloques_asignados() {
		return bloques_asignados;
	}
	public String getBloqueFinal(){
		return bloques_asignados.get(bloques_asignados.size()-1).toString();
	}
}
