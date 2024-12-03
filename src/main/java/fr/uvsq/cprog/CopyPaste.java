package fr.uvsq.cprog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Generates a File object for the provided element path.
 *
 * @param elementpath The path of the element to copy.
 * @return A File object representing the element to copy.
 */

public class CopyPaste {
  static File elementForCopy(String elementpath) {
    return new File(elementpath);
  }

  /**
 * Pastes the copied element into the specified destination path.
 *
 * @param destinationPath The destination path where the element will be pasted.
 * @param elementcopied   The File object representing the element being copied.
 * @param cut             Indicates whether the element was cut (true) or not (false).
 * @return A string message indicating the outcome of the paste operation.
 */

  static String pasteElement(String destinationPath, File elementcopied, boolean cut) {
    if (elementcopied != null) {
      File destinationDir = new File(destinationPath);
      String fileName = elementcopied.getName();
      File destinationFile = new File(destinationDir.getAbsolutePath() + File.separator + fileName);

      // Check if the file or directory exists in the destination directory
      String newName;
      int counter = 0;
      while (destinationFile.exists()) {
        // Handling duplicate names for copied elements
        if (elementcopied.isDirectory()) {

          if (0 == counter) {
            newName = fileName + "-copy";
          } else {
            newName = fileName + "-copy" + "(" + counter + ")";
          }
          destinationFile = new File(destinationDir.getAbsolutePath() + File.separator + newName);
        } else {
          String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
          String extension = fileName.substring(fileName.lastIndexOf('.'));
          if (0 == counter) {
            newName = baseName + "-copy" + extension;
          } else {
            newName = baseName + "-copy" + "(" + counter + ")" + extension;
          }

          destinationFile = new File(destinationDir.getAbsolutePath() + File.separator + newName);
        }
        counter++;
      }
      try {
        if (elementcopied.isDirectory()) {
          // Copy the directory and its contents
          copyDirectory(elementcopied, destinationFile);
          if (cut == true) {
            DeleteFunction.deleteSelectedElement(elementcopied.getPath());
          }
        } else {
          // Copy the file
          Files.copy(elementcopied.toPath(), destinationFile.toPath());
          if (cut == true) {
            elementcopied.delete();
          }
        }
        return ConsoleColors.GREEN_BACKGROUND + "Element pasted:" + ConsoleColors.RESET + "  "
                + ConsoleColors.GREEN_UNDERLINED + destinationFile.getName() + ConsoleColors.RESET;
      } catch (IOException e) {
        return ConsoleColors.RED_BACKGROUND + "Failed to paste the element: " + e.getMessage()
                        + ConsoleColors.RESET;
      }
    } else {
      return ConsoleColors.RED_BACKGROUND + "No element marked for copy." + ConsoleColors.RESET;
    }
  }

  /**
 * Recursively copies a directory and its contents to a specified destination.
 *
 * @param source      The source directory to copy.
 * @param destination The destination directory where the source will be copied.
 */

  public static void copyDirectory(File source, File destination) {
    if (!destination.exists()) {
      destination.mkdirs();
    }

    File[] files = source.listFiles();
    if (files != null) {
      for (File file : files) {
        File destinationFile = new File(destination.getAbsolutePath() + File.separator 
              + file.getName());

        if (file.isDirectory()) {
          copyDirectory(file, destinationFile);
        } else {
          try {
            Files.copy(file.toPath(), destinationFile.toPath(),
                 StandardCopyOption.REPLACE_EXISTING);
          } catch (IOException e) {
            System.out.println("Failed to copy the file: " + e.getMessage());
          }
        }
      }
    }
  }

}
