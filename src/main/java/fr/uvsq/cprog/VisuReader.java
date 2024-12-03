package fr.uvsq.cprog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code VisuReader} class provides methods to visualize the content 
 * of files based on their type.
 * It allows reading and processing different file types such as 'txt' or 'csv'.
 * 
 * <p>It includes a method:
 * <ul>
 *   <li>{@link #visualizeFileContent(String, String)}: 
 * Reads the content of a specified file based on its type
 *       and returns the content as a string.</li>
 * </ul>
 * 
 * <p>The class handles file existence checks and provides visualization based on the file's type.
 * For 'txt' or 'csv' files, it reads and concatenates the lines. 
 * For other types, it displays the file size.
 * 
 * <p>This class uses {@link ConsoleColors} to format the console output.@see ConsoleColors
 */
public class VisuReader {
  /**
 * Reads and visualizes the content of a file based on its type.

@param path The path to the file to be visualized.
@param type The type of the file ('txt' or 'csv').
@return A string representing the content of the file or specific information based on the file type
@throws IOException If an I/O error occurs while reading the file.
 * 
*     <p>The method reads the content of the specified file based on its type.
*     For 'txt' or 'csv' files, it reads and concatenates all lines into a single string 
separated by newlines.
*     For other types, it returns the size of the file in bytes.</p>
 * 
 *     <p>This method assumes that the file exists at the specified path. 
 *      It throws an IOException if the file
 *     doesn't exist or if there is an error while reading the file.</p>
 */
  public static String visualizeFileContent(String path, String type) throws IOException {
    File file = new File(path);

    if (file.exists()) {
      if (type.equals("txt") || type.equals("csv")) {
        // Creating a list to store lines
        List<String> lines = new ArrayList<>();
        // Creating an object of BufferedReader class
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
          // Declaring a string variable
          String st;
          // Condition holds true till there is a character in a string
          while ((st = br.readLine()) != null) {
            // Add the string to the list
            lines.add(st);
          }
        }
        // Join lines into a single string and return
        return String.join("\n", lines);

      } else {
        return ConsoleColors.GREEN_BACKGROUND + "The size of the file: " + file.length() + " bytes"
                    + ConsoleColors.RESET;
      }
    } else {
      return ConsoleColors.RED_BACKGROUND + "File doesn't exist" + ConsoleColors.RESET;
    }
  }
}
