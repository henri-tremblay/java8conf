package pro.tremblay.java8.conf;

import org.junit.Test;

import java.util.function.Consumer;

public class EverythingTest05 {

  @Test
  public void test() {
    Runnable r = () -> System.out.printf("Hello Henri");
    r.run();
  }

  @Test
  public void testEverything() {
    Consumer<String> consumer = s -> System.out.printf(s);
    consumer.accept("Hi");
  }

  public interface MyInterface {
    void print(String val);
  }
}
