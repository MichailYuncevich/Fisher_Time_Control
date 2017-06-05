import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalTime;

public class CreateController {
  @FXML
  private TextField nameAddField;
  @FXML
  private TextField numberAddField;
  @FXML
  private TextField timeAddField;

  @FXML
  private void initialize() {
    timeAddField.setText(TimeUtil.getStringNowTime());
  }

  @FXML
  private void handleOk() throws InterruptedException {
    if (CheckInput.checkInput(nameAddField, numberAddField, timeAddField)) {
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


