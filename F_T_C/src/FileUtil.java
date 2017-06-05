import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {
  static File clientFile = new File("FTC.txt");

  public static boolean checkFile(){
    if (!clientFile.exists()) {
      try {
        clientFile.createNewFile();
      } catch (IOException e) {
        CheckInput.alertMessage("File not open", "Файл не создан", "Записи не сохраняться");
      } finally {
        return false;
      }
    } else return true;
  }

  public static ObservableList<Client> readClientData() {
    try {
      ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(clientFile)));
      ArrayList<Client>  bufClientDataArray = (ArrayList<Client>) input.readObject();
      input.close();
      ObservableList<Client> bufClientData = FXCollections.observableArrayList();
      for(int i=0; i<bufClientDataArray.size(); i++){
        bufClientData.add(bufClientDataArray.get(i));
      }
      return bufClientData;
    } catch (Exception e) {
      System.out.println();
      CheckInput.alertMessage("File not load", "Файл не загружается", "Записи не загружены");
      return null;
    }
  }

  public static void writeClientData(ObservableList<Client> clientData) {
    try {
      FileOutputStream fileOutStr = new FileOutputStream(clientFile);
      ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(fileOutStr));
      ArrayList<Client> bufClientArray = new ArrayList<>();
      for(int i=0; i<clientData.size(); i++){
        bufClientArray.add(clientData.get(i));
      }
      output.writeObject(bufClientArray);
      output.close();
      fileOutStr.close();
    } catch (IOException e){
      CheckInput.alertMessage("File not write", "Файл не записан", "Записи не записаны");
    }
  }
}
