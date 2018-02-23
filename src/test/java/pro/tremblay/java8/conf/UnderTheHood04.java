package pro.tremblay.java8.conf;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;

public class UnderTheHood04 {

  public static void main(String[] args) throws Exception {
    Arrays.asList(UnderTheHood04.class.getDeclaredMethods())
      .forEach(new Consumer<Method>() {
        @Override
        public void accept(Method method) {
          System.out.println(method.getName());
        }
      });
  }
}
