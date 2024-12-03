package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileFunctionsTest {

    private static final String RESOURCE_FOLDER_PATH = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "MyTestResources";
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
    public void testClassDefinition() {
        try {
            // Attempt to create an instance of the class
            FileFunctions fileFunctions = new FileFunctions();

            // Assert that the instance is not null
            assertNotNull(fileFunctions);
        } catch (Exception e) {
            // Handle any exceptions during class instantiation
            e.printStackTrace();
        }
    }

    @Test
    public void testFindFunction_FileExists() throws IOException {
        // Arrange: Create a test file
        String basePath = RESOURCE_FOLDER_PATH;
        String fileName = "testFile.txt";
        createTestFile(basePath, fileName);

        // Act: Call the FindFunction
        FileFunctions.findFunction(basePath, fileName);
    }
    @Test
public void testFindFunction_ExceptionOccurs() {
    // Arrange: Set an invalid base path
    String invalidBasePath = "invalid/path";
    String fileName = "anyFileName.txt";

    // Act: Call FindFunction with an invalid base path
    FileFunctions.findFunction(invalidBasePath, fileName);
}


    // Helper method to create a test file
    private void createTestFile(String basePath, String fileName) throws IOException {
        Files.write(Path.of(basePath, fileName), "Test content".getBytes());
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
                Files.walk(folder.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
