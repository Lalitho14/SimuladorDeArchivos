import java.util.ArrayList;

public class Directorio {
	private ArrayList<Archivo> archivos;

	Directorio() {
		archivos = new ArrayList<Archivo>();
	}

	public ArrayList<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(ArrayList<Archivo> archivos) {
		this.archivos = archivos;
	}

	public void agregarArchivo(Archivo a) {
		archivos.add(a);
	}

	public void infoDirectorio() {
		System.out.println("Archivo  \tInicio\t Tamanio\t Fin");
		for (Archivo a : archivos) {
			System.out.println(a.getNombre() + "  \t\t" + a.getInicio() + "  \t\t" + a.getTamanio()+"  \t\t"+a.getBloqueFinal());
		}
	}
	
	public void borrarArchivo(Archivo a) {
		archivos.remove(a);
	}
}
