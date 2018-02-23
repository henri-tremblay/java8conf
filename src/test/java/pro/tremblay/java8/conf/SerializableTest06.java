package pro.tremblay.java8.conf;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerializableTest06 {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  Path path;

  @Before
  public void setup() throws IOException {
    path = folder.newFile().toPath();
  }

  @Test
  public void test() throws IOException, ClassNotFoundException {
    try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
      out.writeObject((Runnable) () -> System.out.println("Test"));
    }

    try(ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
      Runnable r = (Runnable) in.readObject();
      r.run();
    }
  }

  public static class A {
    String str = "AAA";
  }
}
