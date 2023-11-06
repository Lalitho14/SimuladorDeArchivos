import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class MainGui extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Escenas/menu.fxml")));
    Image icon = new Image("/img/icono.png");
    stage.getIcons().add(icon);
    stage.setTitle("Simulador de Archivos");
    stage.setScene(new Scene(root, 800, 600));
    stage.setWidth(800);
    stage.setHeight(600);
    stage.setMinWidth(800);
    stage.setMinHeight(600);
    stage.show();
  }
}
