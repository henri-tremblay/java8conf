package pro.tremblay.java8.solutions;

import java.lang.reflect.Method;
import java.util.Arrays;

public class UnderTheHood04 {

  // Show the target dir
  // Move to lambda
  // Show the target dir
  // Run the program

  public static void main(String[] args) throws Exception {
    Arrays.asList(UnderTheHood04.class.getDeclaredMethods())
      .forEach(method -> System.out.println(method.getName()));

    System.out.println("#####");

    Method method = UnderTheHood04.class.getDeclaredMethod("lambda$main$0", Method.class);
    method.setAccessible(true);
    method.invoke(null, method);

    // if(user.hasAccess()) {
    //    foo(user -> things);
    // }
  }
}
