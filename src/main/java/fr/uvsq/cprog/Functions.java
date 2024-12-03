package fr.uvsq.cprog;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility class containing various static methods for string manipulation and file operations.
 * <p>
 * This class provides methods for trimming ArrayList elements, splitting strings, 
 * appending text to a file,
 * clearing the console screen, listing directory contents, and checking if a string is an integer.
 * </p>
 * <p>
 * The methods in this class are static and can be used directly without instantiating the class.
 * </p>
 */
public class Functions {
  /**
     * Trims all elements in the provided ArrayList of strings.
     *
     * @param strArrayList The ArrayList of strings to be trimmed.
     * @return The trimmed ArrayList of strings.
     */
  public static ArrayList<String> trimArrayList(ArrayList<String> strArrayList) {
    for (int i = 0; i < strArrayList.size(); i++) {
      strArrayList.set(i, strArrayList.get(i).trim());
    }
    return strArrayList;
  }
  // TO append string into a file
  /**
     * Splits the input string based on the specified delimiter and converts it to an ArrayList.
     *
     * @param s   The input string to be split.
     * @param cut The delimiter for splitting the string.
     * @return An ArrayList containing the split elements of the input string.
     */

  public static ArrayList<String> splitString(String s, String cut) {
    // split string by space
    // Now convert string into ArrayList
    return new ArrayList<String>(Arrays.asList(s.split(cut)));
  }
  // Function to clear the previous output
  /**
     * Clears the console screen by sending the appropriate escape sequences.
     */
    
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }


  /**
     * Checks if the given string is an integer.
     *
     * @param s The string to be checked.
     * @return {@code true} if the string is an integer, {@code false} otherwise.
     */

  public static boolean isInteger(String s) {
    return isInteger(s, 10);
  }
  /**
     * Checks if the given string is an integer with a specified radix.
     *
     * @param s     The string to be checked.
     * @param radix The radix to be used for conversion.
     * @return {@code true} if the string is an integer in the specified radix, 
     {@code false} otherwise.
     */

  public static boolean isInteger(String s, int radix) {
    if (s.isEmpty()) {
      return false;
    }
    for (int i = 0; i < s.length(); i++) {
      if (i == 0 && s.charAt(i) == '-') {
        if (s.length() == 1) {
          return false;
        } else {
          continue;
        }
      }
      if (Character.digit(s.charAt(i), radix) < 0) {
        return false;
      }
    }
    return true;
  }
}
