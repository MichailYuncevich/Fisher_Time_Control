import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
  public MainApplication() {
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
    primaryStage.setTitle("Fisher Time Control");
    primaryStage.setScene(new Scene(root, 900, 500));
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
