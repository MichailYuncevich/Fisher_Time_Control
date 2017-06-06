import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

class FileUtil {
  private static File clientFile = new File("FTC.txt");

  public static boolean checkFile(){
    if (!clientFile.exists()) {
      try {
        clientFile.createNewFile();
      } catch (IOException e) {
        CheckInput.alertMessage("File not open", "Файл не создан", "Записи не сохраняться");
      }
      return false;
    } else return true;
  }

  static ObservableList<Client> readClientData() {
      try {
        ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(clientFile)));
        ArrayList<Client> bufClientDataArray = (ArrayList<Client>) input.readObject();
        input.close();
        ObservableList<Client> bufClientData = FXCollections.observableArrayList();
        for (Client aBufClientDataArray : bufClientDataArray) {
          bufClientData.add(aBufClientDataArray);
        }
        return bufClientData;
      } catch (Exception e) {
        System.out.println();
        CheckInput.alertMessage("File not load", "Файл не загружается", "Записи не загружены");
        clientFile.delete();
        checkFile();
        return FXCollections.observableArrayList();
      }
  }

  static void writeClientData(ObservableList<Client> clientData) {
    if (clientData.isEmpty()){
      clientFile.delete();
      return;
    }
    try {
      FileOutputStream fileOutStr = new FileOutputStream(clientFile);
      ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(fileOutStr));
      ArrayList<Client> bufClientArray = new ArrayList<>();
      for (Client aClientData : clientData) {
        bufClientArray.add(aClientData);
      }
      output.writeObject(bufClientArray);
      output.close();
      fileOutStr.close();
    } catch (IOException e){
      CheckInput.alertMessage("File not write", "Файл не записан", "Записи не записаны");
    }
  }
}
