package Implementacion;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Disco {
  private int tamanio;
  private int tamanio_bloque;
  private ArrayList<Bloque> n_bloques;

  private ArrayList<i_nodos> lista_inodos;
  private Directorio dir;

  public Disco() {
    this.n_bloques = new ArrayList<Bloque>();
    this.lista_inodos = new ArrayList<>();
    this.dir = new Directorio();
  }

  public Disco(int tamanio, int tamanio_bloque) {
    this.tamanio = tamanio;
    this.tamanio_bloque = tamanio_bloque;
    this.n_bloques = new ArrayList<Bloque>();
    this.lista_inodos = new ArrayList<>();
    this.dir = new Directorio();
  }

  public int getTamanio() {
    return tamanio;
  }

  public void setTamanio(int tamanio) {
    this.tamanio = tamanio;
  }

  public ArrayList<Bloque> getN_bloques() {
    return n_bloques;
  }

  public void setN_bloques(ArrayList<Bloque> n_bloques) {
    this.n_bloques = n_bloques;
  }

  public Directorio getDir() {
    return dir;
  }

  public void setDir(Directorio dir) {
    this.dir = dir;
  }

  public void agregarDir(Archivo a) {
    dir.agregarArchivo(a);
  }

  public int getTamanio_bloque() {
    return tamanio_bloque;
  }

  public void setTamanio_bloque(int tamanio_bloque) {
    this.tamanio_bloque = tamanio_bloque;
  }

  public void InicializarDisco() {
    for (int i = 0; i < tamanio / tamanio_bloque; i++) {
      Bloque b = new Bloque(false, i);
      n_bloques.add(b);
    }

    System.out.println("Disco inicializado...\n");
  }

  public void asignacionContigua(Archivo a, int init) {
    int i = -1, c = 0;

    for (int j = init; j < n_bloques.size(); j++) {
      if (!n_bloques.get(j).isEstado()) {
        c++;
      } else {
        c = 0;
      }

      if (c == (Math.ceil((double) a.getTamanio() / tamanio_bloque))) {
        i = (int) ((j + 1) - Math.ceil((double) a.getTamanio() / tamanio_bloque));
        break;
      }
    }

    if (i != -1) {
      if (i != init)
        JOptionPane.showMessageDialog(null, "Se coloco " + a.getNombre() + " en bloque " + i + " por falta de espacio.");
      a.setInicio(i);
      for (int j = i; j < i + Math.ceil((double) a.getTamanio() / tamanio_bloque); j++) {
        Bloque b = new Bloque(true, j);
        b.setArchivo(a);
        n_bloques.set(j, b);
        a.setBloques_asignados(j);
      }
      JOptionPane.showMessageDialog(null, "Se agrego correctamente " + a.getNombre() + ".", "Archivo - " + a.getNombre(), JOptionPane.INFORMATION_MESSAGE);// Mensaje con Error
    } else {
      dir.borrarArchivo(a);
      System.out.println("No hay espacio suficiente...\n");
      JOptionPane.showMessageDialog(null, "No hay espacio suficiente para " + a.getNombre() + ".", "Archivo - " + a.getNombre(), JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }

  public void asginacionVinculada(Archivo a) {
    int dato;
    BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

    if (espacioBloques((int) Math.ceil((double) a.getTamanio() / tamanio_bloque))) {
      for (int i = 0; i < Math.ceil((double) a.getTamanio() / tamanio_bloque); i++) {
        System.out.print("Ingrese bloque : ");
        try {
          dato = Integer.parseInt(leer.readLine());
          while (n_bloques.get(dato).isEstado()) {
            System.out.print("Bloque Ocupado. Ingrese bloque : ");
            dato = Integer.parseInt(leer.readLine());
          }
          Bloque b = new Bloque(true, dato);
          b.setArchivo(a);
          n_bloques.set(dato, b);
          a.setBloques_asignados(dato);
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
      a.setInicio(a.getBloques_asignados().get(0));
    } else {
      dir.borrarArchivo(a);
      System.out.println("No hay suficiente espacio en disco.");
    }
  }

  public void asginacionVinculada(Archivo a, ArrayList<Integer> datos) {
    if (espacioBloques((int) Math.ceil((double) a.getTamanio() / tamanio_bloque))) {
      for (int i = 0; i < Math.ceil((double) a.getTamanio() / tamanio_bloque); i++) {
        try {
          Bloque b = new Bloque(true, datos.get(i));
          b.setArchivo(a);
          n_bloques.set(datos.get(i), b);
          a.setBloques_asignados(datos.get(i));
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
      a.setInicio(a.getBloques_asignados().get(0));

      for (int j = 0; j < datos.size(); j++) {
        if (j + 1 < datos.size()) {
          i_nodos i_nodo = new i_nodos(datos.get(j), datos.get(j + 1));
          a.setLista_inodos(datos.get(j), datos.get(j + 1));
          lista_inodos.add(i_nodo);
        }
      }

      verInodos();

      JOptionPane.showMessageDialog(null, "Se agrego correctamente " + a.getNombre() + ".", "Archivo - " + a.getNombre(), JOptionPane.INFORMATION_MESSAGE);// Mensaje con Error
    } else {
      dir.borrarArchivo(a);
      System.out.println("No hay suficiente espacio en disco.");
    }
  }

  public void verInodos() {
    for (i_nodos i : lista_inodos) {
      System.out.println(i.getDato() + " " + i.getSig_dato());
    }
  }

  public boolean espacioBloques(int tamanioArchivo) {
    int c = 0;
    for (Bloque b : n_bloques) {
      if (!b.isEstado())
        c++;
    }
    System.out.println(c);
    return c >= tamanioArchivo;
  }

  public void asginacionIndexada(Archivo a) {
    int dato;
    BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

    if (espacioBloques((int) Math.ceil((double) a.getTamanio() / tamanio_bloque))) {
      for (int i = 0; i < Math.ceil((double) a.getTamanio() / tamanio_bloque); i++) {
        System.out.print("Ingrese bloque : ");
        try {
          dato = Integer.parseInt(leer.readLine());
          while (n_bloques.get(dato).isEstado()) {
            System.out.print("Bloque Ocupado. Ingrese bloque : ");
            dato = Integer.parseInt(leer.readLine());
          }
          Bloque b = new Bloque(true, dato);
          b.setArchivo(a);
          n_bloques.set(dato, b);
          a.setBloques_asignados(dato);
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
      a.setInicio(a.getBloques_asignados().get(0));
    } else {
      dir.borrarArchivo(a);
      System.out.println("No hay suficiente espacio en disco.");
    }
  }

  public void asginacionIndexada(Archivo a, ArrayList<Integer> datos) {
    if (espacioBloques((int) Math.ceil((double) a.getTamanio() / tamanio_bloque))) {
      for (int i = 0; i < Math.ceil((double) a.getTamanio() / tamanio_bloque); i++) {
        try {
          Bloque b = new Bloque(true, datos.get(i));
          b.setArchivo(a);
          n_bloques.set(datos.get(i), b);
          a.setBloques_asignados(datos.get(i));
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
      a.setInicio(a.getBloques_asignados().get(0));

      for (int j = 1; j < datos.size(); j++) {
        //if (j != a.getInicio()) {
          i_nodos i_nodo = new i_nodos(a.getInicio(), datos.get(j));
          a.setLista_inodos(a.getInicio(), datos.get(j));
          lista_inodos.add(i_nodo);
        //}
      }

      verInodos();

      JOptionPane.showMessageDialog(null, "Se agrego correctamente " + a.getNombre() + ".", "Archivo - " + a.getNombre(), JOptionPane.INFORMATION_MESSAGE);// Mensaje con Error
    } else {
      dir.borrarArchivo(a);
      System.out.println("No hay suficiente espacio en disco.");
    }
  }

  public void eliminarDeDisco(Archivo a) {
    for (Bloque n : n_bloques) {
      if (n.isEstado() && n.getArchivo().getNombre().equals(a.getNombre())) {
        n.setArchivo(null);
        n.setEstado(false);
      }
    }
    dir.borrarArchivo(a);
  }

  public Archivo buscarArchivo(String nombre) {
    for (int i = 0; i < dir.getArchivos().size(); i++) {
      if (dir.getArchivos().get(i).getNombre().equals(nombre)) {
        return dir.getArchivos().get(i);
      }
    }

    return null;
  }

  public void verDisco() {
    for (int i = 0; i < n_bloques.size(); i++) {
      if (!n_bloques.get(i).isEstado()) {
        System.out.print("[" + i + "][Vacio]\t");
      } else {
        System.out.print("[" + i + "][" + n_bloques.get(i).getArchivo().getNombre() + "]\t");
      }

      if ((i + 1) % 4 == 0)
        System.out.println();
    }
  }

  public ArrayList<i_nodos> getLista_inodos() {
    return this.lista_inodos;
  }

}
