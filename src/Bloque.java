public class Bloque {
	private boolean estado;
	private int numero;
	private Archivo archivo;

	Bloque() {
		this.estado = false;
		this.archivo = null;
	}

	Bloque(boolean estado, int numero) {
		this.setEstado(estado);
		this.setNumero(numero);
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
