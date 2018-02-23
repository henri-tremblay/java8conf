package pro.tremblay.java8.solutions;

import pro.tremblay.java8.Event;
import pro.tremblay.java8.EventDao;
import pro.tremblay.java8.User;
import pro.tremblay.java8.UserDao;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ParallelTest10 {

  // Retrieve user in a stream
  // Retrieve users in parallel
  // Talk about the common pool and show a print out
  // Talk about performance
  // Add a user to an event
  // Use a basic completable future and submit it to the common pool
  // Add a user to event in a completable future way

  List<String> list = Arrays.asList("Henri", "Matthieu", "Anthony", "Chris");

  UserDao userDao = new UserDao();
  EventDao eventDao = new EventDao();

  @Test
  public void getUsers() {
    List<User> users = list.stream().map(userDao::find).collect(Collectors.toList());
    assertThat(users).hasSize(list.size());
  }

  @Test
  public void getUsersParallel() {
    List<User> users = list.parallelStream().map(userDao::find).collect(Collectors.toList());
    assertThat(users).hasSize(list.size());
  }

  @Test
  public void getUsersPrintParallel() {
    List<User> users = list.parallelStream()
      .map(userDao::find)
      .peek(u -> {
        System.out.println(Thread.currentThread().getName());
      })
      .collect(Collectors.toList());

    assertThat(users).hasSize(list.size());
  }

  @Test
  public void addUserToEvent() {
    User user = userDao.find("Henri");
    Event event = eventDao.find(1);
    event.addUser(user);

    assertThat(event.getUsers()).contains(user);
  }

  @Test
  public void uselessFuture() throws ExecutionException, InterruptedException {
    CompletableFuture<String> userFuture = new CompletableFuture<>();

    ForkJoinPool.commonPool().execute(() -> userFuture.complete("Henri"));

    assertEquals("Hello Henri", "Hello " + userFuture.get());
  }

  @Test
  public void addUserToEventReactive() throws ExecutionException, InterruptedException {
    CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> userDao.find("henri"));
    CompletableFuture<Event> eventFuture = CompletableFuture.supplyAsync(() -> eventDao.find(1));

    CompletableFuture<Event> conclusion = userFuture.thenCombine(eventFuture, (User user, Event event) -> {
      event.addUser(user);
      return event;
    });

    assertThat(conclusion.get().getUsers()).contains(userFuture.get());
  }
}
