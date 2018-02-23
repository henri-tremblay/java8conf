package pro.tremblay.java8.conf;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class FunctionalTest09 {

  List<String> list = Arrays.asList("Henri", "Matthieu", "Anthony", "Chris");

  @Test
  public void sumOfAllLengthOfFirstnamesContainingAI() {
    int sum = 0;
    assertEquals(18, sum);
  }

  @Test
  public void getListOfOrderedDistinctLengths() {
    List<Integer> actual = null;
    assertThat(actual).containsSequence(5, 7, 8);
  }

  @Test
  public void printOrderedByLength() {

  }

}
