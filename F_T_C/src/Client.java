import java.io.Serializable;
import java.time.LocalTime;

public class Client implements Serializable {
  public static class Setting {
    static int maxBill = 12;
    static int firstHour = 3;
    static double coastMinute = 0.05;
  }

  private String name;
  private Integer number;
  private String time;
  private LocalTime startTime;

  public Client(Client oldClient) {
    this.name = oldClient.name;
    this.number = oldClient.number;
    this.startTime = oldClient.startTime;
    this.time = oldClient.time;
  }

  public Client() {
    this.name = null;
    this.number = null;
    this.startTime = null;
    this.time = null;
  }

  public Client(String nname, Integer nnumber, LocalTime ntime) {
    this.name = nname;
    this.number = nnumber;
    this.startTime = ntime;
    this.time = TimeUtil.getStringTime(ntime);
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
    return TimeUtil.getStringTime(startTime);
  }

  public String getCurrTime() { return TimeUtil.getStringCurrTime(startTime); }

  public void setTime(LocalTime time) {
    startTime = time;
    this.time = TimeUtil.getStringTime(time);
  }

  public Double getBill() {
    LocalTime nowTime = LocalTime.now();
    double Bill;
    int hour = nowTime.getHour() - startTime.getHour();
    int minute = nowTime.getMinute() - startTime.getMinute();
    if (hour > 3 || hour < 0)
      Bill = Setting.maxBill;
    else if (hour < 1)
      Bill = Setting.firstHour;
    else
      Bill = (hour * 60 + minute) * Setting.coastMinute;
    return Bill;
  }
}