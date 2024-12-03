package fr.uvsq.cprog;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectoryFunctionTest {

    final static String testPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator
            + "DirectoryTest";
    static File testfolder = new File(testPath);
    private static final String RESOURCE_FOLDER_PATH = "src" + File.separator + "test" + File.separator + "resources";
    private static File resourceFolder;

    @BeforeAll
    public static void setUp() {
        // Create the resource folder before all tests
        resourceFolder = new File(RESOURCE_FOLDER_PATH);
        createFolder(testfolder);
        createFolder(resourceFolder);
    }


      @AfterAll
      public static void tearDown() {
      // Delete the test folder after all tests
      deleteFolder(testfolder);
      }
      @Test
    public void testClassDefinition() {
        try {
            // Attempt to create an instance of the class
            DirectoryFunction directoryFunction = new DirectoryFunction();

            // Assert that the instance is not null
            assertNotNull(directoryFunction);
        } catch (Exception e) {
            // Handle any exceptions during class instantiation
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDirectory_Success() {
        // Arrange
        String directoryName = "testDirectory";

        // Act
        String result = DirectoryFunction.createdirectory(testPath, directoryName);

        // Assert
        assertTrue(new File(testPath, directoryName).exists());
        assertEquals(ConsoleColors.GREEN_BACKGROUND + "Directory has been created successfully" + ConsoleColors.RESET,
                result);
    }

    @Test
    public void testCreateDirectory_DirectoryAlreadyExists() {
        // Arrange
        String directoryName = "existingDirectory";
        new File(testPath, directoryName).mkdir(); // create an existing directory

        // Act
        String result = DirectoryFunction.createdirectory(testPath, directoryName);

        // Assert
        assertTrue(new File(testPath, directoryName).exists());
        assertEquals(ConsoleColors.RED_BACKGROUND + "Directory cannot be created" + ConsoleColors.RESET, result);
    }

    @Test
    public void testCreateDirectory_InvalidPath() {
        // Arrange
        String invalidPath = "invalid" + File.separator + "path";
        String directoryName = "testDirectory";

        // Act
        String result = DirectoryFunction.createdirectory(invalidPath, directoryName);

        // Assert
        assertEquals(ConsoleColors.RED_BACKGROUND + "Directory cannot be created" + ConsoleColors.RESET, result);
    }

    // Helper method to create a folder
    private static void createFolder(File folder) {
        try {
            Files.createDirectories(Path.of(folder.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to delete a folder and its contents
    private static void deleteFolder(File folder) {
        if (folder.exists()) {
            try {
                Files.walk(folder.toPath())
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
