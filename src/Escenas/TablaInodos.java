package Escenas;

import Implementacion.Archivo;
import Implementacion.Disco;
import Implementacion.i_nodos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TablaInodos extends JFrame {
  private JPanel main;
  private JTable table1;
  private JTable table2;

  private Disco disk;
  private String[][] datos;

  public TablaInodos(Disco disk) {
    this.disk = disk;
    setContentPane(main);
    setTitle("Tabla de I-nodos");
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
  }

  /*public static void main(String[] args) {
    TablaInodos i = new TablaInodos();
  }*/

  private void createUIComponents() {
    // TODO: place custom component creation code here
    table1 = new JTable(setModel1());
    table2 = new JTable(setModel2());
  }

  public DefaultTableModel setModel1() {
    String[] titulos = {"Archivo", "I-nodos"};
    datos = new String[disk.getDir().getArchivos().size()][2];
    int num_inodos = 0;
    for (int i = 0; i < disk.getDir().getArchivos().size(); i++) {
      datos[i][0] = disk.getDir().getArchivos().get(i).getNombre();
      System.out.println(datos[i][0]);
      num_inodos++;
      datos[i][1] = "I-nodo " + num_inodos;
      System.out.println(datos[i][1]);
    }

    return new DefaultTableModel(datos, titulos);
  }

  public DefaultTableModel setModel2() {
    String[] titulos = new String[disk.getDir().getArchivos().size()];

    for (int i = 0; i < disk.getDir().getArchivos().size(); i++) {
      titulos[i] = datos[i][1];
    }

    String[][] info;
    int n = 0;

    for (Archivo a : disk.getDir().getArchivos()) {
      if (n < a.getLista_inodos().size())
        n = a.getLista_inodos().size();
    }
    n+=2;
    info = new String[n][disk.getDir().getArchivos().size()];
    i_nodos temp = null;
    int k = 0, i = 0;
    for (Archivo a : disk.getDir().getArchivos()) {
      for (i_nodos inodo : a.getLista_inodos()) {
        temp = inodo;
        info[k][i] = inodo.getDato() + "";
        k++;
      }
      if(temp != null){
        info[k][i] = temp.getSig_dato() + "";
        k++;
        info[k][i] = "EOF";
      }
      i++;
      k = 0;
    }


    return new DefaultTableModel(info, titulos);
  }
}
