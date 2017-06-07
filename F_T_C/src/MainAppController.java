import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalTime;

public class MainAppController {
  static ObservableList<Client> clientData = FXCollections.observableArrayList();

  @FXML
  private TableView<Client> clientTable;
  @FXML
  private TableColumn<Client, String> nameColumn;
  @FXML
  private TableColumn<Client, Integer> numberColumn;
  @FXML
  private TableColumn<Client, String> timeColumn;

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
  public void showAddDialog() throws Exception {
    Stage addStage = new Stage();
    Parent root = FXMLLoader.load(this.getClass().getResource("XML_Files/CreateDialog.fxml"));
    Stage primaryStage = (Stage) this.nameLabel.getScene().getWindow();
    addStage.getIcons().add(new Image("resource/fisher.png"));
    addStage.initModality(Modality.WINDOW_MODAL);
    addStage.initOwner(primaryStage);
    addStage.setTitle("Добавить новых посетителей");
    addStage.setScene(new Scene(root, 250, 160));
    addStage.setResizable(false);

    addStage.showAndWait();
    FileUtil.writeClientData(clientData);
  }

  @FXML
  public void showEditDialog() throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("XML_Files/EditDialog.fxml"));
    Parent root = loader.load();
    EditController ctrl = loader.getController();
    ctrl.initEdit(clientTable.getSelectionModel().getSelectedItem(), clientTable.getSelectionModel().getSelectedIndex());

    Stage editStage = new Stage();
    Stage primaryStage = (Stage) this.nameLabel.getScene().getWindow();
    editStage.getIcons().add(new Image("resource/fisher.png"));
    editStage.initModality(Modality.WINDOW_MODAL);
    editStage.initOwner(primaryStage);
    editStage.setTitle("Изменение посетителей");
    editStage.setScene(new Scene(root, 250, 200));
    editStage.setResizable(false);

    editStage.showAndWait();
    FileUtil.writeClientData(clientData);
  }

  @FXML
  public void showBillDialog() throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("XML_Files/BillDialog.fxml"));
    Parent root = loader.load();
    BillController ctrl = loader.getController();
    ctrl.initBill(clientTable.getSelectionModel().getSelectedItem(), clientTable.getSelectionModel().getSelectedIndex());

    Stage billStage = new Stage();
    Stage primaryStage = (Stage) this.nameLabel.getScene().getWindow();
    billStage.getIcons().add(new Image("resource/fisher.png"));
    billStage.initModality(Modality.WINDOW_MODAL);
    billStage.initOwner(primaryStage);
    billStage.setTitle("Расчет посетителей");
    billStage.setScene(new Scene(root, 250, 200));
    billStage.setResizable(false);
    billStage.setUserData(clientTable.getSelectionModel().getSelectedItem());

    billStage.showAndWait();
    FileUtil.writeClientData(clientData);
  }

  @FXML
  private void initialize() {
    if (FileUtil.checkFile()) clientData = FileUtil.readClientData();
    timeColumn.setCellValueFactory(new PropertyValueFactory("time"));
    nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    numberColumn.setCellValueFactory(new PropertyValueFactory("number"));
    clientTable.setItems(clientData);
  }

  private void showClientDetails(Client client) {
    if (client != null) {
      nameLabel.setText(client.getName());
      numberLabel.setText(client.getNumber().toString());
      startTime.setText(client.getTime());
      nowTime.setText(client.getCurrTime());
      billLabel.setText(client.getBill().toString());
      BigDecimal numberBig = new BigDecimal((client.getNumber()));
      billAll.setText(client.getBill().multiply(numberBig).toString());
    } else {
      nameLabel.setText("");
      numberLabel.setText("");
      startTime.setText("");
      nowTime.setText("");
      billLabel.setText("");
      billAll.setText("");
    }
  }

  public void selectClick() throws Exception {
    showClientDetails(clientTable.getSelectionModel().getSelectedItem());
  }

  public void selectPress() throws Exception {
    showClientDetails(clientTable.getSelectionModel().getSelectedItem());
  }

  static void addClient(String name, Integer number, LocalTime time) {
    clientData.add(new Client(name, number, time));
  }

  public void deleteClient() {
    int index = clientTable.getSelectionModel().getSelectedIndex();
    if (index >= 0) {
      clientData.remove(index);
    } else {
      CheckInput.alertMessage("No Selection","Посетитель не выбран","Пожалуйста выберете посетителся в списке");
    }
    FileUtil.writeClientData(clientData);
  }
}