package Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import Implementacion.*;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

public class ControlMenu {
  Disco disk;
  Archivo a;
  @FXML
  private Button asignacion_contigua;
  @FXML
  private Button asignacion_indexada;
  @FXML
  private Button asignacion_vinculada;
  @FXML
  private Button borrar_archivo;
  @FXML
  private ColorPicker color_archivo;
  @FXML
  private Button borrar_disk;
  @FXML
  private Button crear_archivo;
  @FXML
  private Button crear_disk;
  @FXML
  private Button asignarC;
  @FXML
  private TextField nom_archivo_borrar;
  @FXML
  private TextField nombre_archivo;
  @FXML
  private TextField tam_archivo;
  @FXML
  private TextField tam_bloque_disk;
  @FXML
  private TextField tam_disk;
  @FXML
  private TextField bloque_inicial;
  @FXML
  private AnchorPane metodos_menu;
  @FXML
  private AnchorPane leer_asignacionContigua;
  @FXML
  private AnchorPane disco_pane;

  @FXML
  void crearDisk(ActionEvent event) {
    int t, tb;
    try {
      t = Integer.parseInt(tam_disk.getText());
      tb = Integer.parseInt(tam_bloque_disk.getText());
      if (t % tb != 0) {
        JOptionPane.showMessageDialog(null, "Datos no validos.", "Error al crear disco.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
      } else {
        disk = new Disco(t, tb);
        disk.InicializarDisco();
        metodos_menu.setDisable(false);
        disco_pane.setDisable(true);
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Datos no validos. Utilice numeros.", "Error al crear disco.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }

  @FXML
  void CrearArchivo(ActionEvent event) {
    a = new Archivo();
    if (!nombre_archivo.getText().isEmpty()) {
      a.setNombre(nombre_archivo.getText());
      try {
        a.setTamanio(Integer.parseInt(tam_archivo.getText()));
        disk.agregarDir(a);
        nombre_archivo.setDisable(true);
        tam_archivo.setDisable(true);
        crear_archivo.setDisable(true);
        color_archivo.setDisable(true);
        bloque_inicial.setDisable(false);
        asignarC.setDisable(false);
        JOptionPane.showMessageDialog(null, "Se agrego correctamente " + a.getNombre() + ".", "Archivo - " + a.getNombre(), JOptionPane.INFORMATION_MESSAGE);// Mensaje con Error
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Datos no validos. Utilice numeros.", "Error al crear disco.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
      }
    } else {
      JOptionPane.showMessageDialog(null, "Datos no validos. Utilice numeros.", "Error al crear disco.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }

  @FXML
  void AsignacionContiguaGUI(ActionEvent event) {
    if (!disk.getDir().getArchivos().isEmpty()) {
      asignacion_indexada.setDisable(true);
      asignacion_indexada.setVisible(false);
      asignacion_vinculada.setDisable(true);
      asignacion_vinculada.setVisible(false);
      asignacion_contigua.setDisable(true);
      asignacion_contigua.setVisible(false);
      leer_asignacionContigua.setVisible(true);
      leer_asignacionContigua.setDisable(false);
    } else {
      JOptionPane.showMessageDialog(null, "Agregue Archivos para iniciar simulación.", "No hay archivos.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }

  @FXML
  void asignarC(ActionEvent event) {
    try {
      int inicio = Integer.parseInt(bloque_inicial.getText());
      if (!disk.getN_bloques().get(inicio).isEstado()) {
        disk.asignacionContigua(a, inicio);
        nombre_archivo.setDisable(false);
        nombre_archivo.setText("");
        tam_archivo.setDisable(false);
        tam_archivo.setText("");
        crear_archivo.setDisable(false);
        color_archivo.setDisable(false);
        bloque_inicial.setDisable(true);
        bloque_inicial.setText("");
        asignarC.setDisable(true);
        disk.verDisco();
        System.out.println();
        disk.getDir().infoDirectorio();
      } else {
        JOptionPane.showMessageDialog(null, "El bloque ingresado esta en uso.", "Error al colocar bloque.", JOptionPane.WARNING_MESSAGE);// Mensaje con Error
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Datos no validos. Utilice numeros.", "Error al colocar bloque.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }

  @FXML
  void BorrarArchivo(ActionEvent event) {
    Archivo temp;
    if ((temp = disk.buscarArchivo(nom_archivo_borrar.getText())) != null) {
      disk.eliminarDeDisco(temp);
      nom_archivo_borrar.setText("");
      nom_archivo_borrar.setPromptText("Nombre Archivo");
      JOptionPane.showMessageDialog(null, "Se elimino archivo correctamente.", "Se elimino archivo.", JOptionPane.INFORMATION_MESSAGE);// Mensaje con Error
      disk.verDisco();
      System.out.println();
      disk.getDir().infoDirectorio();
    } else {
      JOptionPane.showMessageDialog(null, "El archivo " + nom_archivo_borrar.getText() + " no se encotro.", "No se encontro archivo.", JOptionPane.WARNING_MESSAGE);// Mensaje con Error
    }
  }

  @FXML
  void eliminarDisco(ActionEvent event) {
    disk = null;
    tam_disk.setText("");
    tam_disk.setPromptText("Tamaño Disco (kb)");
    tam_bloque_disk.setText("");
    tam_bloque_disk.setPromptText("Tamaño Bloque (kb)");
    metodos_menu.setDisable(true);
    disco_pane.setDisable(false);
    leer_asignacionContigua.setVisible(false);
    leer_asignacionContigua.setDisable(true);
    asignacion_indexada.setDisable(false);
    asignacion_indexada.setVisible(true);
    asignacion_vinculada.setDisable(false);
    asignacion_vinculada.setVisible(true);
    asignacion_contigua.setDisable(false);
    asignacion_contigua.setVisible(true);
  }
}
