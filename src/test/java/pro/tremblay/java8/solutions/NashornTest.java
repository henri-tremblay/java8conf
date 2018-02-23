package pro.tremblay.java8.solutions;

import org.junit.Test;

import java.io.InputStreamReader;
import java.util.function.DoubleBinaryOperator;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static org.assertj.core.api.Assertions.*;


public class NashornTest {

  ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

  @Test
  public void testEval() throws ScriptException {
    int val = (int) engine.eval("2+4");
    System.out.println(val);
  }

  @Test
  public void testEvalScript() throws ScriptException {
    engine.eval(new InputStreamReader(getClass().getResourceAsStream("/script.js")));

    Invocable invocable = (Invocable) engine;

    DoubleBinaryOperator sum = (a, b) -> {
      try {
        return (double) invocable.invokeFunction("sum", a, b);
      }
      catch(ScriptException | NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    };

    double result = sum.applyAsDouble(2, 3);
    assertThat(result).isEqualTo(5);
  }

//  var list = new java.util.ArrayList();
//  list.add("Henri");
//  list.stream().forEach(function(s) { print(s); });
}
