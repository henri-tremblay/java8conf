package pro.tremblay.java8.conf;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class MethodReferenceTest07 {

  @Test
  public void test() {
    Passenger passenger = new Passenger();

    List<Train> trains = Collections.singletonList(new Train());
    trains.forEach(t -> t.repair());
  }

  public static class Passenger {
    public void inboard(Train train) {
      System.out.println("Inboard " + train);
    }
  }

  public static class Train {

    private static int idGenerator = 0;
    private int id = idGenerator++;

    public static Train create(Supplier<Train> supplier) {
      return supplier.get();
    }

    public static void paintBlue(Train train) {
      System.out.println("Painter blue " + train);
    }

    public void repair() {
      System.out.println("Repaired " + this);
    }

    public boolean isBlue() {
      return true;
    }

    @Override
    public String toString() {
      return "Train " + id;
    }
  }

}
