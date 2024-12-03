package fr.uvsq.cprog;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;

/**
 * Searches for a file with a specific name within the given base path.
 *
 * @param basePath  The base path to start the search from.
 * @param fileName  The name of the file to search for.
 * @return A string containing the paths of all occurrences of the file found,
 *         or an indication that the file was not found.
 */

public class FileFunctions {
  /**
 * Searches for a file with the specified name within the given base path.
 *
 * @param basePath The base path where the search begins.
 * @param fileName The name of the file to search for.
 * @return A string containing the paths of all occurrences of the 
 *      file found, or an indication that the file was not found.
 */
    
  public static String findFunction(String basePath, String fileName) {
    StringBuilder result = new StringBuilder();

    try {
      File baseDir = new File(basePath);

      boolean recursive = true;

      Collection<File> files = FileUtils.listFiles(baseDir, null, recursive);

      for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
        File file = iterator.next();
        if (file.getName().equals(fileName)) {
          // Calculate the relative path
          String relativePath = baseDir.toURI().relativize(file.toURI()).getPath();
          result.append(ConsoleColors.YELLOW_UNDERLINED + relativePath 
          + ConsoleColors.RESET).append("\n");
        }
      }

      if (result.length() > 0) {
        return result.toString();
      } else {
        return ConsoleColors.YELLOW_UNDERLINED + "File " + fileName 
          + " not found" + ConsoleColors.RESET;
      }
    } catch (Exception e) {
      return ConsoleColors.RED_BACKGROUND + "Error finding the file: " 
         + e.getMessage() + ConsoleColors.RESET;
    }
  }
}
