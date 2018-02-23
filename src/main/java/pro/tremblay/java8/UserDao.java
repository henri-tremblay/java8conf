package pro.tremblay.java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {

  private Map<String, User> map = new HashMap<>();

  public UserDao() {
    List<String> list = Arrays.asList("Henri", "Matthieu", "Anthony", "Chris");
    list.stream().forEach(s -> map.put(s, User.create(s)));
  }

  public User find(String firstName) {
    return map.get(firstName);
  }
}
