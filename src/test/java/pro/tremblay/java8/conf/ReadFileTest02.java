package pro.tremblay.java8.conf;

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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;

/**
 * @author Henri Tremblay
 */
public class ReadFileTest02 {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  public static List<String> readFile(String folder, String filename) {
    File file = new File(folder, filename);
    BufferedReader in;
    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
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

//      expectedException.expect(IOException.class);
//      expectedException.expectMessage("Reading");

      in.read();
    }
  }
}
