package pro.tremblay.java8.solutions;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class MethodReferenceTest07 {

  // Repair a train with a lambda
  // Repair a train with a method reference
  // Use a long lambda
  // Refactor it in a method reference
  // Show the 4 flavours (and println)

  @Test
  public void test1() {
    List<Train> trains = Collections.singletonList(new Train());
    trains.forEach(t -> t.repair());
  }

  @Test
  public void test2() {
    List<Train> trains = Collections.singletonList(new Train());
    trains.forEach(Train::repair);
  }

  @Test
  public void test3() {
    List<Train> trains = Collections.singletonList(new Train());
    trains.forEach(t -> {
      if(t.isBlue()) {
        t.repair();
      }
    });
  }

  @Test
  public void test4() {
    List<Train> trains = Collections.singletonList(new Train());
    trains.forEach(this::repairTrainIfBlue);
  }

  private void repairTrainIfBlue(Train t) {
    if(t.isBlue()) {
      t.repair();
    }
  }

  @Test
  public void test5() {
    List<Train> trains = Collections.singletonList(Train.create(Train::new));

    trains.forEach(Train::repair);
    trains.forEach(Train::paintBlue);

    Passenger passenger = new Passenger();
    trains.forEach(passenger::inboard);
    trains.forEach(System.out::println);
  }

  public static class Passenger {
    public void inboard(Train train) {
      System.out.println("Inboard " + train);
    }
  }

  public static class Train {

    public static Train create(Supplier<Train> supplier) {
      return supplier.get();
    }

    public static void paintBlue(Train train) {
      System.out.println("Painter blue " + train);
    }

    public boolean isBlue() {
      return true;
    }

    public void repair() {
      System.out.println("Repaired " + this);
    }
  }
}
