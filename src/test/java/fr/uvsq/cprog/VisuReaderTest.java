package fr.uvsq.cprog;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisuReaderTest {

    private static final String RESOURCE_FOLDER_PATH = "src" + File.separator + "test" + File.separator + "resources";
    private static File resourceFolder;
    private static File testFolder;

    @BeforeAll
    public static void setUp() {
        // Create the resource folder and test folder before all tests
        resourceFolder = new File(RESOURCE_FOLDER_PATH);
        createFolder(resourceFolder);

        testFolder = new File(RESOURCE_FOLDER_PATH + File.separator + "visu");
        createFolder(testFolder);
    }
    @AfterAll
    public static void tearDown() {
        // Delete the test folder after all tests
        deleteFolder(testFolder);
    }
    private static void createTestFile(String filePath, List<String> content) throws IOException {
        // Use the provided filePath parameter
        Files.write(Path.of(filePath), content);
    }
        @Test
    public void testClassDefinition() {
        try {
            // Attempt to create an instance of the class
            VisuReader visuReader = new VisuReader();

            // Assert that the instance is not null
            assertNotNull(visuReader);
        } catch (Exception e) {
            // Handle any exceptions during class instantiation
            e.printStackTrace();
        }
    }

    @Test
    public void testVisualizeFileContent_TextFile() throws IOException {
        // Arrange
        String testFilePath = testFolder + File.separator + "testFile.txt";
        List<String> testContent = List.of("This is a test file content.");

        createTestFile(testFilePath, testContent);

        // Act
        String result = VisuReader.visualizeFileContent(testFilePath, "txt");

        // Assert
        assertEquals(String.join("\n", testContent), result.trim());
    }

    @Test
    public void testVisualizeFileContent_CSVFile() throws IOException {
        // Arrange
        String testFilePath = testFolder + File.separator + "testFile.csv";
        List<String> testContent = List.of("Name, Age", "John, 25", "Jane, 30");

        createTestFile(testFilePath, testContent);

        // Act
        String result = VisuReader.visualizeFileContent(testFilePath, "csv");

        // Assert
        assertEquals(String.join("\n", testContent), result.trim());
    }

    @Test
    public void testVisualizeFileContent_NonexistentFile() throws IOException {
        // Arrange
        String nonExistentFilePath = testFolder + File.separator + "nonExistentFile.txt";

        // Act
        String result = VisuReader.visualizeFileContent(nonExistentFilePath, "txt");

        // Assert
        assertTrue(result.contains("File doesn't exist"));
    }

    @Test
    public void testVisualizeFileContent_OtherFileType() throws IOException {
        // Arrange
        String testFilePath = testFolder + File.separator + "testFile.pdf";

        createTestFile(testFilePath, List.of("Dummy PDF content"));

        // Act
        String result = VisuReader.visualizeFileContent(testFilePath, "pdf");

        // Assert
        assertTrue(result.contains("The size of the file"));
    }

    // Helper method to create a folder
    private static void createFolder(File folder) {
        try {
            Files.createDirectories(folder.toPath());
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
