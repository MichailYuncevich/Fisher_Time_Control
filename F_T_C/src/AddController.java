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
    timeField.setText(Client.getStringNowTime());
  }

  @FXML
  private void handleOk() {
    if (checkInput()) {
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

  private Boolean checkInput() {
    String errorMessage = "";
    if (nameField.getText() == null || nameField.getText().length() == 0) {
      errorMessage += "Введите имя!\n";
    }
    if (numberField.getText() == null || nameField.getText().length() == 0) {
      errorMessage += "Введте количество посетителей!\n";
    } else {
      try {
        Integer.parseInt(numberField.getText());
      } catch (NumberFormatException e) {
        errorMessage += "Введите число!\n";
      }
    }
    if (timeField.getText() == null || timeField.getText().length() == 0) {
      errorMessage += "Введите время!\n";
    } else {
      if (validTime(timeField.getText())) {
        errorMessage += "Введите время в формате HH:mm\n";
      }
    }

    if (errorMessage.length() == 0) {
      return true;
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.initOwner(nameField.getScene().getWindow());
      alert.setTitle("Invalid Fields");
      alert.setHeaderText("Пожалуйста введите коректное значение");
      alert.setContentText(errorMessage);
      alert.showAndWait();
      return false;
    }
  }

  public static boolean validTime(String dateString) {
    return DateTimeFormatter.ofPattern("HH:mm").equals(dateString);
  }
}


