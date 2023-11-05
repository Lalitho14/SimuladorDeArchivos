package Implementacion;

public class TablaDir {
  private String nombre_archivo;
  private int inicio;
  private int tamanio;
  private int fin;

  public TablaDir(String nombre_archivo, int inicio, int tamanio, int fin) {
    this.nombre_archivo = nombre_archivo;
    this.inicio = inicio;
    this.tamanio = tamanio;
    this.fin = fin;
  }


  public String getNombre_archivo() {
    return nombre_archivo;
  }

  public void setNombre_archivo(String nombre_archivo) {
    this.nombre_archivo = nombre_archivo;
  }

  public int getInicio() {
    return inicio;
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

  public int getFin() {
    return fin;
  }

  public void setFin(int fin) {
    this.fin = fin;
  }
}
