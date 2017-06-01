import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalTime;

public class CreateController extends CheckInput {
  @FXML
  private TextField nameAddField;
  @FXML
  private TextField numberAddField;
  @FXML
  private TextField timeAddField;

  @FXML
  private void initialize() {
    timeAddField.setText(Client.getStringNowTime());
  }

  @FXML
  private void handleOk() throws InterruptedException {
    if (checkInput(nameAddField, numberAddField, timeAddField)) {
      MainAppController.addClient(nameAddField.getText(),
          Integer.parseInt(numberAddField.getText()),
          LocalTime.parse(timeAddField.getText()));
      closeWindow();
    }
  }

  @FXML
  private void closeWindow() {
    Stage stage = (Stage) nameAddField.getScene().getWindow();
    stage.close();
  }
}


