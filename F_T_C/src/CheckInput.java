import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.DateTimeException;
import java.time.LocalTime;

public class CheckInput {
  public boolean checkInput(TextField nameField, TextField numberField, TextField timeField) {
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
      try {
        LocalTime.parse(timeField.getText());
      } catch (DateTimeException e) {
        errorMessage += "Введите время в формате HH:mm!\n";
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
}
