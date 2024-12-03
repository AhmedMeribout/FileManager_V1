package fr.uvsq.cprog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class CopyPasteTest {

    private static final String TEST_RESOURCES_PATH = "src" + File.separator + "test" + File.separator + "resources";
    private static final String SOURCE_FOLDER_NAME = "SourceFolder";
    private static final String DESTINATION_FOLDER_NAME = "DestinationFolder";

    private File sourceFolder;
    private File destinationFolder;

    @BeforeEach
    public void setUp() {
        sourceFolder = new File(TEST_RESOURCES_PATH + File.separator + SOURCE_FOLDER_NAME);
        destinationFolder = new File(TEST_RESOURCES_PATH + File.separator + DESTINATION_FOLDER_NAME);
        createFolder(sourceFolder);
        createFolder(destinationFolder);
    }

    @AfterEach
    public void tearDown() {
        deleteFolder(sourceFolder);
        deleteFolder(destinationFolder);
    }
    @Test
    public void testCopyAndPasteFoldersAndFiles() {
        // Create folders and files in the source
        createFolderAndFiles(sourceFolder, "File1.txt", "File2.txt");
        File subFolder = new File(sourceFolder, "SubFolder");
        createFolderAndFiles(subFolder, "SubFile1.txt", "SubFile2.txt");

        // Copy and paste the folders and files
        String result = CopyPaste.pasteElement(destinationFolder.getAbsolutePath(), sourceFolder, false);

        // Check if the elements were copied successfully
        assertTrue(result.contains("Element pasted"));
        assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME).exists());
        assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "File1.txt").exists());
        assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "File2.txt").exists());
        assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "SubFolder").exists());
        assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "SubFolder" + File.separator + "SubFile1.txt").exists());
        assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "SubFolder" + File.separator + "SubFile2.txt").exists());
    }
    @Test
public void testCutAndPasteFoldersAndFiles() {
    // Create folders and files in the source
    createFolderAndFiles(sourceFolder, "File1.txt", "File2.txt");
    File subFolder = new File(sourceFolder, "SubFolder");
    createFolderAndFiles(subFolder, "SubFile1.txt", "SubFile2.txt");

    // Copy and paste the folders and files with cut=true
    String result = CopyPaste.pasteElement(destinationFolder.getAbsolutePath(), sourceFolder, true);

    // Check if the elements were cut and pasted successfully
    assertTrue(result.contains("Element pasted"));
    assertFalse(new File(sourceFolder.getAbsolutePath()).exists()); // Source should be deleted
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME).exists());
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "File1.txt").exists());
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "File2.txt").exists());
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "SubFolder").exists());
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "SubFolder" + File.separator + "SubFile1.txt").exists());
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + SOURCE_FOLDER_NAME + File.separator + "SubFolder" + File.separator + "SubFile2.txt").exists());
}
@Test
public void testDuplicateNamesHandling() {
    // Create a file in the source folder
    File fileToCopy = new File(sourceFolder, "FileToCopy.txt");
    createFile(fileToCopy);

    // Copy the file to the destination multiple times
    for (int i = 0; i < 5; i++) {
        String result = CopyPaste.pasteElement(destinationFolder.getAbsolutePath(), fileToCopy, false);
        assertTrue(result.contains("Element pasted"));
    }

    // Check if the file in the destination has a unique name
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + "FileToCopy.txt").exists());
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + "FileToCopy-copy.txt").exists());
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + "FileToCopy-copy(1).txt").exists());
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + "FileToCopy-copy(2).txt").exists());
    assertTrue(new File(destinationFolder.getAbsolutePath() + File.separator + "FileToCopy-copy(3).txt").exists());
    assertFalse(new File(destinationFolder.getAbsolutePath() + File.separator + "FileToCopy-copy(4).txt").exists());
}
@Test
    public void testElementForCopy() {
        // Test with a valid file path
        String validFilePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestFile.txt";
        File file = new File(validFilePath);
        createFile(file);

        assertNotNull(CopyPaste.elementForCopy(validFilePath));
        assertTrue(CopyPaste.elementForCopy(validFilePath).isFile());
        assertEquals(validFilePath, CopyPaste.elementForCopy(validFilePath).getPath());

        // Test with a valid directory path
        String validDirPath = "src" + File.separator + "test" + File.separator + "resources";
        File directory = CopyPaste.elementForCopy(validDirPath);
        assertNotNull(directory);
        assertTrue(directory.isDirectory());
        assertEquals(new File(validDirPath).getAbsolutePath(), directory.getAbsolutePath());

        // Test with an invalid path (non-existing file/directory)
        String invalidPath = "nonexistent" + File.separator + "path";
        File invalidFile = CopyPaste.elementForCopy(invalidPath);
        assertNotNull(invalidFile);
        assertFalse(invalidFile.exists());
    }



    private void createFolderAndFiles(File folder, String... fileNames) {
        createFolder(folder);
        for (String fileName : fileNames) {
            createFile(new File(folder, fileName));
        }
    }

    private static void createFolder(File folder) {
        folder.mkdirs();
    }

    private static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFolder(File folder) {
        if (folder.exists()) {
            try {
                Files.walk(Paths.get(folder.getAbsolutePath()))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

