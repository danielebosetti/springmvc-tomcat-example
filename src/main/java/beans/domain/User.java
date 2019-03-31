package beans.domain;

public class User {
  private String name="bob";
  static int c;
  private int v=c++;
  public String getName() {
      return name;
  }
  public void setName(String name) {
      this.name = name;
  }
  public int getV() {
      return v;
  }
  public void setV(int v) {
      this.v = v;
  }
}

