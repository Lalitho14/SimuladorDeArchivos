package Controladores;

import Implementacion.DirFisica;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.util.ArrayList;

public class ControlDirecciones {
  @FXML
  private Button btn_dirFisicas;
  @FXML
  private TextField caras_pista;
  @FXML
  private TextField direccion_logica;
  @FXML
  private Label resultado;
  @FXML
  private TextField sec_pista;
  @FXML
  private TableView<DirFisica> tabla_direcciones;
  @FXML
  private TableColumn<DirFisica, Integer> direcciones_logicas;
  @FXML
  private TableColumn<DirFisica, Integer> cilindros;
  @FXML
  private TableColumn<DirFisica, Integer> caras;
  @FXML
  private TableColumn<DirFisica, Integer> sectores;

  private ArrayList<DirFisica> listaDirecciones;

  @FXML
  void verDireccionesFisicas(ActionEvent event) {
    direcciones_logicas.setCellValueFactory(new PropertyValueFactory<DirFisica, Integer>("direccionLogica"));
    cilindros.setCellValueFactory(new PropertyValueFactory<DirFisica, Integer>("cilindro"));
    caras.setCellValueFactory(new PropertyValueFactory<DirFisica, Integer>("cara"));
    sectores.setCellValueFactory(new PropertyValueFactory<DirFisica, Integer>("sector"));

    ObservableList<DirFisica> lista = tabla_direcciones.getItems();
    lista.clear();

    calcularDirecciones();
    lista.addAll(listaDirecciones);

    tabla_direcciones.setItems(lista);
    tabla_direcciones.refresh();
  }

  private void calcularDirecciones() {
    listaDirecciones = new ArrayList<>();
    try {
      int direccionLogica = Integer.parseInt(direccion_logica.getText());

      for (int i = 0; i <= direccionLogica; i++) {
        int cilindro = (i / Integer.parseInt(sec_pista.getText())) / Integer.parseInt(caras_pista.getText());
        int cara = (i / Integer.parseInt(sec_pista.getText())) % Integer.parseInt(caras_pista.getText());
        int sector = i % Integer.parseInt(sec_pista.getText());

        DirFisica d = new DirFisica(i, cilindro, cara, sector);
        listaDirecciones.add(d);

        if (i == direccionLogica) {
          resultado.setText("Direccion Fisica : (" + d.getCilindro() + " , " + d.getCara() + " , " + d.getSector() + ")");
        }

      }

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Datos no validos. Utilice numeros.", "Error al ingresar datos.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }
}
