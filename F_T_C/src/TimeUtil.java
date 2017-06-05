import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class TimeUtil {
  static String getStringNowTime() {
    return DateTimeFormatter.ofPattern("HH:mm").format(LocalTime.now());
  }

  static String getStringCurrTime(LocalTime time) {
    int deffTime = LocalTime.now().toSecondOfDay() - time.toSecondOfDay();
    return DateTimeFormatter.ofPattern("HH:mm").format(LocalTime.ofSecondOfDay(deffTime));
  }

  static String getStringTime(LocalTime time) {
    return DateTimeFormatter.ofPattern("HH:mm").format(time);
  }
}
