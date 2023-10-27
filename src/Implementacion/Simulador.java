package Implementacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simulador {

  public static void main(String[] args) throws IOException {
    int t = 0, tb = 0;
    BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
    Disco disk = new Disco();
    System.out.print("Ingrese tamanio del disco : ");
    t = Integer.parseInt(leer.readLine());
    System.out.print("Ingrese tamanio de bloque del disco : ");
    tb = Integer.parseInt(leer.readLine());
    while (t % tb != 0) {
      System.out.print("Ingrese tamanio de bloque del disco valido s: ");
      tb = Integer.parseInt(leer.readLine());
    }
    disk.setTamanio(t);
    disk.setTamanio_bloque(tb);
    disk.InicializarDisco();
    Archivo a;
//    System.out.print("Ingrese el bloque inicial : ");
//    int dato = Integer.parseInt(leer.readLine());
//    while (disk.getN_bloques().get(dato).isEstado()){
//      System.out.print("Implementacion.Bloque inicial ocupado, ingrese un bloque inicial valido : ");
//      dato = Integer.parseInt(leer.readLine());
//    }

    a = new Archivo();
    a.setNombre("carta.pdf");
    a.setTamanio(6);
    disk.agregarDir(a);
    //disk.asignacionContigua(a, dato);
    disk.asginacionVinculada(a);
    System.out.println();
    disk.verDisco();
    System.out.println();
    disk.getDir().infoDirectorio();
  }

}
