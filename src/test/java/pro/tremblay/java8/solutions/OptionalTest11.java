package pro.tremblay.java8.solutions;

import pro.tremblay.java8.User;
import pro.tremblay.java8.UserDao;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalTest11 {

  // Do a lambda filter findAny
  // Get it, show it fails
  // isPresent
  // Map and ifPresent
  // Optinal.ofNullable
  // orElse

  List<String> list = Arrays.asList("Henri", "Matthieu", "Anthony", "Chris");

  @Test
  public void findHenriAndPrintIt() {
    int i = list.indexOf("Henri");
    if(i >= 0) {
      String henri = list.get(i);
      System.out.println(henri);
    }
  }

  @Test
  public void findHenriLambdaStyle() {
    Optional<String> result = list.stream()
      .filter("Henri"::equals)
      .findAny();

    if(result.isPresent()) {
      System.out.println(result.get());
    }
  }

  @Test
  public void findHenriLambdaStyleCute() {
    list.stream()
      .filter("Henri"::equals)
      .findAny()
      .map(s -> "Sir Henri")
      .ifPresent(System.out::println);
  }

  @Test
  public void orElse() {
    UserDao dao = new UserDao();
    User user = dao.find("Henri");
    if(user == null) {
      System.out.println("404");
    }
    else {
      System.out.println(user.getName());
    }
  }

  @Test
  public void orElseCute() {
    UserDao dao = new UserDao();
    String result = Optional.ofNullable(dao.find("Roger"))
      .map(u -> u.getName())
      .orElse("404");
    System.out.println(result);
  }

}
