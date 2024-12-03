package fr.uvsq.cprog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * The {@code CsvReader} class provides functionalities to read, modify, and manipulate CSV files.
 * It offers methods to search, delete, add, and check for the existence 
 * of specific lines in a CSV file.
 *
 * <p>This class uses file I/O operations to read, write, and modify 
 * CSV files. It contains methods to
 * search for a specific line, delete a line based on certain criteria, add new lines to a CSV file,
 * and check if a line already exists in the file.
 *
 * <p>Example usage:
 * <pre>{@code
 * // Search for a line in the CSV file
 * String searchResult = CsvReader.searchCsvLine("/path/to/directory", 0, "searchString");
 *
 * // Delete a line from the CSV file
 * String deleteResult = CsvReader.deleteCsvLine("/path/to/directory", 0, "searchString");
 *
 * // Add a new line to the CSV file
 * String addResult = CsvReader.addCsvLine("/path/to/directory", "fileName", "note");
 *
 * // Check if a line exists in the CSV file
 * boolean exists = CsvReader.LineExists("/path/to/directory", 0, "searchString");
 * }</pre>
 *
 * @author [Author Name]
 * @version 1.0
 * @since [Date]
 */
public class CsvReader {

  /**
 * Searches for a specific line in a CSV file based on the given column and search string.
 *
 * <p>This method reads the CSV file located at the specified path and searches for a line where
 * the content in the specified column matches the provided search 
 * string. If a matching line is found,
 * it retrieves the value from the second column of that line. 
 * The method returns the retrieved value
 * upon finding a matching line, or null if no match is found.
 *
 * <p>If the CSV file doesn't exist or encounters an error while reading the file, an error message
 * indicating the failure is returned in a red background color.
 *
 * @param path         The path to the directory containing the CSV file.
 * @param column       The column index in the CSV file to perform the search.
 * @param searchString The string to search within the specified column.
 * @return The value from the second column of the matching line, or null if no match is found.
 */

  public static String searchCsvLine(String path, int column, String searchString) {
    String resultValue = null;
    try {
      File csvfile = new File(path + File.separator + "note.csv");
      // If note files doesn't exist we create one
      csvfile.createNewFile();

      BufferedReader br = new BufferedReader(new FileReader(csvfile));

      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        // Check if there are enough columns and the value in the second column matches
        // the search string
        if (values.length > column && values[column].equals(searchString)) {
          // Retrieve the value from the second column
          resultValue = values[1];
          break;
        }
      }
      br.close();
      return resultValue;
    } catch (Exception e) {
      return ConsoleColors.RED_BACKGROUND + "error reading the csv file" + ConsoleColors.RESET;
    }
  }

  /**
 * Deletes a specific line from a CSV file based on the given column and search string.
 *
 * <p>This method reads the CSV file located at the specified path, searches for a line where
 * the content in the specified column matches the provided 
 * search string. If a matching line is found,
 * it gets removed from the file. The method returns a success message upon removing the line,
 * otherwise, it returns an error message.
 *
 * <p>If the CSV file doesn't exist or encounters an error while performing the deletion,
 * an error message indicating the failure is returned.
 *
 * @param path         The path to the directory containing the CSV file.
 * @param column       The column index in the CSV file to perform the search.
 * @param searchString The string to search within the specified column.
 * @return A message indicating success or failure in deleting the line.
 */

  public static String deleteCsvLine(String path, int column, String searchString) {
    try {
      File csvfile = new File(path + File.separator + "note.csv");

      // Create a temporary file to write the modified content
      File tempFile = new File(path + File.separator + "temp.csv");

      BufferedReader br = new BufferedReader(new FileReader(csvfile));
      BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        // Check if there are enough columns and the value in the specified column
        // matches the search string
        if (values.length > column && values[column].equals(searchString)) {
          // Skip the line if it matches the search string (i.e., delete it)
          continue;
        }
        // Write the line to the temporary file
        bw.write(line + System.lineSeparator());
      }

      br.close();
      bw.close();

      // Replace the original file with the temporary file
      csvfile.delete();
      tempFile.renameTo(csvfile);
      return ConsoleColors.GREEN_BACKGROUND + "Note removed successfully" + ConsoleColors.RESET;

    } catch (Exception e) {
      return ConsoleColors.RED_BACKGROUND + "Error deleting the line from the CSV file" 
        + ConsoleColors.RESET;
    }
  }

  /**
 * Adds a new line to a CSV file with the provided content.
 *
 * <p>This method reads the existing CSV file located at the specified path and checks if
 * the line with the given filename already exists. If the line doesn't exist, the method appends
 * a new line to the CSV file with the provided 
 * filename and note content. It returns a success message
 * upon adding the line, otherwise, it returns an error message.
 *
 * <p>If an error occurs during the file reading or writing process, an error message is returned.
 *
 * @param path     The path to the directory containing the CSV file.
 * @param fileName The name for the new entry to be added in the CSV file.
 * @param note     The note content associated with the new entry.
 * @return A message indicating success or failure in adding the new line.
 */

  public static String addCsvLine(String path, String fileName, String note) {
    // Try block to check for exceptions
    try {
      if (lineExists(path, 0, fileName)) {
        return ConsoleColors.YELLOW_BACKGROUND 
            + "Note already exists please do the command '-' first"
                         + ConsoleColors.RESET;
      }
      // Open the given file in read mode to check for existing notes
      BufferedReader existingFileReader = new BufferedReader(new FileReader(path
           + File.separator + "note.csv"));

      // Check if the note already exists

      // Close the reader after checking existing notes
      existingFileReader.close();

      // Open the file in append mode by creating an object of BufferedWriter class
      BufferedWriter out = new BufferedWriter(new FileWriter(path + File.separator 
           + "note.csv", true));

      // Writing filename and note on the same line
      out.write(fileName + "," + note + "\n");

      // Closing the connection
      out.close();

      return ConsoleColors.GREEN_BACKGROUND 
           + "Note added successfully to file: note.csv" + ConsoleColors.RESET;
    
    // Catch block to handle the exceptions
    } catch (Exception e) {
      // Display message when an exception occurs
      return "Error occurred while adding the note";
    }
  }

  /**
 * Checks if a specific line exists in a CSV file based on the given column and search string.
 *
 * <p>This method reads the CSV file located at the specified path and checks if a line exists where
 * the content in the specified column matches the provided 
 * search string. It returns a boolean value
 * indicating whether such a line exists or not.
 *
 * <p>If the CSV file doesn't exist or encounters an error 
 * while reading the file, false is returned.
 *
 * @param path         The path to the directory containing the CSV file.
 * @param column       The column index in the CSV file to perform the search.
 * @param searchString The string to search within the specified column.
 * @return True if the line exists, false otherwise.
 */

  public static boolean lineExists(String path, int column, String searchString) {
    try {
      File csvfile = new File(path + File.separator + "note.csv");
      BufferedReader br = new BufferedReader(new FileReader(csvfile));

      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        // Check if there are enough columns and the value in the second column matches
        // the search string
        if (values.length > column && values[column].equals(searchString)) {
          br.close();
          return true;

        }
      }
      br.close();
      return false;
    } catch (Exception e) {
      return false;
    }
  }
}
