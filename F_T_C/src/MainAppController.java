
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalTime;

public class MainAppController {
  private static ObservableList<Client> clientData = FXCollections.observableArrayList();
  @FXML
  private TableView<Client> clientTable;
  @FXML
  private TableColumn<Client, String> nameColumn;
  @FXML
  private TableColumn<Client, String> numberColumn;

  @FXML
  private Label nameLabel;
  @FXML
  private Label numberLabel;
  @FXML
  private Label startTime;
  @FXML
  private Label nowTime;
  @FXML
  private Label billAll;
  @FXML
  private Label billLabel;
  @FXML
  private Button addUserButton;

  @FXML
  public void showAddDialog() throws Exception {
    Stage addStage = new Stage();
    Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("CreateDialog.fxml"));
    Stage primaryStage = (Stage) this.addUserButton.getScene().getWindow();
    addStage.initModality(Modality.WINDOW_MODAL);
    addStage.initOwner(primaryStage);
    addStage.setTitle("Добавить новых посетителей");
    addStage.setScene(new Scene(root, 300.0D, 200.0D));
    addStage.setResizable(false);
    addStage.show();
  }

  @FXML
  private void initialize() {
    clientData.add(new Client("Mic", 1));
    clientData.add(new Client("Rec", 2, LocalTime.of(13, 15)));
    this.nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    this.numberColumn.setCellValueFactory(new PropertyValueFactory("number"));
    this.clientTable.setItems(clientData);
    clientTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showClientDetails(newValue));
  }

  private void showClientDetails(Client client) {
    if (client != null) {
      nameLabel.setText(client.getName());
      numberLabel.setText(client.getNumber().toString());
      startTime.setText(client.getTime());
      nowTime.setText(client.getStringNowTime());
      billLabel.setText(client.getBill().toString());
      Double all = client.getNumber() * client.getBill();
      billAll.setText(all.toString());
    } else {
      nameLabel.setText("");
      numberLabel.setText("");
      startTime.setText("");
      nowTime.setText("");
      billLabel.setText("");
      billAll.setText("");
    }
  }

  public static void addClient(String name, Integer number, LocalTime time) {
    clientData.add(new Client(name, number, time));
  }

  public void deleteClient() {
    int index = clientTable.getSelectionModel().getSelectedIndex();
    if (index >= 0) {
      clientData.remove(index);
    } else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.initOwner(this.addUserButton.getScene().getWindow());
      alert.setTitle("No Selection");
      alert.setHeaderText("No Person Selected");
      alert.setContentText("Please select a person in the table.");
      alert.showAndWait();
    }
  }
}