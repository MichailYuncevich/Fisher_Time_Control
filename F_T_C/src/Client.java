import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

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

  public Client() {
    this.name = null;
    this.number = null;
    this.startTime = null;
    this.time = null;
  }

  public Client(Client oldClient) {
    this.name = oldClient.name;
    this.number = oldClient.number;
    this.startTime = oldClient.startTime;
    this.time = oldClient.time;
  }

  public Client(String newName, Integer newNumber, LocalTime newTime) {
    this.name = newName;
    this.number = newNumber;
    this.startTime = newTime;
    this.time = TimeUtil.getStringTime(newTime);
  }

  public String getName() {
    return name;
  }

  public Integer getNumber() {
    return number;
  }

  public String getTime() {
    return TimeUtil.getStringTime(startTime);
  }

  public String getCurrTime() { return TimeUtil.getStringCurrTime(startTime); }

  public void setName(String name) {
    this.name = name;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public void setTime(LocalTime time) {
    startTime = time;
    this.time = TimeUtil.getStringTime(time);
  }

  public BigDecimal getBill() {
    LocalTime nowTime = LocalTime.now();
    BigDecimal Bill;
    int hour = nowTime.getHour() - startTime.getHour();
    int minute = nowTime.getMinute() - startTime.getMinute();
    if (hour > 3 || hour < 0)
      Bill = new BigDecimal(Setting.maxBill);
    else if ((hour < 1)||(minute < 0 && hour == 1 ))
      Bill = new BigDecimal(Setting.firstHour);
    else {
      BigDecimal timeBill= new BigDecimal(hour * 60 + minute);
      BigDecimal coastMinBill = new BigDecimal(Setting.coastMinute);
      Bill = coastMinBill.multiply(timeBill);
    }
    return Bill.setScale(2, BigDecimal.ROUND_DOWN);
  }
}