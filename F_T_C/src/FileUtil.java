import java.io.File;

public class FileUtil {
  File clientFile;

  public void checkFile(){
    if (!clientFile.exists()) {
      clientFile = new File("Client");
    }
    else {

    }
  }
}
