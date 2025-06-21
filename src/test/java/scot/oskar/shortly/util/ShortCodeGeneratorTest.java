package scot.oskar.shortly.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShortCodeGeneratorTest {

  private final ShortCodeGenerator shortCodeGenerator = new ShortCodeGenerator();

  @Test
  public void testCodeGeneration() {
    final String shortCode = shortCodeGenerator.generateShortCode();

    System.out.println(shortCode);
    assertEquals(shortCode.length(), 12);
  }

  @Test
  public void testWithNegativeValue() {
    assertThrows(IllegalArgumentException.class, () -> shortCodeGenerator.generateShortCode(-2));
  }

  @Test
  public void testWithBelowMin() {
    assertThrows(IllegalArgumentException.class, () -> shortCodeGenerator.generateShortCode(2));
  }

}
