import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalTime;

public class AddController {
  @FXML
  private TextField nameField;
  @FXML
  private TextField numberField;
  @FXML
  private TextField timeField;
  @FXML
  private Button closeButton;

  @FXML
  private void initialize() {
    timeField.setText(Client.getStringNowTime());
  }

  @FXML
  private void handleOk() {
    MainAppController.addClient(nameField.getText(),
        Integer.parseInt(numberField.getText()),
        LocalTime.parse(timeField.getText()));
    closeWindow();
  }

  @FXML
  public void closeWindow() {
    Stage stage = (Stage) this.closeButton.getScene().getWindow();
    stage.close();
  }
}


