import java.time.Clock;
import java.time.LocalTime;

public class Client extends TimeUtil {
  public static class Setting {
    static int maxBill = 12;
    static int firstHour = 3;
    static double coastMinute = 0.05;
  }

  private String name;
  private Integer number;
  private LocalTime startTime;

  public Client(String name, Integer number) {
    this.name = new String(name);
    this.number = new Integer(number);
    this.startTime = LocalTime.now(Clock.systemDefaultZone());
  }

  public Client(String name, Integer number, LocalTime time) {
    this.name = new String(name);
    this.number = new Integer(number);
    this.startTime = time;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getTime() {
    return getStringTime(startTime);
  }

  public void setTime(LocalTime time) {
    this.startTime = time;
  }

  public Double getBill() {
    LocalTime nowTime = LocalTime.now();
    double Bill = 0;
    int hour = nowTime.getHour() - startTime.getHour();
    int minute = nowTime.getMinute() - startTime.getMinute();
    if (hour > 3)
      Bill = Setting.maxBill;
    else if (hour < 1)
      Bill = Setting.firstHour;
    else
      Bill = (hour * 60 + minute) * Setting.coastMinute;
    return Bill;
  }
}