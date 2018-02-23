package pro.tremblay.java8.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henri Tremblay
 */
public class Java7 {

  List<String> list = new ArrayList<>();
  int i = 0b00100100;
  int j = 1_000_000;

  public String switchString(String s) {
    switch(s) {
      case "hello":
        return "world";
      case "bonjour":
        return "le monde";
    }
    return null;
  }

}
