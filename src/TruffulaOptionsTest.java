import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TruffulaOptionsTest {

  @Test
  void testValidDirectoryIsSet(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-nc", "-h", directoryPath};

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert: Check that the root directory is set correctly
    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertTrue(options.isShowHidden());
    assertFalse(options.isUseColor());
  }

  @Test 
  void testUnknownFlagError(@TempDir File tempDir) throws FileNotFoundException {
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-amongus", directoryPath};

    String expected = "Unknown flags: [-amongus]";

    Exception e = assertThrows(IllegalArgumentException.class, ()->new TruffulaOptions(args));
    assertEquals(expected, e.getMessage());
  }

  @Test
  void testTooManyFlagsError(@TempDir File tempDir) throws FileNotFoundException {
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-h", "-nc", "-amongus", directoryPath};

    String expected = "Unknown flags: [-amongus]";

    Exception e = assertThrows(IllegalArgumentException.class, ()->new TruffulaOptions(args));
    assertEquals(expected, e.getMessage());
  }

  @Test
  void testHiddenFlag(@TempDir File tempDir) throws FileNotFoundException {
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-h", directoryPath};

    TruffulaOptions options = new TruffulaOptions(args);

    assertTrue(options.isShowHidden());
  }

  @Test
  void testNoColorFlag(@TempDir File tempDir) throws FileNotFoundException {
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = { "-nc", directoryPath };

    TruffulaOptions options = new TruffulaOptions(args);

    assertFalse(options.isUseColor());
  }

  @Test
  void testNoFlags(@TempDir File tempDir) throws FileNotFoundException {
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = { directoryPath };

    TruffulaOptions options = new TruffulaOptions(args);

    assertFalse(options.isShowHidden());
    assertTrue(options.isUseColor());
  }

  @Test 
  void testNoArgumentsError() {
    String[] args = { };

    Exception e = assertThrows(IllegalArgumentException.class, ()->new TruffulaOptions(args));
    assertEquals("No path provided", e.getMessage());
  }

  @Test
  void testFileDoesNotExistError() {
    String invalidPath = "invalidpathname";
    String[] args = { invalidPath };

    String expected = "Could not find file from path: " + invalidPath + System.lineSeparator()
        + "Ensure path is final argument";

    Exception e =assertThrows(FileNotFoundException.class, () -> new TruffulaOptions(args));
    assertEquals(expected, e.getMessage());
  }

  @Test
  void testPathNotLastArgumentError(@TempDir File tempDir) {
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = { directoryPath, "-h" };

    String expected = "Could not find file from path: " + "-h" + System.lineSeparator() + "Ensure path is final argument";

    Exception e = assertThrows(FileNotFoundException.class, () -> new TruffulaOptions(args));
    assertEquals(expected, e.getMessage());
  }

  @Test
  void testFileIsNotADirectoryError(@TempDir File tempDir) throws IOException {
    File invalidFile = new File(tempDir,"invalid.txt");
    invalidFile.createNewFile();
    String[] args = { invalidFile.getAbsolutePath() };

    Exception e = assertThrows(FileNotFoundException.class, () -> new TruffulaOptions(args));
    assertEquals("File is not a directory", e.getMessage());
  }
}
