package pro.tremblay.java8.solutions;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class StreamTest08 {

  // readAllLines
  // lines stream
  // Random ints
  // Filter with foreach
  // Filter with stream
  // Peek

  String[] people = new String[] { "Henri", "Matthieu", "Anthony", "Chris" };

  @Test
  public void test1() throws IOException {
    Files.readAllLines(Paths.get("pom.xml")).forEach(System.out::println);
    Files.lines(Paths.get("pom.xml")).forEach(System.out::println);

    Random rand = new Random();
    IntStream stream = rand.ints();
    stream.limit(10).forEach(System.out::println);
  }

  @Test
  public void test2() {
    List<String> result = new ArrayList<>();
    getPeople().forEach(name -> {
      if(name.contains("i")) {
        result.add(name);
      }
    });
    assertThat(result).hasSize(3);
  }

  @Test
  public void test() {
    long sum = getPeopleStream()
      .filter(name -> {
        System.out.println("Filter " + name);
        return name.contains("i");
      })
      .map(name -> {
        System.out.println("Map " + name);
        return name;
      })
      .count();
    assertThat(sum).isEqualTo(3);
  }

  public List<String> getPeople() {
    return Arrays.asList(people);
  }

  public Stream<String> getPeopleStream() {
    return Arrays.stream(people);
  }

}
