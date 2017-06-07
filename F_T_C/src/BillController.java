import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class BillController {
  private Client clientBill;
  private int indexClient;

  @FXML
  private Label nameLabel;
  @FXML
  private Label numberLabel;
  @FXML
  private Label startLabel;
  @FXML
  private Label billAll;
  @FXML
  private Label billLabel;

  @FXML
  private void initialize() {
  }

  public void initBill(Client client, int index) {
    indexClient = index;
    clientBill = client;
    if (client != null) {
      nameLabel.setText(client.getName());
      numberLabel.setText(client.getNumber().toString());
      startLabel.setText(client.getTime());
      billLabel.setText(client.getBill().toString());
      BigDecimal numberBig = new BigDecimal((client.getNumber()));
      billAll.setText(client.getBill().multiply(numberBig).toString());
    } else {
      nameLabel.setText("");
      numberLabel.setText("");
      startLabel.setText("");
      billLabel.setText("");
      billAll.setText("");
    }
  }

  @FXML
  public void handleBillOK() {
    if (indexClient >= 0) {
      MainAppController.clientData.remove(indexClient);
    } else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.initOwner(nameLabel.getScene().getWindow());
      alert.setTitle("No Selection");
      alert.setHeaderText("Посетитель не выбран");
      alert.setContentText("Пожалуйста выберете посетителся в списке");
      alert.showAndWait();
    }
    closeWindow();
  }

  @FXML
  private void closeWindow() {
    Stage stage = (Stage) nameLabel.getScene().getWindow();
    stage.close();
  }
}
