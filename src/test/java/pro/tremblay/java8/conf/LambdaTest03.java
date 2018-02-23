package pro.tremblay.java8.conf;

public class LambdaTest03 {

  public static void main(String[] args) {
    print("Henri");
  }

  private static void print(String name) {
    System.out.println("Hello " + name);
  }

  private interface NameSupplier {
    String get();
  }
}
