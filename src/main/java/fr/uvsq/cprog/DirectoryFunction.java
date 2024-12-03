package fr.uvsq.cprog;

import java.io.File;
/**
 * The {@code DirectoryFunction} class provides a simple utility method for creating directories.
 * It allows the user to specify a path and a directory name, attempting to create a directory
 * at the specified location.
 *
 * <p>This class uses the {@link File} class to represent the abstract pathname of the directory
 * and provides feedback on whether the directory creation was successful or not.
 *
 * <p>The method {@code createdirectory} takes a path and a name as parameters and returns a
 * string message indicating the outcome of the directory creation attempt. The success or failure
 * message is colored for better visibility in the console.
 *
 * <p>Example usage:
 * <pre>
 *     String path = "/home/user/documents";
 *     String name = "new_directory";
 *     String result = DirectoryFunction.createdirectory(path, name);
 *     System.out.println(result);
 * </pre>
 *
 * @version 1.0
 * @since [Date]
 */

public class DirectoryFunction {
  /**
     * Creates a directory at the specified path and name.
     *
     * @param path The path where the directory will be created.
     * @param name The name of the directory to be created.
     * @return A message indicating the outcome of the directory creation attempt.
     *         It returns a colored success or failure message.
     */
  static String createdirectory(String path, String name) {
    // specify an abstract pathname in the File object
    File f = new File(path + File.separator + name);
    //check if the directory can be created
    //using the specified path name
    if (f.mkdir() == true) {
      return ConsoleColors.GREEN_BACKGROUND  
        + "Directory has been created successfully" + ConsoleColors.RESET;
    } else {
      return ConsoleColors.RED_BACKGROUND + "Directory cannot be created" + ConsoleColors.RESET;
    }
  }
}
