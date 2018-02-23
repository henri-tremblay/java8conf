package pro.tremblay.java8.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class FunctionalTest09 {

  // Sum of All firstname containing a i in functional programming
  // List of lengths with the collector
  // Print ordered with comparator
  // Refactor the sorted stream in a method
  // Talk about the ordering
  // Refactoring in method reference

  List<String> list = Arrays.asList("Henri", "Matthieu", "Anthony", "Chris");

  private static void print(String s) {
    System.out.println("Foreach " + s);
  }

  private static boolean printAndFilter(String s) {
    System.out.println("Filtering " + s);
    return s.startsWith("A");
  }

  private static String printAndReturn(String s) {
    System.out.println("First mapping: " + s);
    return s;
  }

  @Test
  public void sumOfAllLengthOfFirstnamesContainingAnI() {
    int sum = list.stream()
      .filter(s -> s.contains("i"))
      .mapToInt(String::length)
      .sum();
    assertEquals(15, sum);
  }

  @Test
  public void getListOfLengths() {
    List<Integer> actual = list.stream()
      .map(String::length)
      .collect(Collectors.toList());

    assertThat(actual).containsSequence(5, 5, 7, 5);
  }

  @Test
  public void printOrderedByLength() {
    list.stream()
      .sorted(Comparator.comparingInt(String::length))
      .map(s -> String.format("%s: %d", s, s.length()))
      .forEachOrdered(System.out::println);
  }

  @Test
  public void printOrderedByLength2() {
    getSortedByLength()
      .map(s -> String.format("%s: %d", s, s.length()))
      .forEachOrdered(System.out::println);
  }

  private Stream<String> getSortedByLength() {
    return list.stream()
      .sorted(Comparator.comparingInt(String::length));
  }

  @Test
  public void refactoring() {
    list.stream()
      .map(FunctionalTest09::printAndReturn)
      .filter(FunctionalTest09::printAndFilter)
      .forEachOrdered(FunctionalTest09::print);
  }
}


