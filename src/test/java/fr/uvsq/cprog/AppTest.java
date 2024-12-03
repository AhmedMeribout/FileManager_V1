package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class AppTest {
    private static App fileExplorer;
    private static String directoryName = "testRoot";

    @BeforeEach
    public void setUp() {
        // Initialize the file explorer with a test directory
        fileExplorer = new App(directoryName);
    }

     @AfterEach
      public void tearDown() {
     fileExplorer.runcommand(new ArrayList<>(Arrays.asList("exit")));
      File testRoot= new File("testRoot");
      deleteFolder(testRoot);
      }



    @Test
    public void testChangeDirectoryCommand() {
        // Test changing directory to an existing directory
        fileExplorer.output(); // Display the initial state
        fileExplorer.runcommand(new ArrayList<>(Arrays.asList("mkdir", "changedirectory")));
        Element testdirectoryelement= findElementwithName("changedirectory");
        assertEquals("testRoot", fileExplorer.path);
        fileExplorer.runcommand(new ArrayList<>(Arrays.asList(testdirectoryelement.number.toString(), ".")));
        assertEquals("testRoot"+File.separator+"changedirectory", fileExplorer.path);
        fileExplorer.runcommand(new ArrayList<>(Arrays.asList("..")));
        assertEquals("testRoot", fileExplorer.path);
    }

    @Test
    public void testConstructor() {
        App app = new App("testRoot");
        assertNotNull(app); // Check if the object is created

        File root = new File("testRoot");
        assertTrue(root.exists()); // Check if root directory exists
        assertTrue(root.isDirectory()); // Check if it's a directory
    }

    @Test
    public void testAppInitialization() {
        App app = new App("testRoot");
        assertNotNull(app);
    }

    // Test fetching data (populate elementList)
    @Test
    public void testFetchData() {
        App app = new App("testRoot");
        app.fetchdata();
        assertFalse(app.elementList.isEmpty());
        // Add more assertions to validate fetched data against expected values
    }

    @Test
    public void testCopyAndPasteFile() {
        // Test copying and pasting a file
        fileExplorer.runcommand(new ArrayList<>(Arrays.asList("mkdir", "sourceDirectory")));
        fileExplorer.runcommand(new ArrayList<>(Arrays.asList("mkdir", "destinationDirectory")));
        // Iterate through elementList to find the file with the matching name
        Element sourceFileElement = findElementwithName("sourceDirectory");
        // Create a test file in the source directory
        fileExplorer.runcommand(new ArrayList<>(
                Arrays.asList(sourceFileElement.number.toString(), ".", "mkdir", "CopyFile", "0", "copy", "..")));
        assertEquals("testRoot", fileExplorer.path);

        // Verify that the file is marked for copy
        assertNotNull(fileExplorer.elementMarkedForCopy);

        Element destinationFileElement = findElementwithName("destinationDirectory");
        // Change to the destination directory
        fileExplorer.runcommand(new ArrayList<>(Arrays.asList(destinationFileElement.number.toString(), ".")));
        assertEquals("testRoot" + File.separator + "destinationDirectory", fileExplorer.path);

        // Verify that the file is not present in the destination directory
        assertFalse(new File("testRoot" + File.separator + "destinationDirectory" + File.separator + "CopyFile")
                .exists());

        // Paste the file into the destination directory
        fileExplorer.runcommand(new ArrayList<>(Collections.singletonList("paste")));
        assertTrue(new File("testRoot" + File.separator + "destinationDirectory" + File.separator + "CopyFile")
                .exists());

    }

    @Test
    public void testCutAndPasteFile() {
    assertEquals("testRoot", fileExplorer.path);
    fileExplorer.runcommand(new ArrayList<>(Arrays.asList("mkdir", "CutDirectory")));
     Element sourceFileElement = findElementwithName("CutDirectory");
        // Create a test file in the source directory
        fileExplorer.runcommand(new ArrayList<>(
                Arrays.asList(sourceFileElement.number.toString(),"cut")));
        assertEquals("testRoot", fileExplorer.path);

    // Verify that the file is marked for cut
    assertNotNull(fileExplorer.elementMarkedForCopy);
    assertTrue(fileExplorer.cutflag);
    fileExplorer.runcommand(new ArrayList<>(Arrays.asList("mkdir", "destinationDirectoryCut")));

    // Change to the destination directory
    Element destinationFileElement = findElementwithName("destinationDirectoryCut");
    assertEquals("testRoot", fileExplorer.path);
    assertNotNull(destinationFileElement);
    fileExplorer.runcommand(new ArrayList<>(Arrays.asList(destinationFileElement.number.toString(), ".")));
    assertEquals("testRoot" + File.separator + "destinationDirectoryCut", fileExplorer.path);

    // Verify that the file is not present in the destination directory
    assertFalse(new File("testRoot" + File.separator + "destinationDirectoryCut" + File.separator + "CutDirectory").exists());

    // Paste the file into the destination directory
    fileExplorer.runcommand(new ArrayList<>(Collections.singletonList("paste")));

    // Verify that the file is now in the destination directory
    assertTrue(new File("testRoot" + File.separator + "destinationDirectoryCut" + File.separator + "CutDirectory").exists());

    // Verify that the file is no longer present in the source directory
    assertFalse(new File("testRoot" + File.separator + "sourceDirectory" + File.separator + "CutDirectory").exists());
}



    private static Element findElementwithName(String elementname) {
        Element sourceFileElement = null;
        for (Element element : fileExplorer.elementList) {
            if (element.name.equals(elementname)) {
                sourceFileElement = element;
                break;
            }
        }
        return sourceFileElement;
    }

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