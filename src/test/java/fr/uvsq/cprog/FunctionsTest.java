package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



public class FunctionsTest {

    private static final String RESOURCE_FOLDER_PATH = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "MyTestResources";
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
    public void testSplitString() {
        // Arrange
        String testString = "Hello World Java";
        String cut = " ";
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Hello", "World", "Java"));
        
        // Act
        ArrayList<String> result = Functions.splitString(testString, cut);

        // Assert
        assertEquals(expected, result);
    }
    @Test
    public void testTrimArrayList() {
        // Arrange
        ArrayList<String> input = new ArrayList<>(Arrays.asList("  Hello ", "World ", " Java  "));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Hello", "World", "Java"));
        
        // Act
        ArrayList<String> result = Functions.trimArrayList(input);

        // Assert
        assertEquals(expected, result);
    }







    @Test
    public void testShowDirectoryContent() {
        // Not possible to test console output directly
        // Manually validate if it shows the directory content
    }

    @Test
    public void testIsInteger() {
        // Arrange & Act
        boolean result1 = Functions.isInteger("123");
        boolean result2 = Functions.isInteger("-123");
        boolean result3 = Functions.isInteger("abc");
        
        // Assert
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
    }



    
    

    // Other test methods for other Functions class methods can go here

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
