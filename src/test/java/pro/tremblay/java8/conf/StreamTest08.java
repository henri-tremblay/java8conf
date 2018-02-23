package pro.tremblay.java8.conf;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StreamTest08 {

  String[] people = new String[] { "Henri", "Matthieu", "Anthony", "Chris" };

  public List<String> getPeople() {
    return Arrays.asList(people);
  }

  @Test
  public void test() throws IOException {
    List<String> list = Files.readAllLines(Paths.get("pom.xml"));
    list.forEach(System.out::println);
  }

  @Test
  public void people() {
    List<String> result = new ArrayList<>();
    getPeople().forEach(name -> {
      if(name.contains("i")) {
        result.add(name);
      }
    });

    assertThat(result).hasSize(3);
  }
}
