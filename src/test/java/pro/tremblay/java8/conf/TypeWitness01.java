package pro.tremblay.java8.conf;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TypeWitness01 {

  private static <T> T anyObject() {
    return null;
  }

  @Test
  public void test() {
//    foo(anyObject());
  }

  private void foo(List<String> list) {

  }

  private void foo(Set<String> set) {
    fail("Wrong method called");
  }
}
