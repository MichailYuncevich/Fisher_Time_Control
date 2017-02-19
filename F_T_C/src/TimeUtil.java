import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
  public static String getStringNowTime() {
    return DateTimeFormatter.ofPattern("HH:mm").format(LocalTime.now());
  }

  public static String getStringTime(LocalTime time) {
    return DateTimeFormatter.ofPattern("HH:mm").format(time);
  }
}
