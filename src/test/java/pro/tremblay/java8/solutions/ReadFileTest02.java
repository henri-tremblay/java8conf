package pro.tremblay.java8.solutions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;

/**
 * @author Henri Tremblay
 */
public class ReadFileTest02 {

  // Old version
  // Use Paths and StandardCharsets
  // Show addSuppressed old way
  // Use try with resource
  // Files.readAllLines

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  public static List<String> readFile1(String folder, String filename) {
    String finalName = folder.replace('/', File.separatorChar) + File.separatorChar + filename;
    BufferedReader in;
    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(finalName), Charset.forName("UTF-8")));
    }
    catch(FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    try {
      List<String> list = new ArrayList<>();
      String s;
      while((s = in.readLine()) != null) {
        list.add(s);
      }
      return list;
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }
    finally {
      try {
        in.close();
      }
      catch(IOException e) {
        // ignore silently
      }
    }
  }

  public static List<String> readFile2(String folder, String filename) {
    File file = Paths.get(folder, filename).toFile();
    BufferedReader in;
    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
    }
    catch(FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    try {
      List<String> list = new ArrayList<>();
      String s;
      while((s = in.readLine()) != null) {
        list.add(s);
      }
      return list;
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }
    finally {
      try {
        in.close();
      }
      catch(IOException e) {
        // ignore silently
      }
    }
  }

  public static List<String> readFile3(String folder, String filename) {
    File file = Paths.get(folder, filename).toFile();
    BufferedReader in;
    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
    }
    catch(FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    List<String> list = new ArrayList<>();

    try {
      String s;
      while((s = in.readLine()) != null) {
        list.add(s);
      }
    }
    catch(IOException e) {
      try {
        in.close();
      }
      catch(IOException e1) {
        e.addSuppressed(e1);
      }
      throw new RuntimeException(e);
    }

    try {
      in.close();
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }

    return list;
  }

  public static List<String> readFile4(String folder, String filename) {
    File file = Paths.get(folder, filename).toFile();
    try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
      List<String> list = new ArrayList<>();
      String s;
      while((s = in.readLine()) != null) {
        list.add(s);
      }
      return list;
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<String> readFile5(String folder, String filename) {
    Path path = Paths.get(folder, filename);
    try(BufferedReader in = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
      List<String> list = new ArrayList<>();
      String s;
      while((s = in.readLine()) != null) {
        list.add(s);
      }
      return list;
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<String> readFile(String folder, String filename) {
    Path path = Paths.get(folder, filename);
    try {
      return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void test() {
    List<String> actual = readFile("src/test/data", "lines.txt");
    assertThat(actual).contains("alpha", "bravo", "charlie");
  }

  @Test
  public void testSuppressed() throws IOException {
    try(InputStream in = createMock(InputStream.class)) {
      expect(in.read()).andThrow(new IOException("Reading"));
      in.close();
      expectLastCall().andThrow(new IOException("Closing"));
      replay(in);

      expectedException.expect(IOException.class);
      expectedException.expectMessage("Reading");

      in.read();
    }
  }
}
