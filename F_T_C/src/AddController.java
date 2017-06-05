import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddController {
  @FXML
  private TextField nameField;
  @FXML
  private TextField numberField;
  @FXML
  private TextField timeField;

  @FXML
  private void initialize() {
    timeField.setText(TimeUtil.getStringNowTime());
  }

  @FXML
  private void handleOk() {
    if (CheckInput.checkInput(nameField, numberField, timeField)) {
      MainAppController.addClient(nameField.getText(),
          Integer.parseInt(numberField.getText()),
          LocalTime.parse(timeField.getText()));
      closeWindow();
    }
  }

  @FXML
  private void closeWindow() {
    Stage stage = (Stage) nameField.getScene().getWindow();
    stage.close();
  }



  public static boolean validTime(String dateString) {
    return DateTimeFormatter.ofPattern("HH:mm").equals(dateString);
  }
}


