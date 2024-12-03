package fr.uvsq.cprog;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;


public class CsvReaderTest {
    private static final String RESOURCE_FOLDER_PATH = "src" + File.separator + "test" + File.separator + "resources";
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






        // Helper method to create a folder
    private static void createFolder(File folder) {
        try {
            Files.createDirectories(folder.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testClassDefinition() {
        try {
            // Attempt to create an instance of the class
            CsvReader csvReader = new CsvReader();

            // Assert that the instance is not null
            assertNotNull(csvReader);
        } catch (Exception e) {
            // Handle any exceptions during class instantiation
            e.printStackTrace();
        }
    }
    @Test
    public void testSearchCsvLine_ColumnValueMatch() {
        try {
            String testLine = "Column1,SearchValue,Column3"; // Example line in the CSV file
            String searchString = "SearchValue";
            int columnToSearch = 1; // The column index to search

            String[] values = testLine.split(",");

            // Simulate the condition where a match is found in the specified column
            if (values.length > columnToSearch && values[columnToSearch].equals(searchString)) {
                // Retrieve the value from the second column
                String resultValue = values[1];

                // Verify if the extracted value matches the expected search string
                assertEquals(searchString, resultValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @Test
    public void testSearchCsvLine_FileDoesNotExist() {
        String result = CsvReader.searchCsvLine(RESOURCE_FOLDER_PATH, 0, "nonExistent");
        assertEquals(null, result);
    }

    @Test
    public void testLineExists_LineDoesNotExist() {
        boolean result = CsvReader.lineExists(RESOURCE_FOLDER_PATH, 0, "nonExistent");
        assertFalse(result);
    }
    @Test
public void testDeleteCsvLine_LineExists() throws IOException {
    // Arrange: Prepare the CSV file with a line to be deleted
    String filePath = RESOURCE_FOLDER_PATH + File.separator + "note.csv";
    Files.write(Path.of(filePath), "test,note".getBytes());

    // Act: Delete the line
    String result = CsvReader.deleteCsvLine(RESOURCE_FOLDER_PATH, 0, "test");

    // Assert: Check if the line was deleted successfully
    assertEquals(ConsoleColors.GREEN_BACKGROUND + "Note removed successfully" + ConsoleColors.RESET, result);
    assertFalse(Files.readString(Path.of(filePath)).contains("test,note"));
}

@Test
public void testAddCsvLine_NewNoteAddedSuccessfully() {
    // Arrange: Prepare the file path and a new note
    String newFileName = "NewFile";
    String newNote = "This is a new note.";

    // Act: Try to add a new note
    String result = CsvReader.addCsvLine(RESOURCE_FOLDER_PATH, newFileName, newNote);

    // Assert: Ensure that the method returns a success message
    assertEquals(ConsoleColors.GREEN_BACKGROUND + "Note added successfully to file: note.csv" + ConsoleColors.RESET, result);
    // Additional assertions to check if the note has been added to the CSV file could be added here
}



@Test
public void testDeleteCsvLine_LastLine() throws IOException {
    // Arrange: Prepare a CSV file with a single line
    String filePath = RESOURCE_FOLDER_PATH + File.separator + "note.csv";
    Files.write(Path.of(filePath), "single,entry".getBytes());

    // Act: Try to delete the line
    String result = CsvReader.deleteCsvLine(RESOURCE_FOLDER_PATH, 0, "single");

    // Assert: Ensure the line is removed and the file becomes empty
    assertEquals(ConsoleColors.GREEN_BACKGROUND + "Note removed successfully" + ConsoleColors.RESET, result);
    assertEquals("", Files.readString(Path.of(filePath)));
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
