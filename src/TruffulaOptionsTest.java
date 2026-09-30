import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

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
  void testUnknownFlag(@TempDir File tempDir) throws FileNotFoundException {
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-amongus", directoryPath};

    assertThrows(IllegalArgumentException.class, ()->new TruffulaOptions(args));
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
}
