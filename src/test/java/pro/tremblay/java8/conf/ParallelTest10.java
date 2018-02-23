package pro.tremblay.java8.conf;

import pro.tremblay.java8.Event;
import pro.tremblay.java8.EventDao;
import pro.tremblay.java8.User;
import pro.tremblay.java8.UserDao;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

public class ParallelTest10 {

  List<String> list = Arrays.asList("Henri", "Matthieu", "Anthony", "Chris");

  UserDao userDao = new UserDao();

  @Test
  public void getUsers() {
    List<User> users = Collections.emptyList();
    assertThat(users).hasSize(list.size());
  }
  
  EventDao eventDao = new EventDao();

  @Test
  public void addUserToEvent() throws ExecutionException, InterruptedException {
    User user = userDao.find("Henri");
    Event event = eventDao.find(1);
    event.addUser(user);

    assertThat(event.getUsers()).contains(user);
  }

  // See: http://www.nurkiewicz.com/2013/05/java-8-definitive-guide-to.html
}
