package Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import Implementacion.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;

public class ControlMenu {
  private Disco disk;
  private Archivo a;
  private ArrayList<Integer> datos = new ArrayList<>();
  private ArrayList<TablaDir> tdir = new ArrayList<>();
  private int op = 0;

  @FXML
  private TableView<TablaDir> tabla_infoDir;
  @FXML
  private TableColumn<TablaDir, String> tabla_archivo;
  @FXML
  private TableColumn<TablaDir, Integer> tabla_fin;
  @FXML
  private TableColumn<TablaDir, Integer> tabla_inicio;
  @FXML
  private TableColumn<TablaDir, Integer> tabla_tamanio;
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
  private Button asignarV;
  @FXML
  private Button asignarI;
  @FXML
  private Text letrero_lectura;
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
  private AnchorPane leer_asignacion;
  @FXML
  private AnchorPane disco_pane;
  @FXML
  private ScrollPane mapa_disco;

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
        color_archivo.setDisable(false);
        crear_archivo.setDisable(false);
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
      a.setColor(color_archivo.getValue());
      try {
        a.setTamanio(Integer.parseInt(tam_archivo.getText()));
        disk.agregarDir(a);
        nombre_archivo.setDisable(true);
        tam_archivo.setDisable(true);
        crear_archivo.setDisable(true);
        color_archivo.setDisable(true);
        bloque_inicial.setDisable(false);
        asignarC.setDisable(false);
        asignarV.setDisable(false);
        asignarI.setDisable(false);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Datos no validos. Utilice numeros.", "Error al crear disco.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
      }
      if (!disk.espacioBloques((int) Math.ceil((double) a.getTamanio() / disk.getTamanio_bloque()))) {
        disk.getDir().borrarArchivo(a);
        nombre_archivo.setDisable(false);
        tam_archivo.setDisable(false);
        crear_archivo.setDisable(false);
        color_archivo.setDisable(false);
        bloque_inicial.setDisable(false);
        asignarC.setDisable(true);
        asignarV.setDisable(true);
        bloque_inicial.setDisable(true);
        JOptionPane.showMessageDialog(null, "No hay espacio suficiente para " + a.getNombre() + ".", "Archivo - " + a.getNombre(), JOptionPane.ERROR_MESSAGE);// Mensaje con Error
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
      leer_asignacion.setVisible(true);
      leer_asignacion.setDisable(false);
      asignarV.setDisable(true);
      asignarV.setVisible(false);
      asignarC.setDisable(false);
      asignarC.setVisible(true);
      asignarI.setDisable(true);
      asignarI.setVisible(false);
      op = 1;
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
        TablaDir tablaDir = new TablaDir(a.getNombre(), a.getInicio(), a.getTamanio(), Integer.parseInt(a.getBloqueFinal()));
        tdir.add(tablaDir);
        disk.verDisco();
        verDiscoGUI();
        actualizarTabla();
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
      tdir.remove(new TablaDir(temp.getNombre(), temp.getInicio(), temp.getTamanio(), Integer.parseInt(temp.getBloqueFinal())));
      actualizarTabla();
      nom_archivo_borrar.setText("");
      nom_archivo_borrar.setPromptText("Nombre Archivo");
      JOptionPane.showMessageDialog(null, "Se elimino archivo correctamente.", "Se elimino archivo.", JOptionPane.INFORMATION_MESSAGE);// Mensaje con Error
      disk.verDisco();
      if (op == 1) {
        verDiscoGUI();
      } else {
        verDiscoGUIAsignacionvinculada();
      }
      System.out.println();
      disk.getDir().infoDirectorio();
    } else {
      JOptionPane.showMessageDialog(null, "El archivo " + nom_archivo_borrar.getText() + " no se encotro.", "No se encontro archivo.", JOptionPane.WARNING_MESSAGE);// Mensaje con Error
    }
  }

  @FXML
  void eliminarDisco(ActionEvent event) {
    disk = null;
    nombre_archivo.setDisable(false);
    nombre_archivo.setText("");
    nombre_archivo.setPromptText("Ingrese Nombre de Archivo");
    tam_archivo.setDisable(false);
    tam_archivo.setText("");
    tam_archivo.setPromptText("Ingrese tamaño (kb)");
    tam_disk.setText("");
    tam_disk.setPromptText("Tamaño Disco (kb)");
    tam_bloque_disk.setText("");
    tam_bloque_disk.setPromptText("Tamaño Bloque (kb)");
    metodos_menu.setDisable(true);
    disco_pane.setDisable(false);
    leer_asignacion.setVisible(false);
    leer_asignacion.setDisable(true);
    asignacion_indexada.setDisable(false);
    asignacion_indexada.setVisible(true);
    asignacion_vinculada.setDisable(false);
    asignacion_vinculada.setVisible(true);
    asignacion_contigua.setDisable(false);
    asignacion_contigua.setVisible(true);
    mapa_disco.setContent(null);
    tdir.clear();
    datos.clear();
    tabla_infoDir.getItems().clear();
  }

  @FXML
  void AsignacionVinculadaGUI(ActionEvent event) {
    if (!disk.getDir().getArchivos().isEmpty()) {

      leer_asignacion.setDisable(false);
      leer_asignacion.setVisible(true);
      asignarC.setDisable(true);
      asignarC.setVisible(false);
      asignarV.setVisible(true);
      asignarV.setDisable(false);
      asignarI.setDisable(false);
      asignarI.setVisible(false);
      asignacion_indexada.setDisable(true);
      asignacion_indexada.setVisible(false);
      asignacion_vinculada.setDisable(true);
      asignacion_vinculada.setVisible(false);
      asignacion_contigua.setDisable(true);
      asignacion_contigua.setVisible(false);
      op = 2;
    } else {
      JOptionPane.showMessageDialog(null, "Agregue Archivos para iniciar simulación.", "No hay archivos.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }

  @FXML
  void asignarV(ActionEvent event) {
    try {
      if (datos.size() != Math.ceil((double) a.getTamanio() / disk.getTamanio_bloque())) {
        if (!disk.getN_bloques().get(Integer.parseInt(bloque_inicial.getText())).isEstado()) {
          datos.add(Integer.parseInt(bloque_inicial.getText()));
          bloque_inicial.setText("");
          String salida = "";
          for (int d : datos) {
            salida += d + " -> ";
          }
          letrero_lectura.setText(salida);
        } else {
          JOptionPane.showMessageDialog(null, "Bloque en uso. Ingrese otro.", "Error al colocar bloque.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
          bloque_inicial.setText("");
        }
      }
      if (datos.size() == Math.ceil((double) a.getTamanio() / disk.getTamanio_bloque())) {
        disk.asginacionVinculada(a, datos);
        nombre_archivo.setDisable(false);
        nombre_archivo.setText("");
        tam_archivo.setDisable(false);
        tam_archivo.setText("");
        crear_archivo.setDisable(false);
        color_archivo.setDisable(false);
        bloque_inicial.setDisable(true);
        bloque_inicial.setText("");
        asignarC.setDisable(true);
        asignarV.setDisable(true);
        asignarI.setDisable(true);
        disk.verDisco();
        verDiscoGUIAsignacionvinculada();
        letrero_lectura.setText("Ingrese bloque inicial : ");
        System.out.println();
        disk.getDir().infoDirectorio();

        TablaDir tablaDir = new TablaDir(a.getNombre(), a.getInicio(), a.getTamanio(), Integer.parseInt(a.getBloqueFinal()));
        tdir.add(tablaDir);
        actualizarTabla();
        datos.clear();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Datos no validos. Utilice numeros.", "Error al colocar bloque.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }

  @FXML
  void AsignacionIndexadaGUI(ActionEvent event) {
    if (!disk.getDir().getArchivos().isEmpty()) {

      leer_asignacion.setDisable(false);
      leer_asignacion.setVisible(true);
      asignarC.setDisable(true);
      asignarC.setVisible(false);
      asignarV.setVisible(false);
      asignarV.setDisable(true);
      asignarI.setVisible(true);
      asignarI.setDisable(false);
      asignacion_indexada.setDisable(true);
      asignacion_indexada.setVisible(false);
      asignacion_vinculada.setDisable(true);
      asignacion_vinculada.setVisible(false);
      asignacion_contigua.setDisable(true);
      asignacion_contigua.setVisible(false);
      op = 3;
    } else {
      JOptionPane.showMessageDialog(null, "Agregue Archivos para iniciar simulación.", "No hay archivos.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }

  @FXML
  void asignarI(ActionEvent event) {
    try {
      if (datos.size() != Math.ceil((double) a.getTamanio() / disk.getTamanio_bloque())) {
        if (!disk.getN_bloques().get(Integer.parseInt(bloque_inicial.getText())).isEstado()) {
          datos.add(Integer.parseInt(bloque_inicial.getText()));
          bloque_inicial.setText("");
          String salida = "";
          for (int d : datos) {
            salida += d + " ; ";
          }
          letrero_lectura.setText(salida);
        } else {
          JOptionPane.showMessageDialog(null, "Bloque en uso. Ingrese otro.", "Error al colocar bloque.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
          bloque_inicial.setText("");
        }
      }
      if (datos.size() == Math.ceil((double) a.getTamanio() / disk.getTamanio_bloque())) {
        disk.asginacionIndexada(a, datos);
        nombre_archivo.setDisable(false);
        nombre_archivo.setText("");
        tam_archivo.setDisable(false);
        tam_archivo.setText("");
        crear_archivo.setDisable(false);
        color_archivo.setDisable(false);
        bloque_inicial.setDisable(true);
        bloque_inicial.setText("");
        asignarC.setDisable(true);
        asignarV.setDisable(true);
        asignarI.setDisable(true);
        disk.verDisco();
        verDiscoGUIAsignacionvinculada();
        letrero_lectura.setText("Ingrese bloque inicial : ");
        System.out.println();
        disk.getDir().infoDirectorio();
        TablaDir tablaDir = new TablaDir(a.getNombre(), a.getInicio(), a.getTamanio(), Integer.parseInt(a.getBloqueFinal()));
        tdir.add(tablaDir);
        actualizarTabla();
        datos.clear();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Datos no validos. Utilice numeros.", "Error al colocar bloque.", JOptionPane.ERROR_MESSAGE);// Mensaje con Error
    }
  }

  void verDiscoGUI() {
    Pane root = new Pane();
    Rectangle r;
    double px = 175, py = 50; //0.014 0
    for (int i = 0; i < disk.getN_bloques().size(); i++) {
      if (i % 8 == 0 && i != 0) {
        px = 175;//0.014
        py += 75;//0.02
      }
      //0.1 0.1
      if (!disk.getN_bloques().get(i).isEstado())
        r = crearRectangulos(px, py, 50, 50, Color.WHITE, root);
      else
        r = crearRectangulos(px, py, 50, 50, disk.getN_bloques().get(i).getArchivo().getColor(), root);

      root.getChildren().add(r);

      Text text = new Text(String.valueOf(i));
      text.setX(px + 20); // Posición X del número
      text.setY(py + 25); // Posición Y del número

      root.getChildren().add(text);

      px += 75;//0.2
    }

    mapa_disco.setContent(root);
    mapa_disco.setFitToWidth(false);
    mapa_disco.setFitToHeight(false);
  }

  void verDiscoGUIAsignacionvinculada() {
    Pane root = new Pane();
    ArrayList<Rectangle> rectangles = new ArrayList<>();
    Rectangle r;
    double px = 175, py = 50; //0.014 0
    for (int i = 0; i < disk.getN_bloques().size(); i++) {
      if (i % 8 == 0 && i != 0) {
        px = 175;//0.014
        py += 75;//0.02
      }
      //0.1 0.1
      if (!disk.getN_bloques().get(i).isEstado())
        r = crearRectangulos(px, py, 50, 50, Color.WHITE, root);
      else
        r = crearRectangulos(px, py, 50, 50, disk.getN_bloques().get(i).getArchivo().getColor(), root);

      root.getChildren().add(r);
      rectangles.add(r);

      Text text = new Text(String.valueOf(i));
      text.setX(px + 20); // Posición X del número
      text.setY(py + 25); // Posición Y del número

      root.getChildren().add(text);

      px += 75;//0.2
    }

    /*for(i_nodos i: disk.getLista_inodos()){
      Line l = dibujarFlecha(rectangles.get(i.getDato()),rectangles.get(i.getSig_dato()));
      root.getChildren().add(l);
    }*/

    for (Archivo archivo : disk.getDir().getArchivos()) {
      for (i_nodos i : archivo.getLista_inodos()) {
        Line l = dibujarFlecha(rectangles.get(i.getDato()), rectangles.get(i.getSig_dato()));
        l.setStroke(archivo.getColor());
        l.setStrokeWidth(2);
        root.getChildren().add(l);
      }
    }

    mapa_disco.setContent(root);
    mapa_disco.setFitToWidth(false);
    mapa_disco.setFitToHeight(false);
  }

  private Rectangle crearRectangulos(double x, double y, double ancho, double alto, Color color, Pane root) {
    Rectangle r = new Rectangle(x, y, ancho, alto);
    r.setFill(color);

    /*root.widthProperty().addListener((obs, oldVal, newVal) -> {
      double newWidth = (double) newVal;
      r.setWidth(newWidth * ancho);
      r.setX(newWidth * x);
    });

    root.heightProperty().addListener((obs, oldVal, newVal) -> {
      double newHeight = (double) newVal;
      r.setHeight(newHeight * alto);
      r.setY(newHeight * y);
    });*/

    return r;
  }

  private Line dibujarFlecha(Rectangle origen, Rectangle destino) {
    //return new Line(75,50,150,75);

    return new Line(origen.getX() + (origen.getWidth() / 2), origen.getY() + origen.getHeight(), destino.getX() + ((destino.getWidth() / 2)), destino.getY());
  }

  private void actualizarTabla() {
    tabla_archivo.setCellValueFactory(new PropertyValueFactory<TablaDir, String>("nombre_archivo"));
    tabla_inicio.setCellValueFactory(new PropertyValueFactory<TablaDir, Integer>("inicio"));
    tabla_tamanio.setCellValueFactory(new PropertyValueFactory<TablaDir, Integer>("tamanio"));
    tabla_fin.setCellValueFactory(new PropertyValueFactory<TablaDir, Integer>("fin"));

    ObservableList<TablaDir> lista = tabla_infoDir.getItems();
    lista.clear();
    lista.addAll(tdir);
    tabla_infoDir.setItems(lista);
    tabla_infoDir.refresh();
    for (TablaDir t : tdir) {
      System.out.println(t.getNombre_archivo() + " " + t.getInicio() + " " + t.getTamanio() + " " + t.getFin());
    }
  }
}