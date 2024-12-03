package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DeleteFunctionsTest {

  private static final String RESOURCE_FOLDER_PATH = "src" + File.separator + "test" 
      + File.separator + "resources" + File.separator + "MyTestResources";
  private static File resourceFolder;

    @BeforeAll
    public static void setUp() {
        // Create the resource folder before all tests
        resourceFolder = new File(RESOURCE_FOLDER_PATH);
        createFolder(resourceFolder);
    }
    @AfterAll
    public static void tearDown() {
        // Delete the resource folder after all tests
        deleteFolder(resourceFolder);
    }
    
    
    

    @Test
    public void testDeleteSelectedElement_FileExists() throws IOException {
        // Arrange
        String testFilePath = "testFile.txt";
        createTestFile(testFilePath);

        // Act
        String result = DeleteFunction.deleteSelectedElement(testFilePath);

        // Assert
        assertTrue(result.contains("This file has been Deleted"));
        assertFalse(Files.exists(Path.of(testFilePath)));
    }

    @Test
    public void testDeleteSelectedElement_FileDoesNotExist() {
        // Arrange
        String nonExistentFilePath = "nonExistentFile.txt";

        // Act
        String result = DeleteFunction.deleteSelectedElement(nonExistentFilePath);

        // Assert
        assertTrue(result.contains("Element does not exist"));
    }
    
    // Helper method to create a test file
    private void createTestFile(String filePath) throws IOException {
        Files.write(Path.of(filePath), "Test content".getBytes());
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
