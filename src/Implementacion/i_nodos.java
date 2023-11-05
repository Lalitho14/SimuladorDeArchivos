package Implementacion;

import java.util.ArrayList;

public class i_nodos {
  private int dato;
  private int sig_dato;

  public i_nodos(int dato, int sig_dato) {
    this.dato = dato;
    this.sig_dato = sig_dato;
  }

  public void setDato(int dato) {
    this.dato = dato;
  }

  public void setSig_dato(int sig_dato) {
    this.sig_dato = sig_dato;
  }

  public int getDato() {
    return this.dato;
  }

  public int getSig_dato() {
    return this.sig_dato;
  }

}
