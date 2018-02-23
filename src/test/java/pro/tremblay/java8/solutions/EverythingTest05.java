package pro.tremblay.java8.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

public class EverythingTest05 {

  // Talk about runnable
  // FunctionalInterface
  // Consumer
  // Replace Consumer by MyInterface
  // Add a method to MyInterface
  // Add static method
  // Add default method

  @Test
  public void test() throws InterruptedException {
    MyRunnable r = () -> System.out.printf("Hello Henri");
    r.run();
    System.out.println(r.getClass());
    System.out.println("Arrays.toString(r.getClass().getInterfaces()) = " + Arrays.toString(r.getClass().getInterfaces()));
  }

  public interface MyRunnable {
    void run();
  }

  @Test
  public void test2() {
    Consumer<String> consumer = s -> System.out.printf(s);
    consumer.accept("Hi");
    MyInterface i = s -> System.out.printf(s);
    i.print("Hi");
    i.awesomePrint("Hi");
  }

  @Test
  public void testStatic() {
    MyInterface.hello("Henri");
  }

  @FunctionalInterface
  public interface MyInterface {
    static void hello(String msg) {
      System.out.printf("Hello %s", msg);
    }

    // void foo();

    void print(String msg);

    default void awesomePrint(String msg) {
      print("*** " + msg + " ***");
    }
  }
}
