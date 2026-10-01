import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorPrinterTest {

  @Test
  void testPrintlnWithRedColorAndReset() {
    // Arrange: Capture the printed output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    ColorPrinter printer = new ColorPrinter(printStream);
    printer.setCurrentColor(ConsoleColor.RED);

    // Act: Print the message
    String message = "I speak for the trees";
    printer.println(message);


    String expectedOutput = ConsoleColor.RED + "I speak for the trees" + System.lineSeparator() + ConsoleColor.RESET;

    // Assert: Verify the printed output
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void testPrintWithRedColorAndReset() {
    // Arrange: Capture the printed output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    ColorPrinter printer = new ColorPrinter(printStream);
    printer.setCurrentColor(ConsoleColor.RED);

    // Act: Print the message
    String message = "I speak for the trees";
    printer.print(message);

    String expectedOutput = ConsoleColor.RED + "I speak for the trees" + ConsoleColor.RESET;

    // Assert: Verify the printed output
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test 
  void testPrintWithDefaultColorAndReset() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    ColorPrinter printer = new ColorPrinter(printStream);
    printer.print("I speak for the trees");

    String expected = ConsoleColor.WHITE + "I speak for the trees" + ConsoleColor.RESET;

    assertEquals(expected,outputStream.toString());
  }

  @Test
  void testPrintWithNoReset() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    ColorPrinter printer = new ColorPrinter(printStream);
    printer.print("I speak for the trees", false);

    String expected = ConsoleColor.WHITE + "I speak for the trees";

    assertEquals(expected, outputStream.toString());
  }

  @Test
  void testPrintAllColors() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    ColorPrinter printer = new ColorPrinter(printStream);
    printer.setCurrentColor(ConsoleColor.BLACK);
    printer.print("B", false);
    printer.setCurrentColor(ConsoleColor.RED);
    printer.print("R", false);
    printer.setCurrentColor(ConsoleColor.GREEN);
    printer.print("G", false);
    printer.setCurrentColor(ConsoleColor.YELLOW);
    printer.print("Y", false);
    printer.setCurrentColor(ConsoleColor.BLUE);
    printer.print("B", false);
    printer.setCurrentColor(ConsoleColor.PURPLE);
    printer.print("P", false);
    printer.setCurrentColor(ConsoleColor.CYAN);
    printer.print("C", false);
    printer.setCurrentColor(ConsoleColor.WHITE);
    printer.print("W", false);
    printer.setCurrentColor(ConsoleColor.RESET);
    printer.print("RESET", false);

    String expected = ConsoleColor.BLACK+"B"
        + ConsoleColor.RED + "R" 
        + ConsoleColor.GREEN + "G" 
        + ConsoleColor.YELLOW + "Y"
        + ConsoleColor.BLUE + "B" 
        + ConsoleColor.PURPLE + "P" 
        + ConsoleColor.CYAN+ "C"
        + ConsoleColor.WHITE + "W"
        + ConsoleColor.RESET + "RESET";

    assertEquals(expected, outputStream.toString());
  }

  // I can't write more tests without knowing how this is supposed to work.

}
