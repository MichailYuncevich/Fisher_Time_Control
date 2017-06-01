
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalTime;

public class EditController extends CheckInput {
  private Client editClient;
  private int indexClient;
  @FXML
  private TextField nameEditField;
  @FXML
  private TextField numberEditField;
  @FXML
  private TextField timeEditField;

  @FXML
  private void initialize() {
  }

  public void initEdit(Client client, int index) {
    indexClient = index;
    editClient = client;
    nameEditField.setText(client.getName());
    numberEditField.setText(client.getNumber().toString());
    timeEditField.setText(client.getTime());
  }

  @FXML
  private void handleOk() throws InterruptedException {
    if (checkInput(nameEditField, numberEditField, timeEditField)) {
      editClient.setName(nameEditField.getText());
      editClient.setNumber(Integer.parseInt(numberEditField.getText()));
      editClient.setTime(LocalTime.parse(timeEditField.getText()));
      MainAppController.clientData.set(indexClient, editClient);
      closeWindow();
    }
  }

  @FXML
  private void closeWindow() {
    Stage stage = (Stage) nameEditField.getScene().getWindow();
    stage.close();
  }
}


