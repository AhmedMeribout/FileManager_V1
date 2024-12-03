package fr.uvsq.cprog;

import java.io.File;

/**
 * The {@code DeleteFunction} class provides methods to delete files and directories.
 * It includes functionality to delete a selected element (file or directory) and its contents,
 * as well as functionality to delete a directory recursively.
 *
 * <p>The method {@code deleteSelectedElement} deletes the specified element at the given path.
 * If the element is a directory, it will be deleted along with its contents.
 * The method also removes the corresponding entry from the CSV file.
 *
 * <p>The method {@code deleteDirectory} is used internally by {@code deleteSelectedElement}.
 * It recursively deletes all contents (files and subdirectories) within a directory.

 * @version 1.0
 * @since [Date]
 */

public class DeleteFunction {

  /**
 * Deletes the specified element at the given path.
 * If the element is a directory, it will be deleted along with its contents.
 * Removes the corresponding entry from the CSV file.
 *
 * @param elementPath The path to the element (file or directory) to be deleted.
 * @return A message indicating the result of the deletion operation:
 *         - Success message with the deleted file/directory name if successful.
 *         - Failure message if the element cannot be deleted or doesn't exist.
 */

  public static String deleteSelectedElement(String elementPath) {
    File selectedElement = new File(elementPath);
    if (selectedElement.exists()) {
      if (selectedElement.isDirectory()) {
        // If it's a directory, delete all its contents recursively
        deleteDirectory(selectedElement);
      }
      if (selectedElement.delete()) {
        CsvReader.deleteCsvLine(selectedElement.getParent(), 0, selectedElement.getName());
        return ConsoleColors.GREEN_BACKGROUND + "This file has been Deleted:" 
                + ConsoleColors.RESET + "  "
                + ConsoleColors.GREEN_UNDERLINED + selectedElement.getName() + ConsoleColors.RESET;
      } else {
        return ConsoleColors.RED_BACKGROUND + "Failed to delete: " + selectedElement.getName()
                        + ConsoleColors.RESET;
      }
    } else {
      return ConsoleColors.RED_BACKGROUND 
            + "Element does not exist: " + elementPath + ConsoleColors.RESET;
    }
  }

  /**
 * Recursively deletes the contents of the specified directory.
 * Deletes all files and subdirectories within the directory.
 *
 * @param directory The directory to be deleted.
 */

  public static void deleteDirectory(File directory) {
    File[] contents = directory.listFiles();
    if (contents != null) {
      for (File file : contents) {
        if (file.isDirectory()) {
          deleteDirectory(file); // Recursively delete subdirectories
        }
        if (!file.delete()) {
          System.out.println("Failed to delete: " + file.getName());
        }
      }
    }
  }
}
