package pro.tremblay.java8.solutions;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaTest03 {

  // NameSupplier anonymous inner class
  // Noargs lambda
  // Supplier<String>
  // decorator for title Function<String, String>
  // Typing
  // Implicit return
  // Multiline
  // Closure
  // Implicit final
  // No-return Consumer

  public static void main(String[] args) {
    BiFunction<String, String, String> decorator = (String title, String name) -> {
      String fullName = title + " " + name;
      return fullName;
    };

    String title = "Mr.";

    print(() -> title + "Henri");

    doSomething(name -> System.out.println("Hello " + name));
  }

  private static void doSomething(Consumer<String> consumer) {
    consumer.accept("Henri");
  }

  private static void print1(String name) {
    System.out.println("Hello " + name);
  }

  private static void print2(NameSupplier nameSupplier) {
    System.out.println("Hello " + nameSupplier.get());
  }

  private static void print3(Supplier<String> nameSupplier) {
    System.out.println("Hello " + nameSupplier.get());
  }

  private static void print4(Supplier<String> nameSupplier, Function<String, String> decorator) {
    System.out.println("Hello " + decorator.apply(nameSupplier.get()));
  }

  private static void print5(Supplier<String> nameSupplier, BiFunction<String, String, String> decorator) {
    System.out.println("Hello " + decorator.apply("Mr.", nameSupplier.get()));
  }

  private static void print(Supplier<String> nameSupplier) {
    System.out.println("Hello " + nameSupplier.get());
  }

  private interface NameSupplier {
    String get();
  }
}
