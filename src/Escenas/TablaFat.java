package Escenas;

import Implementacion.Archivo;
import Implementacion.Disco;
import Implementacion.i_nodos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablaFat {

  private String[][] content;

  public TablaFat(Disco disk) {
    this.content = setContent(disk);
  }

  public void generarTabla() {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Tabla FAT");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(600, 600);

      // Crea un JScrollPane
      JScrollPane scrollPane = new JScrollPane();

      // Crea un JPanel que contendrá el contenido del JScrollPane
      JPanel contentPanel = new JPanel();
      contentPanel.setLayout(new BorderLayout());

      String[] titulos = {"0", "1", "2", "3", "4", ",5", "6", "7"};
      //String[][] content = {{"1", "2", "3", "hola mundo"}, {"1", "2", "3", "hola mundo"}, {"1", "2", "3", "hola mundo"}, {"1", "2", "3", "hola mundo"}, {"1", "2", "3", "hola mundo"}, {"1", "2", "3", "hola mundo"}, {"1", "2", "3", "hola mundo"}, {"1", "2", "3", "hola mundo"}};
      // Agrega contenido al panel (por ejemplo, un JTextArea)
      DefaultTableModel defaultTableModel = new DefaultTableModel(content, titulos);
      JTable tabla = new JTable(defaultTableModel);
      contentPanel.add(tabla, BorderLayout.CENTER);

      DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
      cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

      for (int i = 0; i < tabla.getColumnCount(); i++)
        tabla.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);


      // Configura el JScrollPane para contener el panel
      scrollPane.getViewport().add(contentPanel);
      // Configura el JScrollPane para ocupar todo el espacio del JFrame
      frame.add(scrollPane, BorderLayout.CENTER);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }

  public String[][] setContent(Disco disk) {
    String[][] content = new String[disk.getN_bloques().size() / 8][8];
    int k = 0;
    for (int i = 0; i < disk.getN_bloques().size() / 8; i++) {
      for (int j = 0; j < 8; j++) {
        content[i][j] = "";
        k++;
      }
    }

    k = 0;

    i_nodos temp = null;
    for (Archivo a : disk.getDir().getArchivos()) {
      for (i_nodos i_nodos : a.getLista_inodos()) {
        temp = i_nodos;
        for (int i = 0; i < disk.getN_bloques().size() / 8; i++) {
          for (int j = 0; j < 8; j++) {
            if (k == i_nodos.getDato()) {
              content[i][j] = i_nodos.getSig_dato() + "";
            }
            k++;
          }
        }
        k = 0;
      }
      for (int i = 0; i < disk.getN_bloques().size() / 8; i++) {
        for (int j = 0; j < 8; j++) {
          if (temp != null && k == temp.getSig_dato()) {
            content[i][j] = "EOF";
          }
          k++;
        }
      }
      k = 0;
    }


    return content;
  }
}
