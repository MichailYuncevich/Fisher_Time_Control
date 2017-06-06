import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.DateTimeException;
import java.time.LocalTime;

class CheckInput {
  static boolean checkInput(TextField nameField, TextField numberField, TextField timeField) {
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
        errorMessage += "Введите количество числом!\n";
      }
    }
    if (timeField.getText() == null || timeField.getText().length() == 0) {
      errorMessage += "Введите время!\n";
    } else {
        try {
          if ((LocalTime.now().toSecondOfDay()- LocalTime.parse(timeField.getText()).toSecondOfDay())<0){
            errorMessage += "Указанное время позже текущего!\n";
          }
        } catch (DateTimeException e) {
          errorMessage += "Введите время в формате HH:mm!\n";
        }
    }
    if (errorMessage.length() == 0) {
      return true;
    } else {
      alertMessage("Invalid Fields", "Пожалуйста введите коректное значение", errorMessage);
      return false;
    }
  }

  static void alertMessage(String title, String headText, String contentText){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headText);
    alert.setContentText(contentText);
    alert.showAndWait();
  }
}
